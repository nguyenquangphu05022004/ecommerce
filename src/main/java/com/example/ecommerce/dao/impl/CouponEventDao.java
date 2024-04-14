package com.example.ecommerce.dao.impl;

import com.example.ecommerce.config.SpringJdbcConfig;
import com.example.ecommerce.dao.EventDAO;
import com.example.ecommerce.entity.Coupon;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class CouponEventDao implements EventDAO<Coupon> {
    private final SpringJdbcConfig springJdbcConfig;
    @Autowired
    public CouponEventDao(SpringJdbcConfig springJdbcConfig) {
        this.springJdbcConfig = springJdbcConfig;
    }
    @Override
    public void createEvent(Coupon coupon) {
        try {
            Connection connection = springJdbcConfig.mysqlDataSource().getConnection();
            PreparedStatement query1 = connection.prepareStatement(queryAfterEventStarted(coupon));
            PreparedStatement query2 = connection.prepareStatement(queryBeforeEventStart(coupon));
            query1.setLong(1, coupon.getId());
            query1.execute();

            query2.setBoolean(1, true);
            query2.setLong(2, coupon.getId());
            query2.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String queryAfterEventStarted(Coupon coupon) {
        StringBuilder queryEvent = new StringBuilder();
        queryEvent.append("create event coupon_");
        queryEvent.append(coupon.getId());
        queryEvent.append(" on schedule at TIMESTAMP('");
        queryEvent.append(SystemUtils.getFormatDate(coupon.getEnd(), "yyyy-MM-dd HH:mm:ss"));
        queryEvent.append("') ");
        queryEvent.append(" DO delete from coupons where id = ?");
        System.out.println(queryEvent.toString());
        return queryEvent.toString();
    }
    private String queryBeforeEventStart(Coupon coupon) {
        StringBuilder queryEvent = new StringBuilder();
        queryEvent.append("create event check_start_coupon_");
        queryEvent.append(coupon.getId());
        queryEvent.append(" on schedule at TIMESTAMP('");
        queryEvent.append(SystemUtils.getFormatDate(coupon.getStart(), "yyyy-MM-dd HH:mm:ss"));
        queryEvent.append("') ");
        queryEvent.append(" DO UPDATE coupons set started = ? where id = ?");
        System.out.println(queryEvent.toString());
        return queryEvent.toString();
    }

}
