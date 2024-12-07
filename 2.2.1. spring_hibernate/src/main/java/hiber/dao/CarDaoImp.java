package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findUserbySeriesAndModel(String model, int series) {
        String hql = "from Car where model = :model and series = :series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("series", series);
        query.setParameter("model", model);

        Car car = (Car) query.uniqueResult();
        return car.getUser();
    }
}
