package files;

import Utils.JDBCUtils;
import Utils.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author xsc
 * @time 2020/12/17 - 20:58
 */

/**
 * 登录页面（main方法类）
 */
public class login extends JFrame
{
    public static void main(String[] args)
    {
        String sql="select id,password from account;";
        account acc= JDBCUtils.getInstance(account.class,sql);
        if(acc==null)
        {
            simpletext st = new simpletext("暂无账户，请先注册");
            st.btn.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    new addaccount();
                }
            });

        }
        else
        {
            JFrame jf = new JFrame("用户登录");
            jf.setSize(1000, 1000);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // 第 1 个 JPanel, 使用默认的浮动布局
            JPanel panel01 = new JPanel();
            panel01.add(new JLabel("用户名"));
            JTextField loginField = new JTextField(10);
            panel01.add(loginField);

            // 第 2 个 JPanel, 使用默认的浮动布局
            JPanel panel02 = new JPanel();
            panel02.add(new JLabel("密   码"));
            JPasswordField pwdfield = new JPasswordField(10);
            panel02.add(pwdfield);

            // 第 3 个 JPanel, 使用浮动布局, 并且容器内组件居中显示
            JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton log = new JButton("登录");
            panel03.add(log);


            // 创建一个垂直盒子容器, 把上面 3 个 JPanel 串起来作为内容面板添加到窗口
            Box vBox = Box.createVerticalBox();
            vBox.add(panel01);
            vBox.add(panel02);
            vBox.add(panel03);

            jf.setContentPane(vBox);

            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
            //按钮监听器
            log.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (logincheck(loginField.getText(), new String(pwdfield.getPassword())))
                    {
                        jf.dispose();
                        new mainmenu();
                    } else
                    {
                        new simpletext("用户名或密码错误");
                    }
                }
            });
        }
    }

    /**
     * 检验用户名密码正确
     * @param account 用户名
     * @param password 密码
     * @return
     */
    static boolean logincheck(String account,String password)
    {
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            conn = JDBCUtils.getConnection();
            String sql="select * from account where id=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,account);
            ps.setObject(2, MD5Util.tomd5(password));
            rs = ps.executeQuery();
            if(rs.next())
            {
                JDBCUtils.closeResource(conn,ps,rs);
                return true;
            }
            else
            {
                JDBCUtils.closeResource(conn,ps,rs);
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return false;
    }
}
