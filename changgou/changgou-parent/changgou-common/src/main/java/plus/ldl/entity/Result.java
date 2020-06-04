package plus.ldl.entity;

/**
 * 返回结果实体类
 *
 * @author ldl
 */
public class Result {

    /**
     * 是否成功
     */
    private Boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public Result() {
    }

    public static Result ok() {
        Result result = new Result();
        result.setFlag(true);
        result.code = StatusCode.OK;
        result.message = "执行成功";
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.flag = false;
        result.code = StatusCode.ERROR;
        result.message = "执行失败";
        return result;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
