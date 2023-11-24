package Hackerton.Backend.Repository.Impl;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.Embeded.FundingRelationship;
import Hackerton.Backend.Data.Entity.Funding;
import Hackerton.Backend.Repository.FundingRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Hackerton.Backend.Data.Entity.QFunding.funding;

@Repository
public class FundingRepositoryImpl extends QuerydslRepositorySupport implements FundingRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final DataSource dataSource;

    public FundingRepositoryImpl(JPAQueryFactory jpaQueryFactory, DataSource dataSource) {
        super(Funding.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.dataSource = dataSource;
    }

    @Override
    public void save(Funding funding) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO funding (user_id, concert_id, funding_price) VALUES (?, ?, ?)"
            );

            preparedStatement.setInt(1, funding.getFundingRelationship().getUser().getId());
            preparedStatement.setLong(2, funding.getFundingRelationship().getConcert().getId());
            preparedStatement.setInt(3, funding.getFundingPrice());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    @Transactional
    public void update(Funding funding) {
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE funding SET funding_price = ? WHERE user_id = ? and concert_id = ?"
            );

            preparedStatement.setInt(1, funding.getFundingPrice());
            preparedStatement.setInt(2, funding.getFundingRelationship().getUser().getId());
            preparedStatement.setLong(3, funding.getFundingRelationship().getConcert().getId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public Funding findById(FundingRelationship fundingRelationship) {
        return jpaQueryFactory.select(funding)
                .from(funding)
                .where(funding.fundingRelationship.eq(fundingRelationship)).fetchOne();
    }

    @Override
    public Integer getCurFundingByConcert(Concert concert) {
        return jpaQueryFactory.select(funding.fundingPrice.sum())
                .from(funding).where(funding.fundingRelationship.concert.eq(concert)).fetchOne();
    }

    @Override
    public Integer getFundingPriceByConcertIdAndUserId(Long concert_id, Integer user_id) {
        return jpaQueryFactory.select(funding.fundingPrice)
                .from(funding)
                .where(
                        funding.fundingRelationship.concert.id.eq(concert_id),
                        funding.fundingRelationship.user.id.eq(user_id)
                ).fetchOne();
    }
}
