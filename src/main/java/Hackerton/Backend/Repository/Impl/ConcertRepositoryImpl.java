package Hackerton.Backend.Repository.Impl;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ConcertRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Hackerton.Backend.Data.Entity.QConcert.concert;

@Repository
public class ConcertRepositoryImpl extends QuerydslRepositorySupport implements ConcertRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final DataSource dataSource;

    public ConcertRepositoryImpl(JPAQueryFactory jpaQueryFactory, DataSource dataSource) {
        super(Concert.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.dataSource = dataSource;
    }

    @Override
    public void save(Concert concert) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO concert (concert_date, region, funding_date, funding_price, latitude, longitude) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );

            preparedStatement.setDate(1, concert.getConcertDate());
            preparedStatement.setString(2, concert.getRegion().toString());
            preparedStatement.setDate(3, concert.getFundingDate());
            preparedStatement.setInt(4, concert.getFundingPrice());
            preparedStatement.setFloat(5, concert.getLatitude());
            preparedStatement.setFloat(6, concert.getLongitude());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    @Transactional
    public void update(Concert concert) {
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE concert SET concert_date = ?, region = ?, funding_date = ?, funding_price = ?, latitude = ?, longitude = ? WHERE concert.id = ?"
            );
            preparedStatement.setDate(1, concert.getConcertDate());
            preparedStatement.setString(2, concert.getRegion().toString());
            preparedStatement.setDate(3, concert.getFundingDate());
            preparedStatement.setInt(4, concert.getFundingPrice());
            preparedStatement.setFloat(5, concert.getLatitude());
            preparedStatement.setFloat(6, concert.getLongitude());
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public Concert findById(Long id) {
        return jpaQueryFactory
                .select(concert)
                .from(concert)
                .where(concert.id.eq(id)).fetchOne();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        jpaQueryFactory.delete(concert)
                .where(concert.id.eq(id)).execute();
    }

    @Override
    public Boolean checkConcertByUser(Long id, User user) {
        return jpaQueryFactory.select(concert.artist.user.eq(user))
                .from(concert)
                .where(concert.id.eq(id)).fetchOne();
    }
}
