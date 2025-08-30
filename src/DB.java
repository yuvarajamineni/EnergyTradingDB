import java.sql.*;

public class DB {
    private static final String URL =
    "jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "energyuser";
    private static final String PASS = "123";



    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQL Server JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
