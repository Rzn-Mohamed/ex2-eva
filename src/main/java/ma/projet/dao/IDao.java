package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    T create(T o);
    T update(T o);
    void delete(T o);
    T findById(int id);
    List<T> findAll();
}
