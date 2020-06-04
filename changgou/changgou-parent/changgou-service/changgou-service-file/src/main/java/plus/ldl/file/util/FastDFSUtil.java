package plus.ldl.file.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import plus.ldl.file.domain.FastDFSFile;

import java.io.ByteArrayInputStream;
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

        //创建一个Tracker访 问的客户端对象TrackerClient
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = new TrackerClient().getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //通过StorageClient访问Storage,实现文件上传,并且获取文件上传后的存储信息
        return storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), nameValuePair);
    }

    /**
     * 获取文件信息
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        //创建一个Tracker访 问的客户端对象TrackerClient
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = new TrackerClient().getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static ByteArrayInputStream downFile(String groupName, String remoteFileName) throws IOException, MyException {
        //创建一个Tracker访 问的客户端对象TrackerClient
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = new TrackerClient().getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] downloadFile = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(downloadFile);
    }

    /**
     * 文件删除
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static String deleteFile(String groupName, String remoteFileName) throws IOException, MyException {
        //创建一个Tracker访 问的客户端对象TrackerClient
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = new TrackerClient().getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        int deleteFile = storageClient.delete_file(groupName, remoteFileName);
        if (deleteFile == 1) {
            return "success";
        }
        return "error";
    }

    /**
     * 获取storage信息
     *
     * @return
     * @throws IOException
     */
    public static StorageServer getStorage() throws IOException {
        //创建一个Tracker访 问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 获取Storage的IP和端口信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        //创建一个Tracker访 问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    public static String getTrackerInfo() throws IOException {
        TrackerServer trackerServer = new TrackerClient().getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int trackerHttpPort = ClientGlobal.getG_tracker_http_port();
        return "http://" + ip + ":" + trackerHttpPort;
    }
}
