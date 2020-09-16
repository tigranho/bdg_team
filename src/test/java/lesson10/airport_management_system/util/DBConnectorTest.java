package lesson10.airport_management_system.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class DBConnectorTest {
    private static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException {
        connection = DBConnector.getConnection();
    }

    @Test
    void getConnection() {
        assertNotNull(connection);
    }
}