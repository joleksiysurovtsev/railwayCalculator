package com.company;

import java.sql.*;
import java.util.Map;
import java.io.*;

public class Main {

	public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException {
		CRUD crud = new CRUD();
		double coefficient = crud.getIncCoefficient();
		double sh = crud.getCostShuntingWork();
		double tc = crud.getCostTime();
		System.out.println(tc);
//		Map<String, String> spc = crud.spotting_picking_cost(1.263, 2.3);
//		System.out.println("На данный момент коефициент равен: " + coefficient);
//		System.out.println(spc.toString());


	}

}
