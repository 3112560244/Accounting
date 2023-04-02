package com.qx.accounting.exception;

import lombok.Getter;

/**
 * @author ZedQ
 * @date 2022年10月01日 12:59
 * @Description: 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }

}