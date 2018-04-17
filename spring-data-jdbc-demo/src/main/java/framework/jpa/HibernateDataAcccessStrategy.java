package framework.jpa;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;
import org.springframework.data.mapping.PropertyPath;

public class HibernateDataAcccessStrategy implements DataAccessStrategy {

//    private EntityManager em;
    private SessionFactory sessionFactory;

//    public HibernateDataAcccessStrategy(EntityManager em) {
//        this.em = em;
//    }
    public HibernateDataAcccessStrategy(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> void insert(T instance, Class<T> domainType, Map<String, Object> additionalParameters) {

        Session session = currentSession();
        session.save(instance);
        session.flush();
    }

    @Override
    public <T> void update(T instance, Class<T> domainType) {
        
        Session session = currentSession();
        session.merge(instance);
        session.flush();
    }

    @Override
    public void delete(Object id, Class<?> domainType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Object rootId, PropertyPath propertyPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void deleteAll(Class<T> domainType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void deleteAll(PropertyPath propertyPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public long count(Class<?> domainType) {
        // TODO Auto-generated method stub
        //        return 0;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T findById(Object id, Class<T> domainType) {

        return currentSession().find(domainType, id);
    }

    @Override
    public <T> Iterable<T> findAll(Class<T> domainType) {
        //        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Iterable<T> findAllById(Iterable<?> ids, Class<T> domainType) {
        // TODO Auto-generated method stub
        //        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Iterable<T> findAllByProperty(Object rootId, JdbcPersistentProperty property) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> boolean existsById(Object id, Class<T> domainType) {
        // TODO Auto-generated method stub
        //        return false;
        throw new UnsupportedOperationException();
    }

}
