package ma.projet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TacheService implements IDao<Tache> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tache create(Tache o) {
        entityManager.persist(o);
        return o;
    }

    @Override
    public Tache update(Tache o) {
        return entityManager.merge(o);
    }

    @Override
    public void delete(Tache o) {
        entityManager.remove(entityManager.contains(o) ? o : entityManager.merge(o));
    }

    @Override
    public Tache findById(int id) {
        return entityManager.find(Tache.class, id);
    }

    @Override
    public List<Tache> findAll() {
        return entityManager.createQuery("SELECT t FROM Tache t", Tache.class).getResultList();
    }

    public List<Tache> getTachesPrixSuperieur1000() {
        return entityManager.createNamedQuery("Tache.findByPrixSuperieur", Tache.class)
                .setParameter("prix", 1000.0)
                .getResultList();
    }

    public List<Tache> getTachesRealiseesByDates(Date dateDebut, Date dateFin) {
        return entityManager.createQuery(
                "SELECT DISTINCT t FROM Tache t JOIN t.employeTaches et " +
                "WHERE et.dateDebutReelle BETWEEN :dateDebut AND :dateFin " +
                "OR et.dateFinReelle BETWEEN :dateDebut AND :dateFin", Tache.class)
                .setParameter("dateDebut", dateDebut)
                .setParameter("dateFin", dateFin)
                .getResultList();
    }
}
