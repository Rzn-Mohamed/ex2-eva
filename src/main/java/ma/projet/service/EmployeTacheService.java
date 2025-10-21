package ma.projet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.dao.IDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeTacheService implements IDao<EmployeTache> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EmployeTache create(EmployeTache o) {
        // Merge the related entities to avoid detached entity exception
        if (o.getEmploye() != null && o.getEmploye().getId() != 0) {
            o.setEmploye(entityManager.merge(o.getEmploye()));
        }
        if (o.getTache() != null && o.getTache().getId() != 0) {
            o.setTache(entityManager.merge(o.getTache()));
        }
        entityManager.persist(o);
        return o;
    }

    @Override
    public EmployeTache update(EmployeTache o) {
        return entityManager.merge(o);
    }

    @Override
    public void delete(EmployeTache o) {
        entityManager.remove(entityManager.contains(o) ? o : entityManager.merge(o));
    }

    @Override
    public EmployeTache findById(int id) {
        return null;
    }

    public EmployeTache findById(EmployeTachePK id) {
        return entityManager.find(EmployeTache.class, id);
    }

    @Override
    public List<EmployeTache> findAll() {
        return entityManager.createQuery("SELECT et FROM EmployeTache et", EmployeTache.class).getResultList();
    }
}
