package sopt._39sopkathonserverandroid1.global.exception;
import lombok.Getter;
import sopt._39sopkathonserverandroid1.global.code.ErrorCode;
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    public BusinessException(ErrorCode errorCode) { super(errorCode.getMessage()); this.errorCode = errorCode; }
}
