package com.company;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class CRUD {

    private Map<String, String> csc = GetSettings.serverSetting( "F:\\IdeaProjects\\railwayCalculator\\src\\com\\company\\db_connection_set.txt" );

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private double costTime;
    private double incCoefficient;
    private double costShuntingWork;

    public CRUD ( ) throws IOException {
    }

    private void getConnection ( ) throws ClassNotFoundException, SQLException {
        Class.forName( "com.mysql.jdbc.Driver" );
        connection = DriverManager.getConnection( csc.get( "URL" ) , csc.get( "USERNAME" ) , csc.get( "PASSWORD" ) );
        statement = connection.createStatement( );
    }

    public double getIncCoefficient ( ) throws ClassNotFoundException, SQLException {
        getConnection( );
        String sqlQuery = "SELECT coefic FROM increasing_coefficient";
        resultSet = statement.executeQuery( sqlQuery );
        while (resultSet.next( )) {
            incCoefficient = resultSet.getDouble( 1 );
        }
        connection.close( );
        return incCoefficient;
    }

    public double getCostTime ( ) throws ClassNotFoundException, SQLException {
        getConnection( );
        String sqlQuery = "SELECT TIME_COAST FROM station_db.time_of_use where ID = 1";
        resultSet = statement.executeQuery( sqlQuery );
        while (resultSet.next( )) {
            costTime = resultSet.getDouble( "TIME_COAST" );
        }
        connection.close( );
        return costTime;
    }

    public double getCostShuntingWork ( ) throws SQLException, ClassNotFoundException {
        getConnection( );
        String sqlQuery = "SELECT coast FROM station_db.other_expenses where ID ='shunting'";
        resultSet = statement.executeQuery( sqlQuery );
        while (resultSet.next( )) {
            costShuntingWork = resultSet.getDouble( "coast" );
        }
        connection.close( );
        return costShuntingWork;
    }

    public Map<String, String> spotting_picking_cost ( Double x , Double y ) throws ClassNotFoundException, SQLException {
        getConnection( );

        Map<String, String> resultMap = new LinkedHashMap<>( );
        String query = "";
        double z = x + y;
        DecimalFormat df = new DecimalFormat( "###.##");

        //по двум таблицам
        if (x > 0 && y > 0) {
            if (z <= 0.5) {
                double from = 0.0;
                String till = "05";
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1.DIST_FROM_" + from + "_" + till + " AS t1, table_pu_2.DIST_FROM_" + from + "_" + till + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";
                System.out.println( query );
            }

            if (z > 0.5 && z <= 1) {
                String from = "05";
                int till = 1;
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1.DIST_FROM_" + from + "_" + till + " AS t1, table_pu_2.DIST_FROM_" + from + "_" + till + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";
            }

            if (z > 1 && z <= 10) {
                int from = (int) Math.floor( z );
                String tq = "_";
                String dist = "DIST_FROM_";
                query = "SELECT table_pu_1.NUMBOFWAG AS nt1, table_pu_1." + dist + from + tq + (from + 1) + " AS t1, table_pu_2." + dist + from + tq + (from + 1) + " AS t2 FROM table_pu_1 LEFT JOIN table_pu_2 on (table_pu_1.NUMBOFWAG = table_pu_2.NUMBOFWAG)";
            }

            resultSet = statement.executeQuery( query );
            while (resultSet.next( )) {
                double a = resultSet.getDouble( 2 );
                double b = resultSet.getDouble( 3 );
                BigDecimal f = BigDecimal.valueOf( ((a * x) + (b * y)) / (x + y) );
                resultMap.put( resultSet.getString( 1 ) , df.format( f ) );
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
                BigDecimal f = BigDecimal.valueOf( resultSet.getDouble( 2 ) );

                resultMap.put( resultSet.getString( 1 ) , df.format( f ) );
            }
        }
        return resultMap;
    }


}
