package ar.edu.unsl.backend.util;

import java.util.List;

public class Data<Entity>
{
    private Entity entity;

    private List<Entity> entities;

    public Entity getEntity()
    {
        return this.entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    public List<Entity> getEntities()
    {
        return this.entities;
    }

    public void setEntities(List<Entity> entities)
    {
        this.entities = entities;
    }
}