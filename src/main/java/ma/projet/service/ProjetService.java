package ma.projet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjetService implements IDao<Projet> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Projet create(Projet o) {
        entityManager.persist(o);
        return o;
    }

    @Override
    public Projet update(Projet o) {
        return entityManager.merge(o);
    }

    @Override
    public void delete(Projet o) {
        entityManager.remove(entityManager.contains(o) ? o : entityManager.merge(o));
    }

    @Override
    public Projet findById(int id) {
        return entityManager.find(Projet.class, id);
    }

    @Override
    public List<Projet> findAll() {
        return entityManager.createQuery("SELECT p FROM Projet p", Projet.class).getResultList();
    }

    public List<Tache> getTachesPlanifiees(int projetId) {
        return entityManager.createQuery(
                "SELECT t FROM Tache t WHERE t.projet.id = :projetId", Tache.class)
                .setParameter("projetId", projetId)
                .getResultList();
    }

    public List<Object[]> getTachesRealisees(int projetId) {
        return entityManager.createQuery(
                "SELECT t, et FROM Tache t JOIN t.employeTaches et WHERE t.projet.id = :projetId", 
                Object[].class)
                .setParameter("projetId", projetId)
                .getResultList();
    }
}
