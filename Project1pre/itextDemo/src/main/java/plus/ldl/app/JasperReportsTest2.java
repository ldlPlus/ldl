package plus.ldl.app;

import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author ldl.plus
 * @date 2020年04月21日  20:42
 * 测试JasperReports 数据库方式
 */
public class JasperReportsTest2 {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql:///healthy", "root", "root");


            String jrxmlPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo1.jrxml";
            String jasperPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo1.jasper";
            //编译模板,生成后缀为jasper的二进制文件
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
            // 为模版准备数据，用于最终的pdf文件数据填充
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("reportDate", "2020-04-21");
            parameters.put("company", "itcast");

            //填充数据
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperPath, parameters, connection);
            //输出文件
            String pdfPath = "D:\\test.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
