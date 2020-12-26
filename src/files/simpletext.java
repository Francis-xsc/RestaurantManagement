package files;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author xsc
 * @time 2020/12/23 - 1:26
 */

/**
 * 简单提示框，为工具类
 */
public class simpletext extends JFrame
{


    JButton btn;
    simpletext()
    {

    }
    simpletext(String str)
    {
        this.setSize(200,130);
        JPanel jp = new JPanel();
        Box vbox=Box.createVerticalBox();
        jp.add(new JLabel(str,JLabel.CENTER));
        vbox.add(jp);
        jp=new JPanel();
        btn = new JButton("确定");
        jp.add(btn);
        vbox.add(jp);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setLocationRelativeTo(null);
        this.setContentPane(vbox);
        this.setVisible(true);
    }
}
