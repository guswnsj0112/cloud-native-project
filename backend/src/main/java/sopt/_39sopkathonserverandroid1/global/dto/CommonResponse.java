package sopt._39sopkathonserverandroid1.global.dto;
import java.util.Map;
import sopt._39sopkathonserverandroid1.global.code.*;
public record CommonResponse<T>(boolean success, String message, T data) {
    public static <T> CommonResponse<T> success(SuccessCode code, T data) { return new CommonResponse<>(true, code.getMessage(), data); }
    public static CommonResponse<Void> success(SuccessCode code) { return success(code, null); }
    public static CommonResponse<Void> error(ErrorCode code) { return new CommonResponse<>(false, code.getMessage(), null); }
    public static CommonResponse<Map<String, String>> validationError(Map<String, String> errors) { return new CommonResponse<>(false, GlobalErrorCode.INVALID_REQUEST.getMessage(), errors); }
}
