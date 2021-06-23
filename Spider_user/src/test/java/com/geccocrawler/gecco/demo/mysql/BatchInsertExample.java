package com.geccocrawler.gecco.demo.mysql;

import java.sql.*;
import java.util.Properties;

public class BatchInsertExample {
    public static void main(String[] args) {
        Properties myProp = new Properties();
        myProp.put("user", "ExampleUser");
        myProp.put("password", "password123");

        //Set streamingBatchInsert to True to enable streaming mode for batch inserts.
        //myProp.put("streamingBatchInsert", "True");

        Connection conn;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:vertica://VerticaHost:5433/ExampleDB",
                    myProp);
            // establish connection and make a table for the data.
            Statement stmt = conn.createStatement();


            // Set AutoCommit to false to allow Vertica to reuse the same
            // COPY statement
            conn.setAutoCommit(false);


            // Drop table and recreate.
            stmt.execute("DROP TABLE IF EXISTS customers CASCADE");
            stmt.execute("CREATE TABLE customers (CustID int, Last_Name"
                    + " char(50), First_Name char(50),Email char(50), "
                    + "Phone_Number char(12))");
            // Some dummy data to insert.
            String[] firstNames = new String[] { "Anna", "Bill", "Cindy",
                    "Don", "Eric" };
            String[] lastNames = new String[] { "Allen", "Brown", "Chu",
                    "Dodd", "Estavez" };
            String[] emails = new String[] { "aang@example.com",
                    "b.brown@example.com", "cindy@example.com",
                    "d.d@example.com", "e.estavez@example.com" };
            String[] phoneNumbers = new String[] { "123-456-7890",
                    "555-444-3333", "555-867-5309",
                    "555-555-1212", "781-555-0000" };
            // Create the prepared statement
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO customers (CustID, Last_Name, " +
                            "First_Name, Email, Phone_Number)" +
                            " VALUES(?,?,?,?,?)");
            // Add rows to a batch in a loop. Each iteration adds a
            // new row.

            for (int i = 0; i < firstNames.length; i++) {
                // Add each parameter to the row.
                pstmt.setInt(1, i + 1);
                pstmt.setString(2, lastNames[i]);
                pstmt.setString(3, firstNames[i]);
                pstmt.setString(4, emails[i]);
                pstmt.setString(5, phoneNumbers[i]);
                // Add row to the batch.
                pstmt.addBatch();
            }

            try {
                // Batch is ready, execute it to insert the data
                pstmt.executeBatch();
            } catch (SQLException e) {
                System.out.println("Error message: " + e.getMessage());
                return; // Exit if there was an error
            }finally {
                pstmt.clearParameters();
            }

            // Commit the transaction to close the COPY command
            conn.commit();


            // Print the resulting table.
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT CustID, First_Name, "
                    + "Last_Name FROM customers ORDER BY CustID");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " - "
                        + rs.getString(2).trim() + " "
                        + rs.getString(3).trim());
            }
            // Cleanup
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}