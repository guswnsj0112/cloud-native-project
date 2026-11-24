package sopt._39sopkathonserverandroid1.domain.post.code;
import lombok.*;
import org.springframework.http.HttpStatus;
import sopt._39sopkathonserverandroid1.global.code.SuccessCode;
@Getter @RequiredArgsConstructor
public enum PostSuccessCode implements SuccessCode {
    POST_READ(HttpStatus.OK, "게시글을 조회했습니다."), POST_CREATED(HttpStatus.CREATED, "게시글을 작성했습니다."),
    POST_UPDATED(HttpStatus.OK, "게시글을 수정했습니다."), POST_DELETED(HttpStatus.OK, "게시글을 삭제했습니다.");
    private final HttpStatus status; private final String message;
}
