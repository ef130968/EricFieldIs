import java.sql.SQLException
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

target(drop_database: "This script deletes the database. It is meant to be used prior to using data-genesis") {

    println ">>>> DATABASE IS BEING DROPPED"
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", 'root');

        stmt = con.createStatement();
        stmt.executeUpdate("DROP DATABASE IF EXISTS ericfieldis_development");
        stmt = con.createStatement();
        String sql = "CREATE DATABASE ericfieldis_development";
        stmt.executeUpdate(sql);

    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {rs.close();} catch (Exception e) {}
        try {stmt.close();} catch (Exception e) {}
        try {con.close();} catch (Exception e) {}
    }
    println ">>>> DATABASE HAS BEEN RECREATED EMPTY"
}

setDefaultTarget(drop_database)
