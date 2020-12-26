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

/**
 * 下单页面
 */
class place_an_order extends JFrame
{
    public place_an_order()
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
                    btn1.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            no.dispose();
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
                    sql="insert into orderinfo(order_id,dish_id,count) values(?,?,?)";
                    if(!x.jtf.getText().equals("")&&Integer.parseInt(x.jtf.getText())!=0)
                        JDBCUtils.update(sql,get_one_int.table_lastorder_id(tableno.id),x.d.getId(),Integer.parseInt(x.jtf.getText()));
                }
                JFrame jf1 = new JFrame();
                jf1.setSize(300,150);
                jf1.setLocationRelativeTo(null);
                Box vbox1=Box.createVerticalBox();
                JPanel jp = new JPanel();
                jp.add(new JLabel("花费预计共"+sumprice+"元"));
                vbox1.add(jp);
                jp=new JPanel();
                jp.add(new JLabel("请到"+get_one_int.get_floor(tableno.id)+"层的"+tableno.id+"号餐桌就餐"));
                vbox1.add(jp);
                jp=new JPanel();
                JButton btn=new JButton("确认");
                jp.add(btn);
                vbox1.add(jp);
                jf1.setContentPane(vbox1);
                btn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        jf1.dispose();
                        place_an_order.super.dispose();
                        new mainmenu();
                    }
                });
                jf1.setVisible(true);
                sql="insert into orders(numofpeople,table_id,start_time,total_price) values(?,?,current_timestamp(),?)";
                JDBCUtils.update(sql,person,tableno.id,sumprice);
            }
        });
        this.setContentPane(vBox);
        this.setVisible(true);
    }

}
