package junil.web_service.springboot.service.posts;

import junil.web_service.springboot.domain.posts.Posts;
import junil.web_service.springboot.domain.posts.PostsRepository;
import junil.web_service.springboot.web.dto.PostsResponseDto;
import junil.web_service.springboot.web.dto.PostsSaveRequestDto;
import junil.web_service.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* 서비스 계층은 비즈니스를 처리하지 않는다.
 * 트랜잭션과 도메인의 순서만을 제어한다.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository repository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto updateDto) {
        Posts post = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return post.update(updateDto);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts post = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(post);
    }
}
