package xzfm.common.boot.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xzfm.common.boot.exception.AssertException;
import xzfm.common.boot.exception.ServiceException;


/**
 * Created by wangxizhong on 16/12/17.
 */
@ControllerAdvice
public class EnableControllerAdvice {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData<String> handleServiceException(ServiceException e) {
        return ResponseData.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AssertException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData<String> handleAssertException(AssertException e) {
        return ResponseData.error(400, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData<String> handleRunTimeException(RuntimeException e) {
        return ResponseData.error(500, e.getMessage());
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData<String> handleThrowableException(Throwable e) {
        return ResponseData.error(500, e.getMessage());
    }

}
