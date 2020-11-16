package sample.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Transaction;
import sample.dao.OrderDAO;
import sample.utils.HibernateUtils;
import sample.utils.ValidationUtils;
import sample.model.Order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Controller {
    @FXML
    private Text actionMessage;
    @FXML
    private Button orderBtn;
    @FXML
    private Button reportBtn;
    @FXML
    private HBox orderHbox;
    @FXML
    private Text dateTXT;
    @FXML
    private Text orderDateTXT;
    @FXML
    private Text nameSurnameTXT;
    @FXML
    private Text productListTXT;
    @FXML
    private TextField nameSurnameField;
    @FXML
    private TextField productListField;
    @FXML
    private Button submitBtn;

    private ValidationUtils validationUtils = new ValidationUtils();

    @FXML
    void initialize() {

        orderBtn.setOnMouseClicked(t-> {
            orderHbox.setVisible(true);
            submitBtn.setVisible(true);
            orderDateTXT.setText(new Date().toString());
        });
        submitBtn.setOnMouseClicked(s-> {
            Order order1 = new Order();
            order1.setOderDate((new Date()));
            order1.setCustomer(nameSurnameField.getText());
            order1.setProductName(productListField.getText());
            if (validationUtils.isEmptyOrNull(nameSurnameField.getText())
            && validationUtils.isEmptyOrNull(productListField.getText())) {
                Transaction trn = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
                Long id = (Long) HibernateUtils.getSessionFactory().getCurrentSession().save(order1);
                trn.commit();
                nameSurnameField.setText("");
                productListField.setText("");
                actionMessage.setText("Order saved with id:" + id);
            } else {
                actionMessage.setText("Invalid entry! \n Please fill empty fields!");
            }
        });
        reportBtn.setOnMouseClicked(r-> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/createReport.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Order List");
            stage.setScene(new Scene(root, 1000, 600));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orderList = orderDAO.getOrderList();



        });

    }
}
