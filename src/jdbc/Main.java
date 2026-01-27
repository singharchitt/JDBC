package jdbc;

import java.sql.*;


public class Main{
    public static void main(String[] args)throws ClassNotFoundException, SQLException{
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
            System.out.println("Connected to database successfully");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(67);
        }
        final String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Employees (
                ID INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(100) NOT NULL,
                Position VARCHAR(100),
                Salary DECIMAL(10, 2) NOT NULL,
                JoinDate DATE);
                """.trim();
        Statement statement = connection.createStatement();
        statement.execute(createTableSQL);
    }
}
