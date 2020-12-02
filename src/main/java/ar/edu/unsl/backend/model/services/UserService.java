package ar.edu.unsl.backend.model.services;

import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import ar.edu.unsl.backend.util.CustomAlert;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.persistence.UserOperator;
import ar.edu.unsl.frontend.service_subscribers.UserServiceSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService extends Service implements Callback<List<User>>
{
    /*
    public void searchUsers() throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");
        
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                List<User> users = UserOperator.getOperator().findAll();

                getServiceSubscriber().closeProcessIsWorking(customAlert);
                if(users != null)
                {
                    ((UserServiceSubscriber)getServiceSubscriber()).showUsers(users);
                }
                else
                {
                    getServiceSubscriber().showNoResult("No users registered");
                }

                return null;
            }
        };
        
        Platform.runLater(task);
    }
    */

    public void searchUsers() throws Exception
    {
        UserOperator.getOperator(this).findAll();
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response)
    {
        if(response.isSuccessful())
        {
            ((UserServiceSubscriber)this.getServiceSubscriber()).showUsers(response.body());
        }
        else
        {
            this.getServiceSubscriber().showError("Error has occurred", response.errorBody().toString(), new Exception("error response"));
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t)
    {
        this.getServiceSubscriber().showError("User services fail on retrieve a response: "+t.getLocalizedMessage());
    }
}