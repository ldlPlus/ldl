package plus.ldl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @author ldl.plus
 * @date 2020年05月15日  11:00
 */
public class ExcelWriteTest {
    /**
     *
     */
    @Test
    public void test14() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("统计表");

        HSSFRow row1 = sheet.createRow(0);

        HSSFCell cell11 = row1.createCell(0);
        cell11.setCellValue("哈哈");

        String s = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("s = " + s);
    }
}
