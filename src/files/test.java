package files;

import javax.sound.sampled.BooleanControl;
import javax.swing.*;

/**
 * @author xsc
 * @time 2020/12/24 - 8:31
 */
public class test
{
    public static void main(String[] args)
    {
//        JFrame jf = new JFrame();
//        jf.setLocationRelativeTo(null);
//        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        JPanel jp = new JPanel();
//        jf.setSize(200,200);
//        jp.add(new JLabel("123"));
//        jp.add(new JTextField(5));
//        jf.setContentPane(jp);
//        jf.setVisible(true);

        JFrame jf = new JFrame();
        jf.setSize(200,200);
        jf.setLocationRelativeTo(null);
        JPanel jp = new JPanel();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Box vBox= Box.createVerticalBox();
        jp.add(new JLabel("123"));
        vBox.add(jp);
        jp=new JPanel();
        jp.add(new JTextField(5));
        vBox.add(jp);
        jf.setContentPane(vBox);
        jf.setVisible(true
        );
    }
}
