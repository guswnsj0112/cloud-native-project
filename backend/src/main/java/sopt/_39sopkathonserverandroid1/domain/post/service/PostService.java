package sopt._39sopkathonserverandroid1.domain.post.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt._39sopkathonserverandroid1.domain.post.dto.request.PostRequest;
import sopt._39sopkathonserverandroid1.domain.post.dto.response.PostResponse;
import sopt._39sopkathonserverandroid1.domain.post.entity.Post;
import sopt._39sopkathonserverandroid1.domain.post.exception.PostNotFoundException;
import sopt._39sopkathonserverandroid1.domain.post.repository.PostRepository;
@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class PostService {
    private final PostRepository repository;
    public List<PostResponse> findPosts() { return repository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map(PostResponse::of).toList(); }
    public PostResponse readPost(Long id) { return PostResponse.of(findByIdOrThrow(id)); }
    @Transactional public PostResponse createPost(PostRequest request) { return PostResponse.of(repository.saveAndFlush(new Post(request.title(), request.content()))); }
    @Transactional public PostResponse updatePost(Long id, PostRequest request) { Post post = findByIdOrThrow(id); post.update(request.title(), request.content()); repository.flush(); return PostResponse.of(post); }
    @Transactional public void deletePost(Long id) { repository.delete(findByIdOrThrow(id)); }
    private Post findByIdOrThrow(Long id) { return repository.findById(id).orElseThrow(PostNotFoundException::new); }
}
