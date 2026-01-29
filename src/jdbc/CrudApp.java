package jdbc;

import java.sql.*;
import java.util.Scanner;

public class CrudApp {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(67);
        }

        Statement statement = connection.createStatement();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                    Enter you choice:
                        1. Create table
                        2. Insert data
                        3. Update data
                        4. Delete data
                        5. Display data
                        0. Exit""");

            choice = sc.nextInt();
            switch (choice) {
                case 1 -> createTable(statement);
                case 2 -> insertData(statement);
                case 3 -> updateData(statement);
                case 4 -> deleteData(statement);
                case 5 -> displayData(statement);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Incorrect options selected");
            }
        } while (choice > 0);
    }

    private static void createTable(Statement statement) throws SQLException {
        final String createTableSQL = """
                   CREATE TABLE IF NOT EXISTS Student (
                    ID INT PRIMARY KEY AUTO_INCREMENT,
                    Name VARCHAR(100) NOT NULL,
                    Age INT NOT NULL,
                    Course VARCHAR(100)
                   );
                """.trim();

        statement.executeUpdate(createTableSQL);
        System.out.println("Table Student created");
    }

    private static void insertData(Statement statement) throws SQLException {
        final String insertSQL = """
                   INSERT INTO Student
                   (Name, Age, Course)
                   VALUES
                   ("Bharat", 18, "CS");
                """.trim();

        statement.executeUpdate(insertSQL);
        System.out.println("Inserted data successfully");
    }

    private static void updateData(Statement statement) throws SQLException {
        final String updateSQL = """
                   UPDATE Student
                   SET Name="Archit", Course="Construction"
                   WHERE Name LIKE "Bharat";
                """.trim();

        statement.executeUpdate(updateSQL);
        System.out.println("Updated data successfully");
    }

    private static void deleteData(Statement statement) throws SQLException {
        final String updateSQL = """
                DELETE FROM Student
                WHERE Name IN ("Archit", "Bharat");
                """.trim();

        statement.executeUpdate(updateSQL);
        System.out.println("Updated data successfully");
    }
    private static void displayData(Statement statement) throws SQLException {
        final String selectSQL = """
            SELECT * FROM Student;
            """;

        ResultSet rs = statement.executeQuery(selectSQL);

        System.out.println("\nID | Name | Age | Course");
        System.out.println("--------------------------");

        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("Name");
            int age = rs.getInt("Age");
            String course = rs.getString("Course");

            System.out.println(id + " | " + name + " | " + age + " | " + course);
        }
    }
}