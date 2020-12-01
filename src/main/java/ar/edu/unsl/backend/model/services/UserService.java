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

public class UserService extends Service implements Callback<User>
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
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");

        List<User> users = UserOperator.getOperator().findAll(this);

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

    @Override
    public void onResponse(Call<User> call, Response<User> response)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailure(Call<User> call, Throwable t)
    {
        // TODO Auto-generated method stub

    }
}