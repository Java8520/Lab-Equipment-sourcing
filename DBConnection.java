/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Adarsha
 */
public class DBConnection {
     private Connection connection;
    private PreparedStatement createAdminsTable;
    private PreparedStatement createEquipmentsTable;
    private PreparedStatement createMembersTable;
    private PreparedStatement createCampusesTable;

    
    // static method return mysql connection
    public static Connection getConnection() {
        final String MYSQL_URL = "jdbc:mysql://localhost:3306/";
        final String DATABASE_NAME = "CQULabEquipmentSourcing";
        final String DATABASE_URL = MYSQL_URL + DATABASE_NAME;
        final String USER_NAME = "root";
        final String USER_PASSWORD = null;
        PreparedStatement createDatabase;
        Connection conn = null;  // Connection object creation

        try {
            // MySQL connection without specified database name
            conn = DriverManager.getConnection(MYSQL_URL, USER_NAME, USER_PASSWORD);
            if (conn != null) {
                System.out.println("MySQL database has been connected .........");
                createDatabase = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME); // database creation if not exist
                int result = createDatabase.executeUpdate();
                if (result > 0) {
                    System.out.println(DATABASE_NAME + " database has been created successfully IF NOT EXISTS.");
                }
            }

            // database connection or selection with the specified database name
            conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
            if (conn != null) {
                System.out.println(DATABASE_NAME + " database has been connecting successfully");
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            System.out.println("SQLException : " + e.getMessage());
        }
        return conn;
    }

    public void createTables() {
        try {
            this.connection = getConnection(); // database connection
            if (connection != null) {
                
                // admins table creation
                createAdminsTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS admins ("
                        + "admin_id int NOT NULL AUTO_INCREMENT UNIQUE,"
                        + "username VARCHAR(100) NOT NULL,"
                        + "password VARCHAR(45) NOT NULL,"
                        + "email VARCHAR(100) NOT NULL UNIQUE,"
                        + "contact VARCHAR(10) NOT NULL,"
                        + "PRIMARY KEY (admin_id) )");

                int adminsResult = createAdminsTable.executeUpdate();

                if (adminsResult > 0) {
                    System.out.println("Admin table has been created successfully.");
                    createAdminsTable = connection.prepareStatement("ALTER TABLE user AUTO_INCREMENT = 10001");
                    createAdminsTable.executeUpdate();
                }
                
                //members table creation
                createMembersTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS members ("
                        + "member_id int NOT NULL AUTO_INCREMENT UNIQUE,"
                        + "username VARCHAR(100) NOT NULL,"
                        + "password VARCHAR(45) NOT NULL,"
                        + "email VARCHAR(100) NOT NULL UNIQUE,"
                        + "contact VARCHAR(10) NOT NULL,"
                        + "PRIMARY KEY (member_id) )");

                int membersResult = createMembersTable.executeUpdate();

                if (membersResult > 0) {
                    System.out.println("Members table has been created successfully.");
                    createMembersTable = connection.prepareStatement("ALTER TABLE user AUTO_INCREMENT = 10001");
                    createMembersTable.executeUpdate();
                }
                

                //equipments table creation
                createEquipmentsTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS equipments ("
                        + "equipment_id int NOT NULL AUTO_INCREMENT UNIQUE,"
                        + "name VARCHAR(100) NOT NULL,"
                        + "availableQuantity INT NOT NULL,"
                        + "location VARCHAR(100) NOT NULL,"
                        + "PRIMARY KEY (equipment_id))");

                int equipmentsResult = createEquipmentsTable.executeUpdate();
                if (equipmentsResult > 0) {
                    System.out.println("Equipment table has been created successfully.");
                    createEquipmentsTable = connection.prepareStatement("ALTER TABLE book AUTO_INCREMENT = 30001");
                    createEquipmentsTable.executeUpdate();
                }


                // campuses table creation
                createCampusesTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS campuses ("
                        + "campus_id int NOT NULL AUTO_INCREMENT UNIQUE,"
                        + "campus_name VARCHAR(100) NOT NULL,"
                        + "location VARCHAR(50) DEFAULT NULL,"
                        + "phone VARCHAR(100) NOT NULL,"
                        + "PRIMARY KEY (campus_id) )");

                int campusesResult = createCampusesTable.executeUpdate();
                if (campusesResult > 0) {
                    System.out.println("Campuses table has been created successfully.");
                    createCampusesTable = connection.prepareStatement("ALTER TABLE borrower AUTO_INCREMENT = 40001");
                    createCampusesTable.executeUpdate();
                }

            }
        } catch (SQLException e) {
            System.out.println("Connection Failed!2");
            System.out.println("SQLException : " + e.getMessage());
        }
    }
}
