package plus.ldl.file;

import org.junit.Test;

import java.io.File;

/**
 * @author ldl.plus
 * @date 2020年06月05日  11:45
 * 遍历文件夹，获取里面的MP4并剪切
 */
public class FileTest {
    /**
     * 遍历文件，获取MP4后删除文件夹
     */
    @Test
    public void test15() throws Exception {
        this.getFile("C:\\D\\XiaZai\\96464039");
    }

    public void getFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("file = " + file);
            File[] files = file.listFiles();
            System.out.println("files = " + files);
            for (File file1 : files) {
                File[] files1 = file1.listFiles();
                for (File f : files1) {
                    if (f.getName().endsWith("mp4")) {
                        f.renameTo(new File(path + f.getName()));
                    }
                }
            }
        }
    }
}
