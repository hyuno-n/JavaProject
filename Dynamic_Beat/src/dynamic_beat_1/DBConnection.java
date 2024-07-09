package dynamic_beat_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rhythmgame?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "3792";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String[] rankings = new String[10];

    public DBConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    public void insertData(String uname, int score) {
        final String SQL_INSERT = "INSERT INTO ranking (uname, score) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, uname);
            ps.setInt(2, score);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            System.err.println("Insert data error: " + e.getMessage());
        }
    }

    public String[] selectData() {
        final String SQL_SELECT = "SELECT uname, score, RANK() OVER (ORDER BY score DESC) as rank FROM ranking LIMIT 10";
        try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()) {

            int i = 0;
            while (rs.next() && i < 10) {
                String uname = rs.getString("uname");
                int score = rs.getInt("score");
                int rank = rs.getInt("rank");
                rankings[i] = rank + "     " + uname + "     " + score;
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Select data error: " + e.getMessage());
        }
        return rankings;
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
