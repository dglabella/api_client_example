package ar.edu.unsl.backend.util;

import ar.edu.unsl.backend.model.entities.User;

public class UserContainer
{
    private int code;
    private Metatada meta;
    private Data<User> data;

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Metatada getMeta()
    {
        return this.meta;
    }

    public void setMeta(Metatada meta)
    {
        this.meta = meta;
    }

    public Data<User> getData()
    {
        return this.data;
    }

    public void setData(Data<User> data)
    {
        this.data = data;
    }
}