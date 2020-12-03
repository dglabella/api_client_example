package ar.edu.unsl.backend.model.persistence;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import ar.edu.unsl.App;
import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.interfaces.IUserOperator;
import ar.edu.unsl.backend.model.repositories.UserRepository;
import ar.edu.unsl.backend.model.services.UserService;
import ar.edu.unsl.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;

public class UserOperator2 implements IUserOperator
{
    private final static int REQUEST_CONNECT_TIMEOUT_TOLERANCE = 20;
    //private final static int REQUEST_READ_TIMEOUT_TOLERANCE = 5;
    //private final static int REQUEST_WRITE_TIMEOUT_TOLERANCE = 5;

    public final static String ID = "id";
    public final static String RESOURCE = "/users";
    public final static String SINGLE_RESOURCE = RESOURCE + "/{" + ID + "}";

    private static UserOperator2 operator;

    private UserService userService;
    private CloseableHttpClient httpClient;

    private UserOperator2(UserService userService)
    {
        this.userService = userService;
        //CloseableHttpClient clienteHttp = HttpClients.createDefault();
        this.httpClient = HttpClientBuilder.create()
        .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(REQUEST_CONNECT_TIMEOUT_TOLERANCE).build())
        .build();
    }

    public static UserOperator2 getOperator(UserService userService)
    {
        if(UserOperator2.operator == null)
            UserOperator2.operator = new UserOperator2(userService);

        return UserOperator2.operator;
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
        List<User> ret = null;

        HttpGet getHttp = new HttpGet(App.API_HOSTNAME+RESOURCE);
        HttpResponse respuestaHttp = httpClient.execute(getHttp);
        int statusCode = respuestaHttp.getStatusLine().getStatusCode();
        if(statusCode >= 200 && statusCode < 300)
        {
            HttpEntity entidad = respuestaHttp.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            ret = mapper.readValue(EntityUtils.toString(entidad), new TypeReference<List<User>>() {});
        }
        else
        {
            System.err.println("Error Inesperado STATUS: " + statusCode);
        }
        httpClient.close();

        return ret;
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