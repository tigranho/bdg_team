package updatedjdbctask.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

  private final String DB_URL;
  private final String DB_USER;
  private final String DB_PASSWORD;

  private DatabaseConnectionFactory() {
    this.DB_USER = Settings.getInstance().getString("db.user");
    this.DB_PASSWORD = Settings.getInstance().getString("db.password");
    this.DB_URL = Settings.getInstance().getString("db.url");
    try {
      Class.forName(Settings.getInstance().getString("db.driver"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Connection getConnection(boolean autoCommit) throws SQLException {
    Connection connection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
    connection.setAutoCommit(autoCommit);
    return connection;
  }

  public Connection getConnection() throws SQLException {
    return this.getConnection(true);
  }

  public static DatabaseConnectionFactory getInstance() {
    return DatabaseConnectionFactoryInstanceCreator.connectionFactory;
  }

  private static class DatabaseConnectionFactoryInstanceCreator {
    private static DatabaseConnectionFactory connectionFactory = new DatabaseConnectionFactory();
  }
}
