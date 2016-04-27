
package com.panikena.DAO;

import com.panikena.Models.Car;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem on 26.04.2016.
 */
public class HibernateCarDAO implements CarDAO {

    Session session;

    HibernateCarDAO(){
        session = HibernateDAOFactory.session;
    }


    @Override
    public Car create(Car car) throws SQLException {
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
       // session.close();
        return car;
    }

    @Override
    public Car read(String VIN) throws SQLException {
        session.beginTransaction();
        List<Car> list = session.createCriteria(VIN).list();
        session.getTransaction().commit();
       // session.close();
        return list.get(0);
    }

    @Override
    public void update(Car car) throws SQLException {
        session.beginTransaction();
        session.update(car);
        session.getTransaction().commit();
      //  session.close();
    }

    @Override
    public boolean delete(Car car) throws SQLException {
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
      //  session.close();
        return true;
    }

    @Override
    public List<Car> getAll() throws SQLException {
        session.beginTransaction();
        List<Car> list = session.createCriteria(Car.class).list();
        session.getTransaction().commit();
       // session.close();
        return list;
    }
}
