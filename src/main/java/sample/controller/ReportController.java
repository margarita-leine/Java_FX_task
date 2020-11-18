package sample.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import org.hibernate.Transaction;
import sample.dao.OrderDAO;
import sample.model.Order;
import sample.utils.HibernateUtils;

import javax.persistence.Column;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ReportController {
    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private Text actionMessage;
    @FXML
    private Button saveBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button showHiddenBtn;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Double> orderValueColumn;

    @FXML
    private TableColumn<Order, String> customerColumn;


    public void initialize() {
        List<Order> list = orderDAO.getOrderList ();
        ObservableList<Order> data = tableView.getItems();
        data.addAll(list);

        refreshBtn.setOnMouseClicked(r-> {
            data.clear();
            data.addAll(orderDAO.getOrderList());
        });

        deleteBtn.setOnMouseClicked(d-> {
            int indexOfSelected = tableView.getSelectionModel().getFocusedIndex();
            Order selectedOrder = data.get(indexOfSelected);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/confirmation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();

            stage.setTitle("Are you sure?");
            stage.setScene(new Scene(root, 500, 100));

            VBox vBox = (VBox) root.getChildrenUnmodifiable().get(0);
            Text text = (Text) vBox.getChildren().get(0);
            text.setText("Are you sure you want to delete: " + selectedOrder.getCustomer() + "  " + selectedOrder.getProductName());
            HBox hBox = (HBox) vBox.getChildren().get(1);
            Button noBtn = (Button)  hBox.getChildren().get(0);
            Button yesBtn = (Button) hBox.getChildren().get(1);

            noBtn.setOnMouseClicked(no-> {
                stage.hide();

        });
        yesBtn.setOnMouseClicked(yes-> {
            orderDAO.deleteOrder(selectedOrder);
            data.remove(indexOfSelected);
            stage.hide();
            actionMessage.setText("Order " + selectedOrder.getCustomer() + "  " + selectedOrder.getProductName() + "deleted!");
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    });
        tableView.setEditable(true);
        orderValueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        editBtn.setOnMouseClicked(t-> {
            //razreshaem edit na kolonke i taablice, pri nazatiji ENTER, takoi uz komponent
            customerColumn.setEditable(true);
            customerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            tableView.setEditable(true);
        });


        //SDESJ o4enj vazno. U nas obnovleno na grid no ne obnovleno v baze
        //I grid vnutri xranit starie objekti, esli ego otkritj zakritj on starie dannie vozmjot
        //etot kusok koda govorit: VOZMI to sto ja obnovil, i zapishi sebe v grid items
        //Dlja kazdoi kolonki nuzno takuju logiku napisatj.
        //naprimer dlja orderValue budet order.setvalue(commitEvent.getNewValue)
        customerColumn.setOnEditCommit(commitEvent -> {
            commitEvent.getRowValue().setCustomer(commitEvent.getNewValue());
        });

        //Posle izmenenija v baze to ne izmeneno, izmeneno toljko na GRID, poetomu pri nazatiji nuzno kazdij order obnovitj
        //zaodno zapreshaem editable, tak kak dumaem sto redaktirovanie zakonm4ilosj
        saveBtn.setOnMouseClicked(saveEvent -> {
            tableView.setEditable(false);
            customerColumn.setEditable(false);
            tableView.refresh();
            tableView.getItems().forEach(t-> {
                orderDAO.saveOrder(t);
            });
        });

    }
}
