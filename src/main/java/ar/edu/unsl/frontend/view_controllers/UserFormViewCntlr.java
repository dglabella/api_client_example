package ar.edu.unsl.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

public class UserFormViewCntlr extends ViewCntlr
{
    // ================================= FXML variables =================================
    @FXML private TextField id;
    @FXML private Label invalidId;
    @FXML private Label invalidName;
    @FXML private TextField name;
    @FXML private Label invalidUserName;
    @FXML private TextField userName;
    @FXML private Label invalidWebsite;
    @FXML private TextField website;
    @FXML private DatePicker birthdate;
    @FXML private TextField email;
    @FXML private Label invalidEmail;
    @FXML private TextField phone;
    @FXML private Label invalidPhone;
    @FXML private Label invalidLeaderId;

    // ================================= FXML methods =================================
    @FXML
    private void idCheck()
    {
        if(this.getExpressionChecker().onlyNumbers(this.id.getText(), false))
        {
            this.invalidId.setVisible(false);
        }
        else
        {
            this.invalidId.setText("Dato invalido");
            this.invalidId.setVisible(true);
        }
    }

    @FXML
    private void dniCheck()
    {
        if(this.getExpressionChecker().onlyNumbers(this.dni.getText(), true))
        {
            this.invalidDni.setVisible(false);
        }
        else
        {
            this.invalidDni.setText("Dato invalido");
            this.invalidDni.setVisible(true);
        }
    }

    @FXML
    private void nameCheck()
    {
        if(this.getExpressionChecker().composedName(this.name.getText()))
        {
            this.invalidName.setVisible(false);
        }
        else
        {
            this.invalidName.setText("Dato invalido");
            this.invalidName.setVisible(true);
        }  
    }

    @FXML
    private void lastNameCheck()
    {
        if(this.getExpressionChecker().composedName(this.lastName.getText()))
        {
            this.invalidLastName.setVisible(false);
        }
        else
        {
            this.invalidLastName.setText("Dato invalido");
            this.invalidLastName.setVisible(true);
        }
    }

    @FXML
    private void emailCheck()
    {
        if(this.getExpressionChecker().isEmail(this.email.getText(), true))
        {
            this.invalidEmail.setVisible(false);
        }
        else
        {
            this.invalidEmail.setText("Dato invalido");
            this.invalidEmail.setVisible(true);
        }  
    }

    @FXML
    private void phoneNumberCheck()
    {
        if(this.getExpressionChecker().onlyNumbers(this.phoneNumber.getText(), true))
        {
            this.invalidPhoneNumber.setVisible(false);
        }
        else
        {
            this.invalidPhoneNumber.setText("Dato invalido");
            this.invalidPhoneNumber.setVisible(true);
        }  
    }

    @FXML
    private void registerButtonPressed() throws Exception
    {

    }
    // ================================= private methods =================================


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {
        
    }

    // ================================= public methods ==================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub

    }

    // ================================= service subscriber methods ==================================
}