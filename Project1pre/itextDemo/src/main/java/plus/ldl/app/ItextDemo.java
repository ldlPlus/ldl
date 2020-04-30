package plus.ldl.app;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author ldl.plus
 * @date 2020年04月21日  16:56
 * 测试Itext
 */
public class ItextDemo {
    public static void main(String[] args) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D:\\text.pdf"));
            document.open();
            document.add(new Paragraph("hello itext"));
            // document.add();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
