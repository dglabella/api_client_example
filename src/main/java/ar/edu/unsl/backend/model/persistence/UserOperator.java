package ar.edu.unsl.backend.model.persistence;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.xml.stream.FactoryConfigurationError;
import ar.edu.unsl.App;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.interfaces.IUserOperator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserOperator implements IUserOperator
{
    private final static int REQUEST_CONNECT_TIMEOUT_TOLERANCE = 20;
    private final static int REQUEST_READ_TIMEOUT_TOLERANCE = 5;
    private final static int REQUEST_WRITE_TIMEOUT_TOLERANCE = 5;

    public final static String ID = "id";
    public final static String RESOURCE = "users";
    public final static String SINGLE_RESOURCE = RESOURCE+"/{"+ID+"}";

    private static UserOperator operator;

    private UserOperator()
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_CONNECT_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .readTimeout(REQUEST_READ_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_WRITE_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.API_HOSTNAME)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static UserOperator getOperator()
    {
        if(UserOperator.operator == null)
            UserOperator.operator = new UserOperator();

        return UserOperator.operator;
    }

    @Override
    public User insert(User entity) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
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
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<RegistroCellPhone> call = jsonPlaceHolderAPI.postRegister(registroCellPhone);

        //This will call (asynchronouslly)the OnResponse/OnErrorResponse method in Controller
        call.enqueue(registroCellPhoneCallBack);
        return null;
    }

    @Override
    public User find(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User delete(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }      
}