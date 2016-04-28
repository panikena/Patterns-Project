package com.panikena.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by Artem on 26.04.2016.
 */
public class HibernateDAOFactory extends DAOFactory {

    ServiceRegistry reg;
    SessionFactory factory;
    public static Session session;

    public HibernateDAOFactory(){

        Configuration configuration = new Configuration();
        configuration.configure();

        session = configuration.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry()).openSession();
//
//        reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//        factory = new MetadataSources(reg).buildMetadata().buildSessionFactory();
//        session  = factory.openSession();
    }

    @Override
    public CarDAO getCarDAO() {
        return new HibernateCarDAO();
    }

    @Override
    public UserDAO getUserDAO(){ return new HibernateUserDAO(); }

    @Override
    public OwnerDAO getOwnerDAO(){ return  new HibernateOwnerDAO(); }


}
