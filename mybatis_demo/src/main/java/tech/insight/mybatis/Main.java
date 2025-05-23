package tech.insight.mybatis;

import java.sql.*;

/**
 * @author lijiaobin
 * @version 1.0
 * @project Default (Template) Project
 * @description ${description}
 * @date 2025/5/23 16:12:44
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MySqlSessionFactory mySqlSessionFactory = new MySqlSessionFactory();
        UserMapper mapper = mySqlSessionFactory.getMapper(UserMapper.class);
        User user = mapper.selectById(2);
        System.out.println(user);

    }

    private static User jdbcSelectById(int id) {
        String JDBCURL = "jdbc:mysql://localhost:3306/mybatis_db";
        String DBUSER = "root";
        String PASSWORD = "12345678";

        String sql = "SELECT id, name, age FROM user WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBCURL, DBUSER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                User user = new User();
                user.setId(res.getInt("id"));
                user.setName(res.getString("name"));
                user.setAge(res.getInt("age"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}