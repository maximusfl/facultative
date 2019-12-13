package by.vsu.dao;

public interface Dao<T> {
    T read(Long id) throws DaoException;

    Long create(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(Long id) throws DaoException;
}
