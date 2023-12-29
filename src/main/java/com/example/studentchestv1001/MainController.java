package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
    @FXML
    private Label lblCardName;
    @FXML
    private Label lblCardNumber;
    @FXML
    private Label lblMoney;
    private double balance;
    private int time = 0;

    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();

        String query = "select first_name, last_name, card_number, balance from account a join customer c on a.idcustomer = c.idcustomer join card ca on ca.idaccount = a.idaccount where c.idcustomer = " + login.getId();
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                lblCardName.setText(resultSet.getString(1) + " " + resultSet.getString(2));
                String number = resultSet.getString(3);
                number = maskCardNumber(number);
                lblCardNumber.setText(number);
                balance = resultSet.getDouble(4);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void seeBalance(ActionEvent event) throws IOException{
        if(time%2==0){
            lblMoney.setText(String.valueOf(balance));
        }
        else
            lblMoney.setText("$$$");
        time++;
    }

    private String maskCardNumber(String input){
        if(input != null && input.length() >=4){
            String lastFour = input.substring(input.length() - 4);
            return "****"+lastFour;
        }
        else
            return input;
    }

    public void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDetails(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("details-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHistory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("history-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIBAN(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("IBAN-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransfer(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("transfer-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWithdraw(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("withdraw-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("card-settings-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
