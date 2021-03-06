package sample.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sample.model.Order;
import sample.utils.HibernateUtils;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class OrderDAO {
    public List<Order> getOrderList() {
        Session session = HibernateUtils.getSessionFactory().openSession();
            String selectedOrder = "SELECT o from Order as o";
            Query selectOrderQuery = (Query) session.createQuery(selectedOrder);
            List<Order> result = selectOrderQuery.list();
            session.close();
            return result;

//        Transaction newTrn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
//        List<Order> orderList = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(
//                "SELECT o from Order as o").list();
//        newTrn.commit();
//        return orderList;
    }

    public void deleteOrder(Order order) {
        Transaction newTrn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        HibernateUtils.getSessionFactory().getCurrentSession().delete(order);
        newTrn.commit();
    }


    public void saveOrder(Order order) {
        Transaction newTrn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        //Metod merge stobi on soobrozil esli ID 2 to etot imeno tot object sto v baze s ID 2.
        //Povtorite raznicu mezdu persist / save / merge povtorite sto takoe Hibernate Session
        //na praktike vezde v podobnom ispoljzujut v osnovom merge
        HibernateUtils.getSessionFactory().getCurrentSession().merge(order);
        newTrn.commit();
    }

}
