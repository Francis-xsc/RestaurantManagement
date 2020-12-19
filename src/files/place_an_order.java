package files;

import Utils.JDBCUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xsc
 * @time 2020/12/17 - 23:45
 */

public class place_an_order
{
    public static void main(String[] args)
    {
        double sumprice = 0;
        List<one_dish> m = new LinkedList<>();
        JFrame jf = new JFrame("菜单");
        jf.setSize(300, 900);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane jsp = new JScrollPane();
        String sql = "select name,price from menu";
        List<dish> li = JDBCUtils.getForList(dish.class, sql);
        JButton but = new JButton("确认");
        Box vBox = Box.createVerticalBox();
        for (dish x : li)
        {
            one_dish mt = new one_dish(x);
            vBox.add(mt.getJp());
            m.add(mt);


//            JPanel panel = new JPanel();
//            JLabel jl = new JLabel(x.getName());
//            panel.add(jl);
//            jl=new JLabel(x.getPrice()+"");
//            panel.add(jl);
//            JTextField jfield = new JTextField(4);
//            panel.add(jfield);
//            vBox.add(panel);
//            sumprice+=x.getPrice()*Integer.parseInt(jfield.getText());
//            vBox.add(but);
        }
        JButton jb = new JButton("确认");
        vBox.add(jb);
        jb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double sum = 0;
                for (one_dish x:m)
                {
                    sum += x.calc();
                }
                System.out.println(sum);
            }
        });
        jf.setContentPane(vBox);
        jf.setVisible(true);
    }
}
