package plus.ldl.file.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import plus.ldl.file.domain.FastDFSFile;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年06月04日  15:
 * 实现FastDFS.文件管理
 * 文件上传
 * 文件删除
 * 文件下载
 * 文件信息获取
 * Storage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {
    /*
     * 加载Tracker连接信息
     */
    static {
        String fileName = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(fileName);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @return
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws IOException, MyException {
        //附加参数
        NameValuePair[] nameValuePair = new NameValuePair[1];
        nameValuePair[0] = new NameValuePair("拍摄地址", "重庆");

        //创建一个Tracker访 问的客户端对象TrackerClient τ
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //通过StorageClient访问Storage,实现文件上传,并且获取文件上传后的存储信息
        return storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), nameValuePair);
    }
}
