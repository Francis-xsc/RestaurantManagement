package files;

import Utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author xsc
 * @time 2020/12/22 - 23:55
 */

/**
 * 桌台管理页面，用于追加菜品和结账
 */
class tablepanel extends JFrame
{
    table t;
    JButton but1;

    public tablepanel()
    {
        this.setSize(500, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp=new JPanel(new GridLayout(28 ,5));
        jp.add(new JLabel("桌号"));
        jp.add(new JLabel("最大人数"));
        jp.add(new JLabel("楼层"));
        jp.add(new JLabel("状态"));
        jp.add(new JLabel());
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
            JButton btn1=new JButton("加菜");
            jp.add(btn1);
            JButton btn = new JButton("结账");
            if(x.useable)
            {
                btn.setEnabled(false);
                btn1.setEnabled(false);
            }
            btn1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    dispose();
                    new add_dish(x.id);
                }
            });
            btn.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                    String sql="update orders set end_time=current_timestamp() where id=?;";
                    JDBCUtils.update(sql,get_one_int.table_lastorder_id(x.id));
                    JFrame jf=new JFrame();
                    jf.setSize(300,300);
                    jf.setLocationRelativeTo(null);
                    String sql1="update `tables` set useable=1 where id=?";
                    JDBCUtils.update(sql1,x.id);;
                    jf.setSize(200,200);
                    Box vBox= Box.createVerticalBox();
                    JPanel pa = new JPanel();
                    setVisible(false);
                    btn.setEnabled(false);
                    btn1.setEnabled(false);
                    double yingshou=get_one_int.calc_pay(x.id);
                    pa.add(new JLabel("应收:"+yingshou));
                    vBox.add(pa);
                    pa=new JPanel();
                    pa.add(new JLabel("实收："));
                    JTextField jtf = new JTextField(5);
                    pa.add(jtf);
                    vBox.add(pa);
                    pa=new JPanel();
                    JButton btn = new JButton("确定");
                    btn.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            jf.setVisible(false);
                            String str=jtf.getText();
                            double shishou= Double.parseDouble(str);
                            simpletext st = new simpletext("应找零" + (shishou - yingshou) + "元");
                            st.btn.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    jf.dispose();
                                    setVisible(true);
                                }

                            });
                        }
                    });
                    pa.add(btn);
                    vBox.add(pa);
                    jf.setContentPane(vBox);
                    jf.setVisible(true);
                }
            });
            jp.add(btn);
        }
        JButton btn = new JButton("返回主菜单");
        btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new mainmenu();
            }
        });
        Box vBox=Box.createVerticalBox();
        vBox.add(jp);
        jp=new JPanel();
        jp.add(btn);
        vBox.add(jp);
        setContentPane(vBox);
        setVisible(true);
    }
}
