package com.alvarez.app.rutas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    //Atributos
    private static String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static String username = "SYSTEM";
    private static String password = "admin123";

    public static Connection getInstance(){
        Connection con = null;
        try {
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
