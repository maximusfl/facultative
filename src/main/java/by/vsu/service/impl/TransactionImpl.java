package by.vsu.service.impl;

import by.vsu.service.Transaction;
import by.vsu.service.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void start() throws TransactionException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
