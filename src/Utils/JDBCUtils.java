package Utils;

import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xsc
 * @time 2020/12/15 - 10:15
 */

/**
 * JDBC工具类
 */
public class JDBCUtils
{
    @Test
    public void fn()
    {
        Connection conn= null;
        try
        {
            conn = getConnection();
            System.out.println(conn);
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
    /**
     * 获取数据库连接
     * @return 数据库连接
     * @throws Exception 抛出异常
     */
    public static Connection getConnection() throws Exception
    {
        // 1.读取配置文件中的4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭资源
     * @param conn 连接
     * @param ps Statement对象
     */
    public static void closeResource(Connection conn, Statement ps)
    {
        try
        {
            if (ps != null)
                ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (conn != null)
                conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * @param conn 连接
     * @param ps Statment对象
     * @param rs 结果集
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs)
    {
        try
        {
            if (ps != null)
                ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (conn != null)
                conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (rs != null)
                rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通用的增删改操作
     * @param sql sql语句
     * @param args 填充占位符
     */
    public static void update(String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同！
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.资源的关闭
            JDBCUtils.closeResource(conn, ps);

        }
    }

    /**
     * 针对于不同的表的通用的查询操作，返回表中的一条记录
     * @param clazz 类
     * @param sql sql语句
     * @param args 填充占位符
     * @param <T> 类
     * @return 表中的一条记录
     */
    public static <T> T getInstance(Class<T> clazz,String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);

        }

        return null;
    }

    /**
     * 返回list
     * @param clazz 类
     * @param sql sql语句
     * @param args 占位符
     * @param <T> 类
     * @return 查询到的结果集
     */
    public static  <T> List<T> getForList(Class<T> clazz,String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);

        }

        return null;
    }
}

