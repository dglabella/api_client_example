package ar.edu.unsl.backend.model.entities;

public class User
{
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;
    private String email;
    private Long phoneNumber;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getDni()
    {
        return this.dni;
    }

    public void setDni(Integer dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}