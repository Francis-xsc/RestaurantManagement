package files;

import Utils.JDBCUtils;

/**
 * @author xsc
 * @time 2020/12/21 - 11:41
 */

/**
 * 用于获取一个值，工具类
 */
public class get_one_int
{
    Integer id;
    Double price;

    /**
     * 用于添加订单
     * @return 当前订单号的最大值
     */
    public static int max_order_id()
    {
        String sql="select max(id) id from orders;";
        get_one_int max = JDBCUtils.getInstance(get_one_int.class, sql);
        return  max.id;
    }

    /**
     * 通过桌台号获取该桌台上一个或当前订单编号
     * @param table_id 桌台编号
     * @return 该桌台上一个或当前订单编号
     */
    public static int table_lastorder_id(int table_id)
    {
        String sql="select curorderid id from `tables` where id=?";
        get_one_int ans=JDBCUtils.getInstance(get_one_int.class,sql,table_id);
        return ans.id;
    }

    /**
     * 计算订单总金额
     * @param table_id 桌台号
     * @return 订单总金额
     */
    public static double calc_pay(int table_id)
    {
        String sql="SELECT SUM(price * count) price FROM menu INNER JOIN orderinfo ON menu.id = orderinfo.dish_id WHERE orderinfo.order_id = (SELECT curorderid FROM `tables` WHERE id = ?);";
        get_one_int ans=JDBCUtils.getInstance(get_one_int.class,sql,table_id);
        return ans.price;
    }

    /**
     * 获取桌台号所在楼层
     * @param table_id 桌台号
     * @return 该桌台所在楼层
     */
    public static int get_floor(int table_id)
    {
        String sql="SELECT floor id FROM `tables` WHERE id = ?;";
        get_one_int ans=JDBCUtils.getInstance(get_one_int.class,sql,table_id);
        return ans.id;
    }

}
