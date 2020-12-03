package ar.edu.unsl.backend.model.persistence;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import ar.edu.unsl.App;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import ar.edu.unsl.backend.model.entities.User;
import retrofit2.converter.gson.GsonConverterFactory;
import ar.edu.unsl.backend.model.services.UserService;
import ar.edu.unsl.backend.model.interfaces.IUserOperator;
import ar.edu.unsl.backend.model.repositories.UserRepository;
import ar.edu.unsl.frontend.service_subscribers.UserServiceSubscriber;
import javafx.application.Platform;

public class UserOperator implements IUserOperator
{
    private final static int REQUEST_CONNECT_TIMEOUT_TOLERANCE = 20;
    private final static int REQUEST_READ_TIMEOUT_TOLERANCE = 5;
    private final static int REQUEST_WRITE_TIMEOUT_TOLERANCE = 5;

    public final static String ID = "id";
    public final static String RESOURCE = "/users";
    public final static String SINGLE_RESOURCE = RESOURCE + "/{" + ID + "}";

    static UserOperator operator;

    private UserService userService;
    private UserRepository userRepository;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public UserOperator(UserService userService)
    {
        this.userService = userService;

        // HttpClient and Rest Client can be inyected for more decoupling
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_CONNECT_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .readTimeout(REQUEST_READ_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_WRITE_TIMEOUT_TOLERANCE, TimeUnit.SECONDS).build();

        this.retrofit = new Retrofit.Builder().baseUrl(App.API_HOSTNAME).client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        this.userRepository = this.retrofit.create(UserRepository.class);
    }

    @Override
    public User insert(User user) throws Exception
    {
        User ret = null;
        Response<User> response = null;
        try
        {
            response = this.userRepository.postUser(user).execute();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }

        if(response != null && response.isSuccessful())
        {
            ret = response.body();
        }
        else
        {
            this.userService.getServiceSubscriber().showError("User registration error. Body:\n"+response.errorBody().toString());
        }
        
        return ret;
    }

    @Override
    public List<User> insertMany(List<User> list) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User update(User entity) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() throws Exception
    {
        this.userRepository.findAll().enqueue
        (
            new Callback<List<User>>()
            {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response)
                {
                    if(response.isSuccessful())
                    {
                        ((UserServiceSubscriber)userService.getServiceSubscriber()).showUsers(response.body());
                    }
                    else
                    {
                        userService.getServiceSubscriber().showError("Cannot obtain a users list", response.errorBody().toString(), new Exception("Error response"));
                    }
                };
                
                @Override
                public void onFailure(Call<List<User>> call, Throwable throwable)
                {
                    userService.getServiceSubscriber().showError("Find all user request fail", null, new Exception(throwable));
                };
            }
        );
        return null;
    }

    @Override
    public User find(Integer id) throws Exception
    {
        this.userRepository.find(id).enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                if(response.isSuccessful())
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ((UserServiceSubscriber)userService.getServiceSubscriber()).showUser(response.body());
                        } 
                    });
                }
                else
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            userService.getServiceSubscriber().showError("Cannot obtain user "+id, response.errorBody().toString(), new Exception("Error response"));
                        } 
                    });
                }
            };
            
            @Override
            public void onFailure(Call<User> call, Throwable throwable)
            {
                userService.getServiceSubscriber().showError("find user request fail", null, new Exception(throwable));
            };
        });
        return null;
    }

    @Override
    public User delete(Integer id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}