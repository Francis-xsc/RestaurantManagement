package files;

import java.util.Objects;

/**
 * @author xsc
 * @time 2020/12/17 - 20:54
 */
public class table
{
    int id;
    boolean leisure;
    int MAX_person;
    int location;

    public table()
    {
    }

    public table(int id, int MAX_person, int location)
    {
        this.id = id;
        this.leisure = false;
        this.MAX_person = MAX_person;
        this.location = location;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        table table = (table) o;
        return id == table.id && leisure == table.leisure && MAX_person == table.MAX_person && location == table.location;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, leisure, MAX_person, location);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isLeisure()
    {
        return leisure;
    }

    public void setLeisure(boolean leisure)
    {
        this.leisure = leisure;
    }

    public int getMAX_person()
    {
        return MAX_person;
    }

    public void setMAX_person(int MAX_person)
    {
        this.MAX_person = MAX_person;
    }

    public int getLocation()
    {
        return location;
    }

    public void setLocation(int location)
    {
        this.location = location;
    }
}
