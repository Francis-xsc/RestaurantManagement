package files;

import Utils.JDBCUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xsc
 * @time 2020/12/23 - 17:00
 */

/**
 * 桌台追加菜品
 */
public class add_dish extends JFrame
{
    add_dish(){}

    /**
     * 桌台追加菜品
     * @param tableno 桌台编号
     */
    public add_dish(int tableno)
    {
        super("加菜");
        List<one_dish> m = new LinkedList<>();
        this.setSize(300, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String sql = "select id,name,price from menu";
        List<dish> li = JDBCUtils.getForList(dish.class, sql);
        JButton but = new JButton("确认");
        Box vBox = Box.createVerticalBox();

        for (dish x : li)
        {
            one_dish mt = new one_dish(x);
            vBox.add(mt.getJp());
            m.add(mt);
        }
        JButton btn1 = new JButton("确认");
        JButton btn2 = new JButton("返回");
        btn2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new mainmenu();
            }
        });
        JPanel jp = new JPanel();
        jp.add(btn1);
        jp.add(btn2);
        vBox.add(jp);
        btn1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (one_dish x : m)
                {
                    String sql="insert into orderinfo(order_id,dish_id,count) values(?,?,?)";
                    if(!x.jtf.getText().equals("")&&Integer.parseInt(x.jtf.getText())!=0)
                        JDBCUtils.update(sql,get_one_int.table_lastorder_id(tableno),x.d.getId(),Integer.parseInt(x.jtf.getText()));
                }
                dispose();
                new mainmenu();
            }
        });
        this.setContentPane(vBox);
        this.setVisible(true);
    }
}
