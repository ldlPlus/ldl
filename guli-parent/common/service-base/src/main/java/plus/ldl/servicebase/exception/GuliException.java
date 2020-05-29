package plus.ldl.servicebase.exception;

/**
 * @author ldl.plus
 * @date 2020年05月29日  21:54
 */
public class GuliException extends RuntimeException {

    private Integer code;
    private String msg;

    public GuliException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GuliException() {
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuliException that = (GuliException) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return msg != null ? msg.equals(that.msg) : that.msg == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
