package sopt._39sopkathonserverandroid1.domain.post.controller;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import sopt._39sopkathonserverandroid1.domain.post.dto.request.PostRequest;
import sopt._39sopkathonserverandroid1.domain.post.dto.response.PostResponse;
import sopt._39sopkathonserverandroid1.global.dto.CommonResponse;
public interface PostApi {
    @Operation(summary = "게시글 목록 조회") ResponseEntity<CommonResponse<List<PostResponse>>> findPosts();
    @Operation(summary = "게시글 조회") ResponseEntity<CommonResponse<PostResponse>> readPost(Long id);
    @Operation(summary = "게시글 작성") ResponseEntity<CommonResponse<PostResponse>> createPost(PostRequest request);
    @Operation(summary = "게시글 수정") ResponseEntity<CommonResponse<PostResponse>> updatePost(Long id, PostRequest request);
    @Operation(summary = "게시글 삭제") ResponseEntity<CommonResponse<Void>> deletePost(Long id);
}
