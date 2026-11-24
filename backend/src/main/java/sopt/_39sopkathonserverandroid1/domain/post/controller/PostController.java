package sopt._39sopkathonserverandroid1.domain.post.controller;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt._39sopkathonserverandroid1.domain.post.code.PostSuccessCode;
import sopt._39sopkathonserverandroid1.domain.post.dto.request.PostRequest;
import sopt._39sopkathonserverandroid1.domain.post.dto.response.PostResponse;
import sopt._39sopkathonserverandroid1.domain.post.service.PostService;
import sopt._39sopkathonserverandroid1.global.dto.CommonResponse;
@RestController @RequestMapping("/v1/posts") @RequiredArgsConstructor
public class PostController implements PostApi {
    private final PostService service;
    @GetMapping public ResponseEntity<CommonResponse<List<PostResponse>>> findPosts() { return ResponseEntity.ok(CommonResponse.success(PostSuccessCode.POST_READ, service.findPosts())); }
    @GetMapping("/{id}") public ResponseEntity<CommonResponse<PostResponse>> readPost(@PathVariable Long id) { return ResponseEntity.ok(CommonResponse.success(PostSuccessCode.POST_READ, service.readPost(id))); }
    @PostMapping public ResponseEntity<CommonResponse<PostResponse>> createPost(@Valid @RequestBody PostRequest request) { return ResponseEntity.status(PostSuccessCode.POST_CREATED.getStatus()).body(CommonResponse.success(PostSuccessCode.POST_CREATED, service.createPost(request))); }
    @PutMapping("/{id}") public ResponseEntity<CommonResponse<PostResponse>> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request) { return ResponseEntity.ok(CommonResponse.success(PostSuccessCode.POST_UPDATED, service.updatePost(id, request))); }
    @DeleteMapping("/{id}") public ResponseEntity<CommonResponse<Void>> deletePost(@PathVariable Long id) { service.deletePost(id); return ResponseEntity.ok(CommonResponse.success(PostSuccessCode.POST_DELETED)); }
}
