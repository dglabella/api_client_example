package ar.edu.unsl.frontend.service_subscribers;

import java.util.List;
import ar.edu.unsl.backend.model.entities.User;

public interface UserServiceSubscriber
{
    void showUser(User user);

    void showUsers(List<User> users);
}