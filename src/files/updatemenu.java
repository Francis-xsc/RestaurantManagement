package files;

import Utils.JDBCUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xsc
 * @time 2020/12/22 - 22:50
 */

/**
 * 修改菜单页面
 */
public class updatemenu extends JFrame
{
    public updatemenu()
    {
        super("菜单");
        List<one_dish> m = new LinkedList<>();
        this.setSize(300, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane jsp = new JScrollPane();
        String sql = "select id,name,price from menu";
        List<dish> li = JDBCUtils.getForList(dish.class, sql);
        JButton but = new JButton("确认");
        Box vBox = Box.createVerticalBox();
        for (dish x : li)
        {
            premenu t=new premenu(x);
            vBox.add(t.getJp());
            t.btn1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String sql="delete from menu where id=?;";
                    JDBCUtils.update(sql,x.getId());
                    dispose();
                    new updatemenu();
                }
            });
        }
        JButton jb1= new JButton("添加");
        JButton jb2 = new JButton("返回");

//        vBox.add(jb1);
        jb1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame jf = new JFrame();
                jf.setSize(300,150);
                Box vBox=Box.createVerticalBox();
                JPanel jp = new JPanel();
                jp.add(new JLabel("菜名:"));
                JTextField jtf1 = new JTextField(5);
                jp.add(jtf1);
                vBox.add(jp);
                jp=new JPanel();
                jp.add(new JLabel("价格:"));
                JTextField jtf2 = new JTextField(5);
                jp.add(jtf2);
                vBox.add(jp);
                jp=new JPanel();
                jf.setLocationRelativeTo(null);
                jf.setContentPane(vBox);
                JButton btn1 = new JButton("确认");
                JButton btn2 = new JButton("取消");
                btn1.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        String str1=jtf1.getText();
                        String str2=jtf2.getText();
                        String sql="insert into menu(name,price) values(?,?);";
                        double t=-1;
                        try
                        {
                            t=Double.parseDouble(str2);
                        } catch (Exception ee)
                        {
                            ee.printStackTrace();
                            t=-1;
                        }
                        if(t>=0)
                        {
                            JDBCUtils.update(sql,str1,Double.parseDouble(str2));
                            jf.dispose();
                        }
                        else
                        {
                            simpletext s = new simpletext("价格格式错误");
                            s.btn.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    dispose();
                                    new updatemenu();
                                }
                            });

                        }
                    }
                });
                btn2.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        jf.dispose();
                        dispose();
                        new updatemenu();
                    }
                });
                jp.add(btn1);
                jp.add(btn2);
                vBox.add(jp);
                jf.setVisible(true);

            }
        });
        jb2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new mainmenu();
            }
        });
//        vBox.add(jb2);
        JPanel jp = new JPanel();
        jp.add(jb1);
        jp.add(jb2);
        vBox.add(jp);
        this.setContentPane(vBox);
        this.setVisible(true);
    }
}

/**
 * 原订单菜品的对象
 */
class premenu
{
    dish d;
    JPanel jp;
    JButton btn1;

    public dish getD()
    {
        return d;
    }

    public void setD(dish d)
    {
        this.d = d;
    }

    public JPanel getJp()
    {
        return jp;
    }

    public void setJp(JPanel jp)
    {
        this.jp = jp;
    }


    public premenu(dish d)
    {
        this.d = d;
        jp = new JPanel();
        jp.add(new JLabel(d.getName()));
        btn1 = new JButton("删除");
        jp.add(new JLabel(d.getPrice()+""));
        jp.add(btn1);
        jp.setVisible(true);

    }


}