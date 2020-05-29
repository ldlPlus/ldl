package plus.ldl.commons.pojo;


/**
 * @author ldl.plus
 * @date 2020年05月25日  15:38
 */
public class ServerInfo {

    public static final String OK = "UP";
    public static final String ERROR = "DOWN";

    /**
     * 服务名
     */
    private String appName;
    /**
     * 客户端或服务端ip
     */
    private String ip;
    /**
     * 客户端或服务端端口
     */
    private int port;
    /**
     * 服务端或客户端URL
     */
    private String url;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "ServerInfo{" +
                "appName='" + appName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerInfo that = (ServerInfo) o;

        if (port != that.port) return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = appName != null ? appName.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

}
