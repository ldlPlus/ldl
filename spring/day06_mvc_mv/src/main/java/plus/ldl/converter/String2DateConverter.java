package plus.ldl.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ldl.plus
 * @date 2020年03月16日  10:46
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String dateStr) {//2020.3.16
        System.out.println("String2DateConverter.convert.dateStr = " + dateStr);
        SimpleDateFormat sdf = null;
        if (dateStr.contains("-")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateStr.contains(".")) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else if (dateStr.contains("/")) {
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        }
        if (sdf != null) {
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
