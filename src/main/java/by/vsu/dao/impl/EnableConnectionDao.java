package by.vsu.dao.impl;

import java.sql.Connection;

public abstract class EnableConnectionDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
