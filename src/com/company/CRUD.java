package com.company;

import java.math.BigDecimal;
import java.sql.*;

public class CRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/station_db?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private BigDecimal cosTime;
    private BigDecimal incCoef;

    public BigDecimal getIncCoef ( ) throws ClassNotFoundException, SQLException {
        Class.forName( "com.mysql.jdbc.Driver" );
        connection = DriverManager.getConnection( URL , USERNAME , PASSWORD );
        statement = connection.createStatement();
        String sqlQuery = "SELECT coefic FROM increasing_coefficient";
        resultSet = statement.executeQuery( sqlQuery );
        while (resultSet.next( )) {
            BigDecimal coefficient = resultSet.getBigDecimal( 1 );
            incCoef = coefficient;
        }
        connection.close( );
        return incCoef;
    }

}
