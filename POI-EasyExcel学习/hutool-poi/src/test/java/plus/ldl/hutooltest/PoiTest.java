package plus.ldl.hutooltest;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;
import plus.ldl.domain.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ldl.plus
 * @date 2020年05月19日  23:04
 */
public class PoiTest {

    /**
     *
     */
    @Test
    public void test13() throws Exception {
        String filePath = "C:\\Users\\93762\\Documents\\工作簿1.xlsx";
        ExcelReader excelReader = ExcelUtil.getReader(filePath, 0);
        ExcelWriter excelWriter = ExcelUtil.getWriter("C:\\Users\\93762\\Documents\\工作簿2.xlsx");
        List<User> users = excelReader.readAll(User.class);

        // HashMap<Integer, Integer> hashMap = new HashMap<>();
        TreeMap<Integer, Integer> hashMap = new TreeMap<>();
        for (User user : users) {
            int group = user.getGroup();
            if (hashMap.get(group) != null) {
                hashMap.put(group, hashMap.get(group) + 1);
            } else {
                hashMap.put(group, 1);
            }
        }

        int i = 1;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            treeMap.put(i++, entry.getValue());
        }

        int j = 1;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            int column = 2;
            if (entry.getValue() == 1) {
                excelWriter.writeCellValue(column, j, entry.getValue());
            } else {
                excelWriter.merge(j, j + entry.getValue() - 1, column, column, entry.getValue(), false);
            }
            j = j + entry.getValue();
            /*for (Integer integer = 0; integer < entry.getValue(); integer++) {
                excelWriter.writeCellValue(j++, 1, entry.getKey());
            }*/

        }

        excelWriter.close();

    }

}
