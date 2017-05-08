package xzfm.common.boot.exception;

/**
 * Created by wangxizhong on 2016/10/28.
 */
public class ServiceException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }
}
