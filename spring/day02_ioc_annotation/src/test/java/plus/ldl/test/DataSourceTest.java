package plus.ldl.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author ldl.plus
 * @date 2020年03月10日  10:32
 * 测试连接池
 */
public class DataSourceTest {
    /**
     * 测试c3p0连接池
     */
    @Test
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setJdbcUrl("jdbc:mysql:///db4");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");

        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

    /**
     * 测试druid连接池
     */
    @Test
    public void test2() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///db4");
        dataSource.setPassword("root");
        dataSource.setUsername("root");

        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

    /**
     * 配置文件方式
     */
    @Test
    public void test3() throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc", Locale.CHINA);
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setUser(username);

        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

    /**
     *
     */
    @Test
    public void test4() throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc", Locale.CHINA);
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);

        DruidPooledConnection connection = dataSource.getConnection(1000);
        PreparedStatement statement = connection.prepareStatement("select * from db4.account");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            System.out.println("anInt = " + anInt);
        }
        dataSource.close();
    }


    /**
     *
     */
    @Test
    public void test5() throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        System.out.println("dataSource = " + dataSource);
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from db4.account");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int money = rs.getInt(3);
            System.out.println("connection = " + connection + "===>>" + id + "--" + name + "--" + money);
        }
        rs.close();
        connection.close();
    }


    /**
     * spring注入c3p0
     */
    @Test
    public void test6() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = app.getBean("dataSource", DataSource.class);
        System.out.println("dataSource = " + dataSource);
    }

    /**
     * spring注入druid
     */
    @Test
    public void test143() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource druidDataSource = app.getBean("druidDataSource", DataSource.class);
        System.out.println("druidDataSource = " + druidDataSource);
    }
}
