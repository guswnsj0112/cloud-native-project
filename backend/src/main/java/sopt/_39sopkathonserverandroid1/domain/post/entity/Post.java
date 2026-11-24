package sopt._39sopkathonserverandroid1.domain.post.entity;
import jakarta.persistence.*;
import lombok.*;
import sopt._39sopkathonserverandroid1.global.entity.BaseTimeEntity;
@Entity @Getter @Table(name = "posts") @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 200) private String title;
    @Column(nullable = false, columnDefinition = "TEXT") private String content;
    public Post(String title, String content) { this.title = title; this.content = content; }
    public void update(String title, String content) { this.title = title; this.content = content; }
}
