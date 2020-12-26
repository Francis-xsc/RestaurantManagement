package files;

import java.sql.Timestamp;

/**
 * @author xsc
 * @time 2020/12/21 - 10:17
 */

/**
 * 订单类
 */
public class order
{
    private static int id;
    private int id_;
    private int numofpeople;
    private int table_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private double total_price;

    public order()
    {
        id++;
    }

    /**
     * 获取已存在订单的最大值，用于生成新的订单号
     */
    static
    {
        try
        {
            id= get_one_int.max_order_id();
        } catch (Exception e)
        {
            id=1;
        }
    }
    public static int getId()
    {
        return id;
    }

    public static void setId(int id)
    {
        order.id = id;
    }

    public int getNumofpeople()
    {
        return numofpeople;
    }


    public int getTable_id()
    {
        return table_id;
    }



    public Timestamp getStart_time()
    {
        return start_time;
    }



    public Timestamp getEnd_time()
    {
        return end_time;
    }


    public double getTotal_price()
    {
        return total_price;
    }


}

