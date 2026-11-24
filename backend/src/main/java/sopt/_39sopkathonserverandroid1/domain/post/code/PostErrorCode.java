package sopt._39sopkathonserverandroid1.domain.post.code;
import lombok.*;
import org.springframework.http.HttpStatus;
import sopt._39sopkathonserverandroid1.global.code.ErrorCode;
@Getter @RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
    private final HttpStatus status; private final String message;
}
