package com.company;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

public class Main {

    public static void main ( String[] args ) throws SQLException, ClassNotFoundException {
        CRUD crud = new CRUD();
        BigDecimal coefficient = crud.getIncCoef();
        Map<String,BigDecimal> spc = crud.spotting_picking_cost( 3.0,2.0 );
        System.out.println("На данный момент коефициент равен:" + coefficient);
        System.out.println(spc.toString() );
    }
}
