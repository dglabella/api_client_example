package ar.edu.unsl;

import javafx.application.Application;

public class App extends Application
{
    @Override
    public void start(final Stage stage) throws IOException
    {
        //new DetailFileInterpreter(new FileChooser().showOpenDialog(null)).insertClientFromDetailFile();

        String fileName = "mainMenu";

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(GUIs_LOCATION + fileName + FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        
        ViewCntlr viewCtrller = fxmlLoader.getController();

        Service prefClientService= new PreferentialClientService();
        viewCtrller.addService(prefClientService);
        prefClientService.setServiceSubscriber(viewCtrller);
        prefClientService.setExpressionChecker(ExpressionChecker.getExpressionChecker());

        Service campaignService = new CampaignService();
        viewCtrller.addService(campaignService);
        campaignService.setServiceSubscriber(viewCtrller);
        campaignService.setExpressionChecker(ExpressionChecker.getExpressionChecker());

        stage.show();
        ((MainMenuViewCntlr)viewCtrller).init();
    }

    public static void main( String[] args )
    {
        launch();
    }
}
