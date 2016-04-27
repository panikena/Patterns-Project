package com.panikena.DAO;

import com.panikena.Models.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 27.04.2016.
 */
public class HibernateUserDAO implements UserDAO {

    Session session;

    public HibernateUserDAO() {
        session = HibernateDAOFactory.session;
    }

    @Override
    public User create(User user) throws SQLException {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public User read(String name) throws SQLException {
        session.beginTransaction();
        List<User> list = session.createCriteria(User.class).add(Restrictions.like("name", name)).list();
        session.getTransaction().commit();
        return list.get(0);
    }

    @Override
    public void update(User user) throws SQLException {
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    @Override
    public boolean delete(User user) throws SQLException {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException {
        session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        return list;
    }
}
