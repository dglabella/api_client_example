package ar.edu.unsl.frontend.view_controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import java.util.function.Predicate;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import ar.edu.unsl.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import javafx.scene.control.Alert.AlertType;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.services.UserService;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainMenuViewCntlr extends TableViewCntlr
{
    private int USERS_TABLE_NUMBER;

    // ================================= FXML variables =================================
    @FXML private TableView<User> users;

    @FXML private TableColumn<User, Integer> id;
    @FXML private TableColumn<User, Long> dni;
    @FXML private TableColumn<User, String> name;
    @FXML private TableColumn<User, String> lastName;
    @FXML private TableColumn<User, String> email;
    @FXML private TableColumn<User, Long> phoneNumber;

    @FXML private TextField searchField;
    @FXML private ImageView catButton;

    // ================================== FXML methods ==================================
    @FXML
    private void openUserDataButtonPressed()
    {
        if(this.users.getSelectionModel().getSelectedIndex() > -1)
        {

        }
    }

    @FXML
    private void selectUser(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.openUserDataButtonPressed();
        }
    }

    @FXML
    private void newUserButtonPressed()
    {
        this.createStage("User registration form", "userForm", new UserService()).getStage().show();
    }

    // ================================= private methods =================================


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize()
    {

    }

    // ================================= public methods ==================================
    public void init()
    {
        this.manualInitialize();
    }

    @Override
    public void customTableViewInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.users.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<TableColumn> columns = new ArrayList<>();
        columns.add(this.id);
        columns.add(this.dni);
        columns.add(this.name);
        columns.add(this.lastName);
        columns.add(this.email);
        columns.add(this.phoneNumber);

        List<PropertyValueFactory> propertiesValues = new ArrayList<>();
        propertiesValues.add(new PropertyValueFactory<>("id"));
        propertiesValues.add(new PropertyValueFactory<>("dni"));
        propertiesValues.add(new PropertyValueFactory<>("name"));
        propertiesValues.add(new PropertyValueFactory<>("lastName"));
        propertiesValues.add(new PropertyValueFactory<>("email"));
        propertiesValues.add(new PropertyValueFactory<>("phoneNumber"));


        this.registerTable(this.users);
        this.USERS_TABLE_NUMBER = 0; // because is the first table registered.
        
        this.registerColumns(columns);
        this.registerPropertiesValues(propertiesValues);


        //Setting the filter binding to the text field
        this.searchField.textProperty().addListener((obs, oldValue, newValue) -> 
        {
            this.filterTable(this.USERS_TABLE_NUMBER, new Predicate<User>()
            {
                @Override
                public boolean test(User user)
                {
                    boolean ret;
                    if (newValue.isBlank() || String.valueOf(user.getId()).contains(newValue) ||
                        (user.getDni() != null && String.valueOf(user.getDni()).contains(newValue)) ||
                        user.getName().contains(newValue.toUpperCase()) ||
                        String.valueOf(user.getLastName()).contains(newValue.toUpperCase()) ||
                        (user.getEmail() != null && user.getEmail().contains(newValue)) ||
                        (user.getPhoneNumber() != null && String.valueOf(user.getPhoneNumber()).contains(newValue)))
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
                    }
                    return ret;
                }
            });
        });
    }

    // ================================= service subscriber methods =================================
}