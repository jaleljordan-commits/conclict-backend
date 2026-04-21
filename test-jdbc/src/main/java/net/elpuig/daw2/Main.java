package net.elpuig.daw2;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String
        URL = "jdbc:mysql://172.16.0.119/pruebas",
        usr = "daw2",
        pwd = "secreto";
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection(URL, usr, pwd)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select * from productos");
            while(res.next()){
                int id = res.getInt("id");
                String desc = res.getString("descripcion");
                float precio = res.getFloat("precio");
                System.out.printf("ID: %d %s %.2f", id, desc, precio);
            }
            System.out.println("\nFin del programa");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
}