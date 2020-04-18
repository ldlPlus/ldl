package plus.ldl.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author ldl.plus
 * @date 2020年04月12日  22:26
 */
public class Demo01 {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);

        configuration.setDirectoryForTemplateLoading(new File("d:\\ftl"));
        configuration.setDefaultEncoding("utf-8");

        String name = "test.ftl";
        Template template = configuration.getTemplate(name);

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "itcast");
        map.put("message", "hello freemarker!!");

        FileWriter writer = new FileWriter("D:\\ftl\\test.html");

        template.process(map, writer);
        writer.close();
    }
}
