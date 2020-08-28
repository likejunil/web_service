package junil.web_service.springboot.domain.posts;

import junil.web_service.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity class 에서는 절대 Setter 를 만들지 않는다.
 * 필드의 값 변경이 필요한 경우 목적과 의도를 나타낸 method 를 추가한다.
 * 도메인 계층과 웹 계층은 분리되어야 한다.
 * Entity 는 결코 웹 계층(Request/Response)에서 사용하지 않는다.
 * 반드시 Dto 로 변환해서 외부와 연결해야 한다.
 * Dto 는 화면과, Entity 는 테이블과 연결되기 때문이다.
 * 화면 수정이 테이블에 영향을 주어서는 안된다.
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long update(String title, String content) {
        this.title = title;
        this.content = content;
        return this.id;
    }
}
