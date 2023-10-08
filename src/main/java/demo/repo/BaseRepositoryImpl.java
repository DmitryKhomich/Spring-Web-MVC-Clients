package demo.repo;

import demo.entity.Client;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseRepositoryImpl implements BaseRepository<Client>{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Client client) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(client);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> fetchAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Client fetchById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Client.class, id);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Client product = session.byId(Client.class).load(id);
        session.remove(product);
    }
}
