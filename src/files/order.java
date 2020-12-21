package files;

import java.sql.Timestamp;

/**
 * @author xsc
 * @time 2020/12/21 - 10:17
 */
public class order
{
    private static int id;
    private Timestamp start_time;
    private Timestamp end_time;

    public order()
    {
        id++;
    }
    static
    {
        id= get_one_int.max_order_id();
    }
    public static int getId()
    {
        return id;
    }
}

