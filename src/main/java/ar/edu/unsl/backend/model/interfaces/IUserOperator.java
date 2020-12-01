package ar.edu.unsl.backend.model.interfaces;

import ar.edu.unsl.backend.model.entities.User;

public interface IUserOperator extends CrudOperator<User>
{
    User find(Integer id);

    User delete(Integer id);
    
}