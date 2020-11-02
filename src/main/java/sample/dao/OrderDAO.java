package sample.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sample.model.Order;
import sample.utils.HibernateUtils;

import java.util.List;

public class OrderDAO {
    public List<Order> getOrderList() {
        Transaction newTrn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        List<Order> orderList = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(
                "SELECT o from Order as o").list();
        newTrn.commit();
        return orderList;
    }

    public void deleteOrder(Order order) {
        Transaction newTrn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        HibernateUtils.getSessionFactory().getCurrentSession().delete(order);
        newTrn.commit();
    }

}
