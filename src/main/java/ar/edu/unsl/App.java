package ar.edu.unsl;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import ar.edu.unsl.backend.model.services.Service;
import ar.edu.unsl.backend.model.services.UserService;
import ar.edu.unsl.backend.util.ExpressionChecker;
import ar.edu.unsl.frontend.view_controllers.ViewCntlr;

public class App extends Application
{
    public static final String GUIs_LOCATION = App.class.getResource("") + "../../../frontend/GUIs/";
    public static final String FILE_EXTENSION = ".fxml";
    public static final String API_HOSTNAME = "https://gorest.co.in/public-api/";

    @Override
    public void start(final Stage stage) throws IOException
    {
        String fileName = "mainMenu";

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(GUIs_LOCATION + fileName + FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        ViewCntlr viewCtrller = fxmlLoader.getController();

        Service userService = new UserService();
        viewCtrller.addService(userService);
        userService.setServiceSubscriber(viewCtrller);
        userService.setExpressionChecker(ExpressionChecker.getExpressionChecker());

        stage.show();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
