package Hackerton.Backend.Repository.Impl;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import Hackerton.Backend.Repository.ConcertPhotoRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static Hackerton.Backend.Data.Entity.QConcertPhoto.concertPhoto;

@Repository
public class ConcertPhotoRepositoryImpl extends QuerydslRepositorySupport implements ConcertPhotoRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final DataSource dataSource;

    public ConcertPhotoRepositoryImpl(JPAQueryFactory jpaQueryFactory, DataSource dataSource) {
        super(ConcertPhoto.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.dataSource = dataSource;
    }

    @Override
    public void save(ConcertPhoto concertPhoto) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO concert_photo (concert, imgurl) values " +
                            "(?, ?)"
            );

            preparedStatement.setLong(1, concertPhoto.getConcert().getId());
            preparedStatement.setString(2, concertPhoto.getImgUrl());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();


        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public List<ConcertPhoto> findByConcert(Concert concert) {
        return jpaQueryFactory
                .select(concertPhoto)
                .from(concertPhoto)
                .where(concertPhoto.concert.eq(concert)).fetch();
    }
}
