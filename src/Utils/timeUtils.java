package Utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xsc
 * @time 2020/12/19 - 17:17
 */
public class timeUtils
{
    @Test
    public void fn()
    {
        Date d=getCurrentTime();
        System.out.println(d);
    }
    Date getCurrentTime()
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));
        return date;
    }
}
