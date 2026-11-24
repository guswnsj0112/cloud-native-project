package sopt._39sopkathonserverandroid1.domain.post.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import sopt._39sopkathonserverandroid1.domain.post.entity.Post;
public record PostResponse(
    @Schema(description = "게시글 ID", example = "1") Long id,
    @Schema(description = "제목", example = "안녕하세요") String title,
    @Schema(description = "내용", example = "첫 번째 게시글입니다.") String content,
    @Schema(description = "작성 시각") LocalDateTime createdAt,
    @Schema(description = "수정 시각") LocalDateTime updatedAt
) { public static PostResponse of(Post post) { return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt()); } }
