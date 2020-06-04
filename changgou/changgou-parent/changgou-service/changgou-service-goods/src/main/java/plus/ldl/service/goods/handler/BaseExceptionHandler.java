package plus.ldl.service.goods.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.ldl.entity.Result;
import plus.ldl.entity.StatusCode;

/**
 * @author ldl.plus
 * @date 2020年06月02日  16:24
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, "当前系统繁忙，请稍后再试");
    }
}
