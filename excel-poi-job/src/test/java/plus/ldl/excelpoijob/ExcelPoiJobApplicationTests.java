package plus.ldl.excelpoijob;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.excelpoijob.domain.Jianguocun;
import plus.ldl.excelpoijob.service.JianguocunService;
import plus.ldl.excelpoijob.utils.PoiUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

@SpringBootTest
class ExcelPoiJobApplicationTests {

    @Autowired
    private JianguocunService service;

    // @Test
    void contextLoads() {
        // Jianguocun jianguocun = service.selectByPrimaryKey(1);
        // System.out.println("jianguocun = " + jianguocun);
        Jianguocun jianguocun = new Jianguocun();
        jianguocun.setId(0);
        jianguocun.setRelationship("");
        jianguocun.setName("");
        jianguocun.setIdcard("");
        jianguocun.setPhone("");
        jianguocun.setBirthday("");
        jianguocun.setAddress("");
        jianguocun.setHousenumberone("");
        jianguocun.setHousenumbertwo("");
        jianguocun.setSex("");
        jianguocun.setHometown("");

        service.insertSelective(jianguocun);
    }


    /**
     * poi读取数据
     */
    // @Test
    public void test40() throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("E:\\job\\b2.xlsx")));
        //读取Excel文件中第一个Sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获得当前工作表中最后一个行号，需要注意：行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("lastRowNum = " + lastRowNum);
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);//根据行号获取每一行
            //获得当前行最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            System.out.println("lastCellNum = " + lastCellNum);
            for (int j = 0; j < 8; j++) {
                XSSFCell cell = row.getCell(j);//根据单元格索引获得单元格对象
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }

    /**
     * 身份证
     */
    // @Test
    public void test72() throws Exception {
        File file = new File("E:\\job\\b2.xlsx");
        List<String[]> excel = PoiUtils.readExcel(file);
        Jianguocun jianguocun;
        for (String[] strings : excel) {
            jianguocun = new Jianguocun();

            jianguocun.setId(Integer.parseInt(strings[0]));
            jianguocun.setName(strings[2]);
            jianguocun.setIdcard(strings[1]);
            jianguocun.setPhone(strings[3]);
            jianguocun.setBirthday(strings[4]);
            jianguocun.setAddress(strings[5]);
            jianguocun.setHousenumberone(strings[6]);
            jianguocun.setHousenumbertwo(strings[7]);
            System.out.println("jianguocun = " + jianguocun);
            int i = service.insertSelective(jianguocun);
            System.out.println("成功 = " + i);
        }
    }

    /**
     * 户主关系
     */
    // @Test
    public void test99() throws Exception {
        File file = new File("E:\\job\\a1.xlsx");
        List<String[]> excel = PoiUtils.readExcel(file);

        Jianguocun jianguocun;
        for (String[] strings : excel) {
            // System.out.println("strings = " + Arrays.toString(strings));
            jianguocun = new Jianguocun();

            jianguocun.setRelationship(strings[0]);
            jianguocun.setName(strings[1]);
            jianguocun.setSex(strings[2]);
            jianguocun.setBirthday(strings[3]);
            jianguocun.setHometown(strings[7]);
            jianguocun.setHousenumberone(strings[5] + strings[6]);

            // System.out.println("jianguocun = " + jianguocun);

            // 根据姓名修改信息
            int i = service.updateByName(jianguocun);
            // System.out.println("成功 = " + i);

            if (i != 1) {
                System.err.println(jianguocun.getName() + "----" + i);
            }
        }
    }

    /**
     * 融合表1
     */
    @Test
    public void test138() throws Exception {
        File file = new File("E:\\job\\a1.xlsx");
        List<String[]> excel = PoiUtils.readExcel(file);

        for (String[] strings : excel) {
            Jianguocun jianguocun = new Jianguocun();
            jianguocun.setRelationship(strings[0]);
            jianguocun.setName(strings[1]);
            jianguocun.setSex(strings[2]);
            jianguocun.setBirthday(strings[3]);
            jianguocun.setAddress(strings[4]);
            jianguocun.setHousenumberone(strings[5] + strings[6]);
            jianguocun.setHometown(strings[7]);

            // 用two来排序 户主-1，妻子-2。。。
            String sortNum;
            switch (strings[0]) {
                case "户主":
                    sortNum = "1";
                    break;
                case "妻":
                    sortNum = "2";
                    break;
                case "夫":
                    sortNum = "3";
                    break;
                case "父亲":
                    sortNum = "4";
                    break;
                case "母亲":
                    sortNum = "5";
                    break;
                case "长子":
                    sortNum = "6";
                    break;
                case "长女":
                    sortNum = "7";
                    break;
                case "子":
                    sortNum = "8";
                    break;
                case "女":
                    sortNum = "9";
                    break;
                case "二女":
                    sortNum = "10";
                    break;
                case "次子":
                    sortNum = "11";
                    break;
                case "三女":
                    sortNum = "12";
                    break;
                case "三子":
                    sortNum = "13";
                    break;
                case "四女":
                    sortNum = "14";
                    break;
                case "四子":
                    sortNum = "15";
                    break;
                case "五子":
                    sortNum = "16";
                    break;
                case "养子或继子":
                    sortNum = "17";
                    break;
                case "养女":
                    sortNum = "18";
                    break;
                case "弟":
                    sortNum = "19";
                    break;
                case "姐姐":
                    sortNum = "20";
                    break;
                case "妹妹":
                    sortNum = "21";
                    break;
                case "儿媳":
                    sortNum = "22";
                    break;
                case "女婿":
                    sortNum = "23";
                    break;
                case "兄":
                    sortNum = "24";
                    break;
                case "侄子":
                    sortNum = "25";
                    break;
                case "侄女":
                    sortNum = "26";
                    break;
                case "孙子":
                    sortNum = "27";
                    break;
                case "孙女":
                    sortNum = "28";
                    break;
                case "外孙女":
                    sortNum = "29";
                    break;
                case "外孙子":
                    sortNum = "30";
                    break;
                case "曾孙子或曾外孙子":
                    sortNum = "31";
                    break;
                default:
                    sortNum = "32";
            }
            jianguocun.setHousenumbertwo(sortNum);
            System.out.println(jianguocun);
            int i = service.insert(jianguocun);
            if (i != 1) {
                System.err.println(jianguocun);
            }
        }
    }

    /**
     * 融合表2
     */
    @Test
    public void test165() throws Exception {
        File file = new File("E:\\job\\b2.xlsx");
        List<String[]> excel = PoiUtils.readExcel(file);
        for (String[] strings : excel) {
            Jianguocun jianguocun = new Jianguocun();
            jianguocun.setName(strings[2]);
            jianguocun.setIdcard(strings[1]);
            jianguocun.setPhone(strings[3]);
            jianguocun.setBirthday(strings[4]);

            int i = service.updateByName(jianguocun);
            if (i != 1) {
                System.err.println(i + "-----------" + jianguocun);
            }
        }
    }

    /**
     * 建国村表格
     */
    // @Test
    public void test128() throws Exception {
        String pathname = "E:\\job\\jianguochun\\周嘉镇建国村7原光明村4组农村土地承包经营权承包方调查表.xls";
        int firstRow = 5;
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(pathname)));
        List<String[]> list = new ArrayList<>();
        //获得当前sheet工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获得当前sheet的开始行
        int firstRowNum = sheet.getFirstRowNum();
        //获得当前sheet的结束行
        int lastRowNum = sheet.getLastRowNum();
        //循环除了第7行的所有行
        for (int rowNum = firstRowNum + firstRow; rowNum <= lastRowNum; rowNum++) {
            //获得当前行
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            String[] cells = new String[5];
            //循环当前行 4/7/8

            Cell cell4 = row.getCell(4);
            cells[0] = PoiUtils.getCellValue(cell4);
            Cell cell7 = row.getCell(7);
            cells[1] = PoiUtils.getCellValue(cell7);
            Cell cell8 = row.getCell(9);
            cells[2] = PoiUtils.getCellValue(cell8);
            cells[3] = PoiUtils.getCellValue(row.getCell(5));
            cells[4] = String.valueOf(rowNum);

            if (!cells[2].equals("√") & !cells[2].equals("")) {
                list.add(cells);
            }
        }

        for (String[] strings : list) {
            System.out.println("strings = " + Arrays.toString(strings));
            Jianguocun jianguocun = null;
            try {
                jianguocun = service.findByName(strings[0], strings[3] + "性");
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (jianguocun != null) {
                System.err.println("jianguocun = " + jianguocun);
                // 设置身份证号 7
                int rowNum = Integer.parseInt(strings[4]);
                int cellNum = 7;

                Cell cell = sheet.getRow(rowNum).getCell(cellNum);
                cell.setCellValue(jianguocun.getIdcard());
            }
        }
        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream out = new FileOutputStream(new File(pathname));
        workbook.write(out);
        out.flush();
        System.err.println("out = 完成--------------------------------------------");
        out.close();
        workbook.close();
    }

    /**
     * 建国村集体经济组织成员身份确认表
     */
    @Test
    public void test203() throws Exception {
        String pathname = "C:\\Users\\93762\\Documents\\建国村集体经济组织成员身份确认表.xlsx";
        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(new File(pathname)));
        XSSFSheet sheet = sheets.getSheetAt(0);

    }

    /**
     * 建国村集体经济组织股权确认登记表
     */
    @Test
    public void test211() throws Exception {
        String pathname = "C:\\Users\\93762\\Documents\\建国村集体经济组织股权确认登记表.xlsx";

    }

    /**
     * 查询所有
     */
    @Test
    public void test374() throws Exception {
        List<Jianguocun> jianguocuns = service.findAll();
        for (Jianguocun jianguocun : jianguocuns) {
            System.out.println("jianguocun = " + jianguocun);
        }
    }

    /**
     *
     */
    @Test
    public void test385() throws Exception {
        String pathname = "C:\\Users\\93762\\Documents\\建国村集体经济组织成员身份确认表.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(pathname)));
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<String> housenumberones = service.findHousenumberonesByGroup();
        ArrayList<List<Jianguocun>> sortList = new ArrayList<>();
        for (String housenumberone : housenumberones) {
            // System.out.println("housenumberone = " + housenumberone);
            List<Jianguocun> jianguocuns = service.findByHousenumberones(housenumberone);
            Collections.sort(jianguocuns);
            // System.out.println("jianguocuns = " + jianguocuns);
            sortList.add(jianguocuns);
        }
        sortList.sort(Comparator.comparingInt(o -> o.get(0).getId()));
        int rowNum = 1;
        for (int i = 0; i < sortList.size(); i++) {
            // 遍历每一条数据
            for (int j = 0; j < sortList.get(i).size(); j++) {
                // 每一条写出的数据
                XSSFRow row = sheet.createRow(rowNum);
                Jianguocun jianguocun = sortList.get(i).get(j);
                System.out.println("jianguocun = " + jianguocun);
                row.createCell(0).setCellValue(rowNum++);
                row.createCell(1).setCellValue(i + 1);
                row.createCell(2).setCellValue(jianguocun.getHousenumberone().substring(0, 1));
                row.createCell(3).setCellValue(jianguocun.getRelationship());
                row.createCell(4).setCellValue(jianguocun.getName());
                row.createCell(5).setCellValue(jianguocun.getIdcard());
            }
            System.out.println();
        }
        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream out = new FileOutputStream(new File(pathname));
        workbook.write(out);
        out.flush();
        System.err.println("out = 完成--------------------------------------------");
        out.close();
        workbook.close();

    }

    /**
     * 建国村集体经济组织股权确认登记表
     */
    @Test
    public void test427() throws Exception {
        String pathname = "C:\\Users\\93762\\Documents\\建国村集体经济组织股权确认登记表.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(pathname)));
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<String> housenumberones = service.findHousenumberonesByGroup();
        ArrayList<List<Jianguocun>> sortList = new ArrayList<>();
        for (String housenumberone : housenumberones) {
            // System.out.println("housenumberone = " + housenumberone);
            List<Jianguocun> jianguocuns = service.findByHousenumberones(housenumberone);
            Collections.sort(jianguocuns);
            // System.out.println("jianguocuns = " + jianguocuns);
            sortList.add(jianguocuns);
        }
        sortList.sort(Comparator.comparingInt(o -> o.get(0).getId()));
        // 建立前6列
        /*int rowNum = 1;
        for (int i = 0; i < sortList.size(); i++) {
            // 遍历每一条数据
            for (int j = 0; j < sortList.get(i).size(); j++) {
                // 每一条写出的数据
                XSSFRow row = sheet.createRow(rowNum);
                Jianguocun jianguocun = sortList.get(i).get(j);
                // System.out.println("jianguocun = " + jianguocun);
                row.createCell(0).setCellValue(rowNum++);
                row.createCell(1).setCellValue(i + 1);
                row.createCell(2).setCellValue(jianguocun.getHousenumberone().substring(0, 1));
                row.createCell(3).setCellValue(jianguocun.getRelationship());
                row.createCell(4).setCellValue(jianguocun.getName());
                row.createCell(5).setCellValue(jianguocun.getIdcard());
                row.createCell(6).setCellValue("有");
                row.createCell(7).setCellValue(1);
                row.createCell(9).setCellValue(1);
            }
        }*/

        // 建立第10列

        int firstRowNum = 0;
        int lastRowNum = 0;
        for (List<Jianguocun> jianguocuns : sortList) {
            int rowSize = jianguocuns.size();
            lastRowNum = firstRowNum + rowSize - 1;
            sheet.createRow(firstRowNum).createCell(2).setCellValue(jianguocuns.get(0).getHousenumberone().substring(0, 1));
            if (rowSize > 1) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRowNum, lastRowNum, 2, 2);
                sheet.addMergedRegion(cellRangeAddress);
            }
            firstRowNum = lastRowNum + 1;
            System.out.println(rowSize + "---");
        }
        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream out = new FileOutputStream(new File(pathname));
        workbook.write(out);
        out.flush();
        System.err.println("out = 完成--------------------------------------------");
        out.close();
        workbook.close();
    }


}
