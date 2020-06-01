package com.company;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

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
        statement = connection.createStatement( );
        String sqlQuery = "SELECT coefic FROM increasing_coefficient";
        resultSet = statement.executeQuery( sqlQuery );
        while (resultSet.next( )) {
            BigDecimal coefficient = resultSet.getBigDecimal( 1 );
            incCoef = coefficient;
        }
        connection.close( );
        return incCoef;
    }

    public Map<String, BigDecimal> spotting_picking_cost ( Double x , Double y ) throws ClassNotFoundException, SQLException {
        Class.forName( "com.mysql.jdbc.Driver" );
        connection = DriverManager.getConnection( URL , USERNAME , PASSWORD );
        statement = connection.createStatement( );

        Map<String, BigDecimal> resultMap = new LinkedHashMap<>( );
        String query = "";

        //по двум таблицам
        if (x > 0 && y > 0) {
            double z = x + y;
            if (z <= 0.5) {
                int from = 0;
                String till = "05";
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1.DIST_FROM_" + from + "_" + till + " AS t1, table_pu_2.DIST_FROM_" + from + "_" + till + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";

                resultSet = statement.executeQuery( query );
                while (resultSet.next( )) {
                    BigDecimal a = resultSet.getBigDecimal( 2 );
                    BigDecimal b = resultSet.getBigDecimal( 3 );

                    /* The formulas for calculating the cost of two tables are described: Total filing distance
                     and picking up 9.4 km, including 2.8 km
                     on the balance of the railway and 6.6 km - on the balance of the enterprise. The actual number of submissions
                     and wagons taken during the reporting period is 30 wagons. The weighted average rate is determined by:
                     (4052.4 x 6.6 + 5674.7 x 2.8): (6.6 + 2.8) = (26745.8 + 15888.3): 9.4 = 4535.6 UAH,
                     */

                    resultMap.put( resultSet.getString( 1 ) , (a.multiply( BigDecimal.valueOf( x ) )).add( b.multiply( BigDecimal.valueOf( y ) ) ).divide( BigDecimal.valueOf( x ).add( BigDecimal.valueOf( y ) ) ) );
                }
            }

            if (z > 0.5 && z <= 1) {
                String from = "05";
                int till = 1;
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1.DIST_FROM_" + from + "_" + till + " AS t1, table_pu_2.DIST_FROM_" + from + "_" + till + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";

                resultSet = statement.executeQuery( query );
                while (resultSet.next( )) {
                    BigDecimal a = resultSet.getBigDecimal( 2 );
                    BigDecimal b = resultSet.getBigDecimal( 3 );

                    resultMap.put( resultSet.getString( 1 ) , (a.multiply( BigDecimal.valueOf( x ) )).add( b.multiply( BigDecimal.valueOf( y ) ) ).divide( BigDecimal.valueOf( x ).add( BigDecimal.valueOf( y ) ) ) );
                }
                return resultMap;
            }

            if (z > 1 && z <= 10) {
                int from = (int) Math.floor( x );
                String tq = "_";
                String dist = "DIST_FROM_";
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1." + dist + from + tq + (from + 1) + " AS t1, table_pu_2." + dist + from + tq + (from + 1) + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";

                resultSet = statement.executeQuery( query );
                while (resultSet.next( )) {
                    BigDecimal a = resultSet.getBigDecimal( 2 );
                    BigDecimal b = resultSet.getBigDecimal( 3 );

                    /* The formulas for calculating the cost of two tables are described: Total filing distance
                     and picking up 9.4 km, including 2.8 km
                     on the balance of the railway and 6.6 km - on the balance of the enterprise. The actual number of submissions
                     and wagons taken during the reporting period is 30 wagons. The weighted average rate is determined by:
                     (4052.4 x 6.6 + 5674.7 x 2.8): (6.6 + 2.8) = (26745.8 + 15888.3): 9.4 = 4535.6 UAH,
                     */

                    resultMap.put( resultSet.getString( 1 ) , (a.multiply( BigDecimal.valueOf( x ) )).add( b.multiply( BigDecimal.valueOf( y ) ) ).divide( BigDecimal.valueOf( x ).add( BigDecimal.valueOf( y ) ) ) );
                }
                return resultMap;
            }


        }


        //work with the first table or second table
        if ((x > 0 && y == 0) || (x == 0 && y > 0)) {
            //work with the first table
            if (x > 0 && y == 0) {

                if (x <= 0.5) {
                    int from = 0;
                    String till = "05";
                    query = "SELECT NUMBOFWAG, DIST_FROM_" + from + "_" + till + " FROM table_pu_1";
                }
                if (x > 0.5 && x <= 1) {
                    String from = "05";
                    int till = 1;
                    query = "SELECT NUMBOFWAG, DIST_FROM_" + from + "_" + till + " FROM table_pu_1";
                }

                if (x > 1 && x <= 10) {
                    int from = (int) Math.floor( x );
                    String dist = "DIST_FROM_";
                    String tq = "_";
                    query = "SELECT NUMBOFWAG, " + dist + from + tq + (from + 1) + " FROM table_pu_1";
                }

                resultSet = statement.executeQuery( query );
            }

            //work with the second table
            if (x == 0 && y > 0) {

                if (y <= 0.5) {
                    int from = 0;
                    String till = "05";
                    query = "SELECT NUMBOFWAG, DIST_FROM_" + from + "_" + till + " FROM table_pu_2";
                }
                if (y > 0.5 && y <= 1) {
                    String from = "05";
                    int till = 1;
                    query = "SELECT NUMBOFWAG, DIST_FROM_" + from + "_" + till + " FROM table_pu_2";
                }

                if (y > 1 && y <= 10) {
                    int from = (int) Math.floor( y );
                    String dist = "DIST_FROM_";
                    String tq = "_";
                    query = "SELECT NUMBOFWAG, " + dist + from + tq + (from + 1) + " FROM table_pu_2";
                }

            }

            resultSet = statement.executeQuery( query );
            while (resultSet.next( )) {
                resultMap.put( resultSet.getString( 1 ) , resultSet.getBigDecimal( 2 ) );

            }
        }
        return resultMap;
    }
}
