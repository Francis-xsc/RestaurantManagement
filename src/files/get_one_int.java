package files;

import Utils.JDBCUtils;

/**
 * @author xsc
 * @time 2020/12/21 - 11:41
 */
public class get_one_int
{
    Integer id;

    public static int max_order_id()
    {
        String sql="select max(id) id from orders;";
        get_one_int max = JDBCUtils.getInstance(get_one_int.class, sql);
        return  max.id;
    }


}
