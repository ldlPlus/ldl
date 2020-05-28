package plus.ldl.testhutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.util.Date;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月16日  20:01
 * 测试胡桃里-core模块
 */
public class CoreTest {

    /**
     * Convert
     */
    @Test
    public void test16() throws Exception {

        // String str = Convert.toStr(new int[]{1, 2, 3, 4, 56});
        String str = Convert.toStr(123);
        System.out.println("str = " + str);

        String a = "2017.06.05";
        Date date = Convert.toDate(a);
        System.out.println("date = " + date);

    }

    /**
     * Date
     */
    @Test
    public void test57() throws Exception {
        TimeInterval timer = DateUtil.timer();
        DateTime dateTime = DateUtil.date(System.currentTimeMillis() - 1000000000);
        System.out.println("dateTime = " + dateTime);

        String now = DateUtil.now();
        System.out.println("now = " + now);

        String today = DateUtil.today();
        System.out.println("today = " + today);

        DateTime parse = DateUtil.parse("2019.3.3", "yyyy.M.d");
        System.out.println("parse = " + parse);

        long interval = timer.intervalMs();
        System.out.println("interval = " + interval);

        ChineseDate chineseDate = new ChineseDate(DateUtil.date());
        System.out.println("chineseDate = " + chineseDate);
    }

    /**
     * io
     */
    @Test
    public void test64() throws Exception {
        BufferedInputStream in = FileUtil.getInputStream("D:\\easyTest.xlsx");
        BufferedOutputStream out = FileUtil.getOutputStream("D:\\easyTest1.xlsx");
        byte[] bytes = IoUtil.readBytes(in);
        System.out.println("bytes = " + new String(bytes, "GBK"));
        IoUtil.write(out, false, bytes);
        BufferedReader reader = IoUtil.getUtf8Reader(in);
        FastByteArrayOutputStream read = IoUtil.read(in);
        System.out.println("read = " + read.toString());

        IoUtil.close(out);
        IoUtil.close(in);
    }

    /**
     * 工具类
     */
    @Test
    public void test88() throws Exception {
        boolean b = StrUtil.hasEmpty("");
        System.out.println("b = " + b);

        String ipconfig = RuntimeUtil.execForStr("ipconfig");
        System.out.println("ipconfig = " + ipconfig);
    }

    /**
     * poi
     */
    @Test
    public void test102() throws Exception {
        ExcelReader reader = ExcelUtil.getReader("E:\\job\\b2.xlsx");
        List<List<Object>> list = reader.read();
        for (List<Object> objects : list) {
            System.out.println("objects = " + objects);
        }
    }
}
