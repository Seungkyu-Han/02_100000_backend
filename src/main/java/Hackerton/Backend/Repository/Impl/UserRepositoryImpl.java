package Hackerton.Backend.Repository.Impl;

import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static Hackerton.Backend.Data.Entity.QUser.user;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final DataSource dataSource;

    public UserRepositoryImpl(JPAQueryFactory jpaQueryFactory, DataSource dataSource) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Integer id) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.id.eq(id)).fetchOne();
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user (id, name, refresh_token, role) values " +
                            "(?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getRefreshToken());
            preparedStatement.setString(4, user.getRole().toString());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();


        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE user SET interest = ?, name = ?, refresh_token = ?, role = ? WHERE user.id = ?"
            );


            if (user.getInterest() != null) {
                preparedStatement.setString(1, user.getInterest().toString());
            } else {
                preparedStatement.setNull(1, Types.VARCHAR);
            }
            preparedStatement.setString(2, user.getName());
            if (user.getRefreshToken() != null) {
                preparedStatement.setString(3, user.getRefreshToken());
            } else {
                preparedStatement.setNull(3, Types.VARCHAR);
            }
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setInt(5, user.getId());


            preparedStatement.execute();

            preparedStatement.close();
            connection.close();


        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
