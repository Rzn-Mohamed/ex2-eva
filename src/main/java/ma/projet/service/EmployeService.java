package ma.projet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeService implements IDao<Employe> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employe create(Employe o) {
        entityManager.persist(o);
        return o;
    }

    @Override
    public Employe update(Employe o) {
        return entityManager.merge(o);
    }

    @Override
    public void delete(Employe o) {
        entityManager.remove(entityManager.contains(o) ? o : entityManager.merge(o));
    }

    @Override
    public Employe findById(int id) {
        return entityManager.find(Employe.class, id);
    }

    @Override
    public List<Employe> findAll() {
        return entityManager.createQuery("SELECT e FROM Employe e", Employe.class).getResultList();
    }

    public List<Tache> getTachesRealisees(int employeId) {
        return entityManager.createQuery(
                "SELECT et.tache FROM EmployeTache et WHERE et.employe.id = :employeId", Tache.class)
                .setParameter("employeId", employeId)
                .getResultList();
    }

    public List<Projet> getProjetsGeres(int employeId) {
        return entityManager.createQuery(
                "SELECT DISTINCT t.projet FROM Tache t JOIN t.employeTaches et " +
                "WHERE et.employe.id = :employeId", Projet.class)
                .setParameter("employeId", employeId)
                .getResultList();
    }
}
