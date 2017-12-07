package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vitaly on 19.11.2017.
 */
public interface GenericDAO<V, ID> {

    V getById(ID id) throws SQLException;
    V getByName(String name) throws SQLException;
    List<V> getAll() throws SQLException;
    Long save(V val) throws SQLException;
    void update(V val) throws SQLException;
    void delete(V val) throws SQLException;
}
