package com.qx.accounting.exception;

import com.qx.accounting.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZedQ
 * @date 2022年10月01日 12:57
 * @Description: 业务异常处理器
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    //捕捉到 之后直接返回result
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se) {
        return new Result().error(se.getMessage());
    }
}
