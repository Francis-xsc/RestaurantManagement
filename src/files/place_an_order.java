package files;

import Utils.JDBCUtils;
import org.junit.Test;

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

        JPanel personcount= new JPanel();
        personcount.add(new JLabel("人数"));
        JTextField personcountinput = new JTextField(5);
        personcount.add(personcountinput);
        vBox.add(personcount);

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
                int person= Integer.parseInt(personcountinput.getText());
                String sql="select id from `tables` where max_person>"+person+" and useable = true order by max_person;";
                get_one_int tableno= JDBCUtils.getInstance(files.get_one_int.class,sql);
                if(tableno==null)
                {
                    JFrame no = new JFrame();
                    Box t=Box.createVerticalBox();
                    no.setSize(200,100);
                    no.setLocationRelativeTo(null);
                    t.add(new JLabel("没有相应桌台"));
                    JButton jBtn = new JButton("确定");
                    jb.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            jf.dispose();
                        }
                    });
                    t.add(jBtn);
                    no.setContentPane(t);
                    no.setVisible(true);
                    return;
                }
                sql="update `tables` set useable=0,curorderid="+order.getId()+" where id = "+ tableno.id;
                JDBCUtils.update(sql);
                double sumprice = 0;
                for (one_dish x : m)
                {
                    sumprice += x.calc();
                }
                JFrame jf1 = new JFrame();
                jf1.setSize(300,100);
                jf1.setLocationRelativeTo(null);
                JButton btn=new JButton("确认");
                Box vbox1=Box.createVerticalBox();
                vbox1.add(new JLabel("花费预计共"+sumprice+"元\n请到"+tableno.id+"号餐桌就餐"));
                vbox1.add(btn);
                jf1.setContentPane(vbox1);
                btn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        jf1.dispose();
                    }
                });
                jf1.setVisible(true);
                sql="insert into orders(numofpeople,table_id,start_time,total_price) values(?,?,current_timestamp(),?)";
                JDBCUtils.update(sql,person,tableno.id,sumprice);
            }
        });
        jf.setContentPane(vBox);
        jf.setVisible(true);
    }

}
