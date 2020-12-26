package files;

import Utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author xsc
 * @time 2020/12/23 - 13:14
 */

/**
 * 订单详情页面
 */
public class orders_info extends JFrame
{

    public orders_info()
    {
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp=new JPanel(new GridLayout());
        jp.add(new JLabel("人数"));
        jp.add(new JLabel("桌号"));
        jp.add(new JLabel("下单时间"));
        jp.add(new JLabel("结账时间"));
        jp.add(new JLabel("总金额"));
        Box vbox= Box.createVerticalBox();
        vbox.add(jp);
        String sql="select id id_,numofpeople,table_id,start_time,end_time,total_price from `orders`;";
        List<order> a = JDBCUtils.getForList(order.class,sql);
        for(order x:a)
        {
            jp=new JPanel(new GridLayout());
            jp.add(new JLabel(x.getNumofpeople()+""));
            jp.add(new JLabel(x.getTable_id()+""));
            jp.add(new JLabel(x.getStart_time()+""));
            if(x.getEnd_time()!=null)
                jp.add(new JLabel(x.getEnd_time()+""));
            else
                jp.add(new JLabel("暂未结账"));
            jp.add(new JLabel(x.getTotal_price()+""));
            vbox.add(jp);
        }
        JButton btn = new JButton("返回主菜单")  ;
        btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new mainmenu();
            }
        });
        jp=new JPanel();
        jp.add(btn);
        vbox.add(jp);
        setContentPane(vbox);
        setVisible(true);
    }
}
