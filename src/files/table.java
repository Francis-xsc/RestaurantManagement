package files;


/**
 * @author xsc
 * @time 2020/12/17 - 20:54
 */

/**
 * 桌台类
 */
public class table
{
    int id;
    int max_person;
    int floor;
    boolean useable;
    int curorderid;

    public table()
    {
    }

    public table(int id, int max_person, int floor, boolean useable, int curorderid)
    {
        this.id = id;
        this.max_person = max_person;
        this.floor = floor;
        this.useable = useable;
        this.curorderid = curorderid;
    }
}

