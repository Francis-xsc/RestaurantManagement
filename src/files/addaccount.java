package files;

import Utils.JDBCUtils;
import Utils.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xsc
 * @time 2020/12/22 - 17:01
 */

/**
 * 添加账户
 */
public class addaccount extends JFrame
{
    private JPanel panel01;
    private JPanel panel02;
    private JPanel panel03;
    private JPanel panel04;
    private JTextField loginField;
    private Box vBox;
    private JPasswordField pwdfield1;
    private JPasswordField pwdfield2;
    private JButton btn1;
    private JButton btn2;

    public addaccount()
    {
        super("注册");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 第 1 个 JPanel, 使用默认的浮动布局
        panel01 = new JPanel();
        panel01.add(new JLabel("用户名"));
        loginField = new JTextField(10);
        panel01.add(loginField);

        // 第 2 个 JPanel, 使用默认的浮动布局
        panel02 = new JPanel();
        panel02.add(new JLabel("密   码"));
        pwdfield1 = new JPasswordField(10);
        panel02.add(pwdfield1);

        // 第 2 个 JPanel, 使用默认的浮动布局
        panel03 = new JPanel();
        panel03.add(new JLabel("确认密码"));
        pwdfield2 = new JPasswordField(10);
        panel03.add(pwdfield2);

        // 第 3 个 JPanel, 使用浮动布局, 并且容器内组件居中显示
        panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton res1 = new JButton("注册");
        JButton res2 = new JButton("取消");
        panel04.add(res1);
        panel04.add(res2);
        res1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String str1=loginField.getText();
                String str2=new String(pwdfield1.getPassword());
                String str3=new String(pwdfield2.getPassword());
                if(str2.equals(str3)&&!str1.equals("")&&!str2.equals(""))
                {
                    dispose();
                    simpletext st = new simpletext("注册成功");
                    register(str1,str2);
                    st.btn.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            new mainmenu();
                        }
                    });
                }
                else
                {
                    JFrame jf = new JFrame();
                    jf.setSize(200,200);
                    JPanel jp = new JPanel();
                    jp.add(new JLabel("信息填写错误"));
                    JButton btn = new JButton("确定");
                    jp.add(btn);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jf.dispose();
                        }
                    });
                    jf.setLocationRelativeTo(null);
                    jf.setContentPane(jp);
                    jf.setVisible(true);
                }
            }
        });
        res2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new addaccount();
            }
        });

        // 创建一个垂直盒子容器, 把上面 3 个 JPanel 串起来作为内容面板添加到窗口
        vBox = Box.createVerticalBox();
        vBox.add(panel01);
        vBox.add(panel02);
        vBox.add(panel03);
        vBox.add(panel04);

        this.setContentPane(vBox);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    /**
     * 注册模块
     * @param account 用户名
     * @param password 密码
     * @throws Exception
     */
    static void register(String account,String password)
    {
        Connection conn= null;
        try
        {
            conn = JDBCUtils.getConnection();
            String sql="insert into account values(?,?);";
            JDBCUtils.update(sql,account, MD5Util.tomd5(password));
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                conn.close();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }

    }
}
