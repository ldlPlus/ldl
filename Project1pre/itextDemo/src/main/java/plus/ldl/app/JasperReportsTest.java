package plus.ldl.app;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年04月21日  20:42
 * 测试JasperReports
 */
public class JasperReportsTest {
    public static void main(String[] args) throws JRException {
        String jrxmlPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo.jrxml";
        String jasperPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo.jasper";
        //编译模板
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);

        HashMap<String, Object> paramters = new HashMap<String, Object>();
        paramters.put("reportDate", "2019-10-10");
        paramters.put("company", "itcast");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "xiaoming");
        map1.put("address", "beijing");
        map1.put("email", "xiaoming@itcast.cn");
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "xiaoli");
        map2.put("address", "nanjing");
        map2.put("email", "xiaoli@itcast.cn");
        list.add(map1);
        list.add(map2);
        //填充数据
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, paramters, new JRBeanCollectionDataSource(list));
        //输出文件
        String pdfPath = "D:\\test.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
    }
}
