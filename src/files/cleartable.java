package files;

import Utils.JDBCUtils;
import org.junit.Test;

/**
 * @author xsc
 * @time 2020/12/21 - 12:41
 */
public class cleartable
{
    @Test
    public  void clear()
    {
        String sql="update `tables` set useable = true";
        JDBCUtils.update(sql);
    }
}
