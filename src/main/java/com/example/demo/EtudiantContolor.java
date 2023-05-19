package com.example.demo;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;

public class EtudiantContolor implements Initializable{
    EtudiantService etudiantService=new EtudiantService();
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLastName;
    @FXML
    private RadioButton radioF;

    @FXML
    private Group groupRadio;
    @FXML
    private RadioButton radioM;
    @FXML
    private Button addEtd;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    @FXML
    private TableView<Etudiant> table;
    @FXML
    private TableColumn<Etudiant, String> idCol;
    @FXML
    private TableColumn<Etudiant, String> nameCol;
    @FXML
    private TableColumn<Etudiant, String> lastNameCol;
    @FXML
    private TableColumn<Etudiant, String> sexeCol;
    @FXML
    private TableColumn<Etudiant, String> filiereCol;
    @FXML
    private ComboBox combo;
    @FXML
    void addEtd(ActionEvent event) {
        Window owner = addEtd.getScene().getWindow();
        String selectedCombo = combo.getSelectionModel().getSelectedItem().toString();
        String sex;
        if (radioM.isSelected()) {
            sex="Homme";
            radioM.setDisable(false);
        }
        else {
            sex="Femme";
        }
        if (etudiantService.create(new Etudiant(tfName.getText(),tfLastName.getText(),sex,selectedCombo))) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student added successfully!");
            alert.initOwner(owner);
            alert.show();
        }
    }

    //private EtudiantM etudiantM;
    private ObservableList<Etudiant> etudiantList;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub


        etudiantList = FXCollections.observableArrayList(etudiantService.findAll());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        filiereCol.setCellValueFactory(new PropertyValueFactory<>("filiere"));
        table.setItems(etudiantList);

        ObservableList<String> list=FXCollections.observableArrayList("dsi","rsi","sem","dm");
        combo.setItems(list);


        table.setEditable(true);
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getNom());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getPrenom());
        sexeCol.setCellValueFactory(cellData -> cellData.getValue().getSex());
        filiereCol.setCellValueFactory(cellData -> cellData.getValue().getFiliere());

        table.setItems(etudiantList);

        // make column of table editable
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexeCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

}
