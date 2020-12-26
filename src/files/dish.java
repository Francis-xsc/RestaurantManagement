package files;

/**
 * @author xsc
 * @time 2020/12/18 - 22:25
 */

/**
 * 菜品类
 */
public class dish
{
    private int id;
    private String name;
    private double price;

    public dish()
    {
    }

    public dish(int id,String name, double price)
    {
        this.id=id;
        this.name = name;
        this.price = price;
    }


    public int getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
