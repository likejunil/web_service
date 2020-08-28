package junil.web_service.springboot.web.dto;

import junil.web_service.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PostsApiController 에서 사용한다. 따라서 default 생성자가 필요하다.
 * client 가 http body 에 담아 보낸 json 이 spring 에 의해 PostsSaveRequestDto 으로 변환된다.
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
