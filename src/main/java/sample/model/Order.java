package sample.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "order_list")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "customer")
    private String customer;
    @Column (name = "product_list")
    private String productName;
    @Column (name = "order_date")
    private Date oderDate;
    @Column(name = "order_value")
    private Double orderValue;
    @Column(name = "registration_date")
    private Date registrDate;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column (name= "sent_date")
    private Date sentDate;

    public Order() {
    }

    public Order(long id, String customer, String productName, Date oderDate, Double orderValue, Date registrDate, Date paymentDate, Date sentDate) {
        this.id = id;
        this.customer = customer;
        this.productName = productName;
        this.oderDate = oderDate;
        this.orderValue = orderValue;
        this.registrDate = registrDate;
        this.paymentDate = paymentDate;
        this.sentDate = sentDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    public Date getRegistrDate() {
        return registrDate;
    }

    public void setRegistrDate(Date registrDate) {
        this.registrDate = registrDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}
