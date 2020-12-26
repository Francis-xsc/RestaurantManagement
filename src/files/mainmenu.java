package files;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author xsc
 * @time 2020/12/22 - 16:39
 */

/**
 * 主菜单
 */
public class mainmenu extends JFrame
{
    private JButton btn1=new JButton("下单");
    private JButton btn2=new JButton("结账");
    private JButton btn4=new JButton("修改菜单");
    private JButton btn5=new JButton("查看订单详情");
    private JButton btn3=new JButton("添加用户");
    private JButton btn6=new JButton("退出系统");
    private JPanel jp=new JPanel();
    Box vbox=Box.createVerticalBox();
    public mainmenu()
    {
        super("主菜单");
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp.add(btn1);
        vbox.add(jp);
        jp=new JPanel();
        jp.add(btn2);
        vbox.add(jp);
        jp=new JPanel();
        jp.add(btn4);
        vbox.add(jp);
        jp=new JPanel();
        jp.add(btn5);
        vbox.add(jp);
        jp=new JPanel();
        jp.add(btn3);
        vbox.add(jp);
        jp=new JPanel();
        jp.add(btn6);
        vbox.add(jp);
        jp=new JPanel();
        btn1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new place_an_order();
            }
        });
        btn2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new tablepanel();
            }
        });
        btn3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new addaccount();
            }
        });
        btn5.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new orders_info();
            }
        });
        btn4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new updatemenu();
            }
        });
        btn6.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        this.setContentPane(vbox);
        this.setVisible(true);
    }
}
