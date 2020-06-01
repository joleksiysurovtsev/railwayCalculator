package com.company;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {

    public static void main ( String[] args ) throws SQLException, ClassNotFoundException {
        CRUD crud = new CRUD();
        BigDecimal coefficient = crud.getIncCoef();
        System.out.println("На данный момент коефициент равен:" + coefficient);
    }
}
