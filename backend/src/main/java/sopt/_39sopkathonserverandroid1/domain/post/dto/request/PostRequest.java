package sopt._39sopkathonserverandroid1.domain.post.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
public record PostRequest(
    @Schema(description = "제목", example = "안녕하세요") @NotBlank(message = "제목을 입력해 주세요.") @Size(max = 200, message = "제목은 200자 이하여야 합니다.") String title,
    @Schema(description = "내용", example = "첫 번째 게시글입니다.") @NotBlank(message = "내용을 입력해 주세요.") String content
) {}
