package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sample.dao.OrderDAO;
import sample.model.Order;

public class ReportController {
    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private TableView<Order> tableView;
    @FXML
    private Button editBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button showHidenBtn;




}
