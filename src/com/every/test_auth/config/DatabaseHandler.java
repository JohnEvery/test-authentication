package com.every.test_auth.config;

import java.sql.*;


public class DatabaseHandler extends Configs {
     Connection connection;

     public  Connection getConnection() throws ClassNotFoundException, SQLException {
         String connectionString = "jdbc:postgresql://localhost:5432/test_fx";

         connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
         Class.forName("org.postgresql.Driver");

        return connection;
     }


    public ResultSet getUser(User user) {

        ResultSet resultSet = null;

        String request = "SELECT * from " + Const.USER_TABLE + " WHERE "
                + Const.USERS_USERNAME + " =? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(request);

            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }
}
