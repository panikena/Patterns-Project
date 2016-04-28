package com.panikena.DAO;

import com.panikena.Models.Owner;
import com.panikena.Models.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 28.04.2016.
 */
public class HibernateOwnerDAO implements OwnerDAO {

    Session session;

    public HibernateOwnerDAO() {
        session = HibernateDAOFactory.session;
    }

    @Override
    public Owner create(Owner owner) throws SQLException {
        session.beginTransaction();
        session.save(owner);
        session.getTransaction().commit();
        return owner;
    }

    @Override
    public Owner read(String name) throws SQLException {
        session.beginTransaction();
        List<Owner> list = session.createCriteria(Owner.class).add(Restrictions.like("name", name)).list();
        session.getTransaction().commit();
        return list.get(0);
    }

    @Override
    public void update(Owner owner) throws SQLException {
        session.beginTransaction();
        session.update(owner);
        session.getTransaction().commit();
    }

    @Override
    public boolean delete(Owner owner) throws SQLException {
        session.beginTransaction();
        session.delete(owner);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Owner> getAll() throws SQLException {
        session.beginTransaction();
        List<Owner> list = session.createCriteria(Owner.class).list();
        session.getTransaction().commit();
        return list;
    }
}
