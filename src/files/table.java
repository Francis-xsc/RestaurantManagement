package files;

import Utils.JDBCUtils;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author xsc
 * @time 2020/12/17 - 20:54
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

class tablepanel
{
    table t;
    JButton but1;

    public tablepanel()
    {
    }

    public static JFrame jm()
    {
        JFrame t=new JFrame();
        JPanel jp=new JPanel(new GridLayout(31 ,5));
        jp.add(new JLabel("桌号"));
        jp.add(new JLabel("最大人数"));
        jp.add(new JLabel("楼层"));
        jp.add(new JLabel("状态"));
        jp.add(new JLabel());
        String sql="select * from `tables`";
        List<table> a = JDBCUtils.getForList(table.class,sql);
        for(table x:a)
        {
            jp.add(new JLabel(x.id+""));
            jp.add(new JLabel(x.max_person+""));
            jp.add(new JLabel(x.floor+""));
            if(x.useable)
                jp.add(new JLabel("空闲"));
            else
                jp.add(new JLabel("占用"));

            JButton btn = new JButton("结账");
            if(x.useable)
                btn.setEnabled(false);
            btn.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JFrame jf=new JFrame();
                    String sql1="update `tables` set useable=1 where id=?";
                    JDBCUtils.update(sql1,x.id);;
                    jf.setSize(200,200);
                    JPanel pa = new JPanel();
                    t.setVisible(false);
                    btn.setEnabled(false);
                    t.setVisible(true);

                    jf.add(new JLabel("应收:"));

                    jf.setVisible(true);
                }
            });
            jp.add(btn);
        }
        t.setContentPane(jp);
        return t;
    }

}
