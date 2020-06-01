package com.company;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/station_db?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private BigDecimal cosTime;
    private BigDecimal incCoef;



}
