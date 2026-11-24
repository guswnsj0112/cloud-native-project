package sopt._39sopkathonserverandroid1.global.exception;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sopt._39sopkathonserverandroid1.global.code.GlobalErrorCode;
import sopt._39sopkathonserverandroid1.global.dto.CommonResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> business(BusinessException ex) { return ResponseEntity.status(ex.getErrorCode().getStatus()).body(CommonResponse.error(ex.getErrorCode())); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(CommonResponse.validationError(errors));
    }
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> invalid(Exception ex) { return ResponseEntity.badRequest().body(CommonResponse.error(GlobalErrorCode.INVALID_REQUEST)); }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unexpected(Exception ex) { return ResponseEntity.internalServerError().body(CommonResponse.error(GlobalErrorCode.INTERNAL_ERROR)); }
}
