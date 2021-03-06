package ar.edu.unsl.backend.model.services;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import ar.edu.unsl.backend.util.CustomAlert;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.interfaces.IUserOperator;
import ar.edu.unsl.backend.model.persistence.UserOperatorApache;
import ar.edu.unsl.backend.model.persistence.UserOperatorRetrofit;
import ar.edu.unsl.frontend.service_subscribers.UserServiceSubscriber;

public class UserService extends Service
{
    private IUserOperator operator;

    public UserService()
    {
        //this.operator = new UserOperatorRetrofit(this);
        this.operator = new UserOperatorApache(this);
    }

    private boolean allFieldsOk(String name, String userName, String website, String email, String phone)
    {
        boolean ret = false;

        if(this.getExpressionChecker().composedName(name) && this.getExpressionChecker().userName(userName) &&
            this.getExpressionChecker().isEmail(email, false) && this.getExpressionChecker().onlyNumbers(phone, true))
            {
                ret = true;
            }

        return ret;
    }
    
    
    public void searchUsers() throws Exception
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");
        
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                List<User> users = operator.findAll();

                //getServiceSubscriber().closeProcessIsWorking(customAlert);
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
        
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
    }
    

    
    /*
    public void searchUsers() throws Exception
    {
        List<User> users = this.operator.findAll();
        if(users != null)
        {
            ((UserServiceSubscriber)this.getServiceSubscriber()).showUsers(users);
        }
    }
    */
    
    

    public void searchUser(String id) throws Exception
    {
        if(this.getExpressionChecker().onlyNumbers(id, false))
        {
            User user = this.operator.find(Integer.parseInt(id));
            
            if(user != null)
            {
                ((UserServiceSubscriber)this.getServiceSubscriber()).showUser(user);
            }
            
        }
    }

    public void registerUser(String name, String userName, String website, String email, String phone) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");

        if(allFieldsOk(name, userName, website, email, phone))
        {
            User newUser = new User();
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
                    User user = operator.insert(newUser);

                    getServiceSubscriber().closeProcessIsWorking(customAlert);

                    if(user != null)
                    {
                        ((UserServiceSubscriber)getServiceSubscriber()).showUser(user);
                    }

                    return null;
                }
            };
            
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
        }
        else
        {
            getServiceSubscriber().closeProcessIsWorking(customAlert);
            this.getServiceSubscriber().showError("Some fields are in the wrong format or need to be completed");
        }
	}
}