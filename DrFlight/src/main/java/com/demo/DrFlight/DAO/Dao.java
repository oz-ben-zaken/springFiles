package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Poco.Poco;

import java.util.List;

public interface Dao <T extends Poco> {

    /**
     * T get(long id)
     * @param id
     * @return T (Poco) with matching id from corresponding T table in the database.
     */
    public T get(long id);

    /**
     * List&lt;T> getAll();
     * @return List &lt;T> of all Pocos from corresponding T table in the database.
     */
    public List<T> getAll();

    /**
     * boolean add(T t)
     * Try to add t to corresponding T table in the database.
     * @param t
     * @return True if succeeds or false if not.
     */
    public boolean add(T t);

    /**
     * boolean remove(T t)
     * Try to remove t from corresponding T table in the database.
     * @param t
     * @return True if succeeds or false if not.
     */
    public boolean remove(T t);

    /**
     * boolean update(T t)
     * Try to update t in corresponding T table in the database.
     * @param t
     * @return True if succeeds or false if not.
     */
    public boolean update(T t);

    /**
     * Closing Connection and Statement if either of them is opened.
     */
    public void close();
}
