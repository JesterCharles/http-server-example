package Util.interfaces.servicerepo;

import java.util.ArrayList;

/**
 * Generic interface to map methods that EVERY service and repository should have the functionality to complete across
 * every model, including methods to create, read and update.
 * @param <T>
 */
public interface GenericServiceRepo<T> {
    ArrayList<T> findAll();
    T create(T newObject);
    T findByID();
    boolean update(T updatedObject);
}
