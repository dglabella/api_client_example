package ar.edu.unsl.backend.model.services;

import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import ar.edu.unsl.backend.util.CustomAlert;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.persistence.UserOperator;
import ar.edu.unsl.frontend.service_subscribers.UserServiceSubscriber;

public class UserService extends Service
{
    private boolean allFieldsOk(String id, String name, String userName, String website, String email, String phone)
    {
        boolean ret = false;

        if(this.getExpressionChecker().onlyNumbers(id, false) && this.getExpressionChecker().composedName(name) &&
            this.getExpressionChecker().userName(userName) && this.getExpressionChecker().isEmail(email, false) &&
            this.getExpressionChecker().onlyNumbers(phone, true))
            ret = true;

        return ret;
    }
    
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

    public void searchUser(Integer id) throws Exception
    {
        UserOperator.getOperator(this).find(id);
    }

    public void registerUser(String id, String name, String userName, String website, String email, String phone) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");

        if(allFieldsOk(id, name, userName, website, email, phone))
        {
            UserService userService = this;
            User newUser = new User();
            newUser.setId(Integer.parseInt(id));
            newUser.setName(name);
            newUser.setUserName(userName);
            newUser.setWebsite(website);
            newUser.setEmail(email);
            newUser.setPhone(phone);

            Task<Void> task = new Task<>()
            {
                @Override
                protected Void call() throws Exception
                {
                    User user = UserOperator.getOperator(userService).insert(newUser);

                    getServiceSubscriber().closeProcessIsWorking(customAlert);

                    if(user != null)
                    {
                        ((UserServiceSubscriber)getServiceSubscriber()).showUser(user);
                    }

                    return null;
                }
            };
            Platform.runLater(task);
        }
        else
        {
            getServiceSubscriber().closeProcessIsWorking(customAlert);
            this.getServiceSubscriber().showError("Some fields are in the wrong format or need to be completed");
        }
	}
}