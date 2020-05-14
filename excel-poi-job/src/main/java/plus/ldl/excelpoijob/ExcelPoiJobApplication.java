package plus.ldl.excelpoijob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("plus.ldl.excelpoijob.mapper")
public class ExcelPoiJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelPoiJobApplication.class, args);
    }

}
