package com.baobaotao.dao;

import com.baobaotao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WQJ on 6/18/2017.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名和密码获取匹配的用户数。
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户数
     */
    public int getMatchCount(String userName, String password) {
        String sqlStr = "SELECT count(*) FROM t_user " + " WHERE user_name=? and password=?";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    public User findUserByUserName(final String userName) {
        String sqlStr = "SELECT user_id,user_name,credits " + " FROM t_user WHERE user_name=?";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    /**
     * 更新用户登录信息
     *
     * @param user 用户信息
     */
    public void updateLoginInfo(User user) {
        String sqlStr = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? " + " WHERE user_id=?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(),
                user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
