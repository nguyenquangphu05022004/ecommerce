package com.example.ecommerce.dao.impl;

import com.example.ecommerce.config.SpringJdbcConfig;
import com.example.ecommerce.dao.EventDAO;
import com.example.ecommerce.entity.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
public class UserEventDao implements EventDAO<User> {

    private final DataSource dataSource = SpringJdbcConfig.mysqlDataSource();
    //this event auto remove after code(forgotten) expire
    @Override
    public void createEvent(User user) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "create event event_verify_code_" + user.getId() + "\n" +
                    "on schedule AT CURRENT_TIMESTAMP + INTERVAL 5 MINUTE\n" +
                    "DO\n" +
                    "    UPDATE verify SET status = 0 where user_id = ?";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setLong(1, user.getId());
            query.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
