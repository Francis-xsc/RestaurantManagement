package files;

import Utils.JDBCUtils;

/**
 * @author xsc
 * @time 2020/12/21 - 12:41
 */

/**
 * 清空桌台占用信息，用于测试
 */
public class cleartable
{
    public  void clear()
    {
        String sql="update `tables` set useable = true";
        JDBCUtils.update(sql);
    }
}
