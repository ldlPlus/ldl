package plus.ldl.app;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年04月21日  20:42
 * 测试JasperReports javabean方式
 */
public class JasperReportsTest3 {
    public static void main(String[] args) throws Exception {


        String jrxmlPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo2.jrxml";
        String jasperPath = "C:\\javaProject\\ldl\\Project1pre\\itextDemo\\src\\main\\resources\\demo2.jasper";
        //编译模板,生成后缀为jasper的二进制文件
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
        // 为模版准备数据，用于最终的pdf文件数据填充
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportDate", "2020-04-21");
        parameters.put("company", "itcast");

        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "入职体检套餐");
        map1.put("age", "18-60");
        map1.put("code", "RZTJ");
        map1.put("sex", "男");
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "阳光爸妈老年健康体检");
        map2.put("age", "55-60");
        map2.put("code", "YGBM");
        map2.put("sex", "不详");
        list.add(map1);
        list.add(map2);
        //填充数据
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperPath, parameters, new JRBeanCollectionDataSource(list));
        //输出文件
        String pdfPath = "D:\\test.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
    }
}
