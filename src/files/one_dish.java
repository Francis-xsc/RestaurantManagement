package files;

import javax.swing.*;

/**
 * @author xsc
 * @time 2020/12/19 - 11:26
 */
public class one_dish
{
    dish d;
    JPanel jp;
    JTextField jtf;

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

    public one_dish(dish d, JPanel jp)
    {
        this.d = d;
        this.jp = jp;
    }

    public one_dish(dish d)
    {
        this.d = d;
        jp = new JPanel();
        jp.add(new JLabel(d.getName()));
        jtf = new JTextField(5);
        jp.add(new JLabel(d.getPrice()+""));
        jp.add(jtf);
        jp.setVisible(true);
    }

    public one_dish()
    {
        jp.add(jtf);
    }

    /**
     * 计算此菜品的总价，如果不填则结果为0
     * @return
     */
    public double calc()
    {
        int t;
        try
        {
            t = Integer.parseInt(jtf.getText());
        }catch (Exception e)
        {
            t=0;
        }
        return d.getPrice() * t;
    }
}
