package junil.web_service.springboot.domain.posts;

import junil.web_service.springboot.service.posts.PostsService;
import junil.web_service.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스를 자동으로 실행해 준다.
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository repository;

    @Autowired
    private PostsService service;

    @After
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        repository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("likejunil@gmail.com")
                .build());

        // when
        List<Posts> postsList = repository.findAll();

        // then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() throws InterruptedException {
        // given
        Posts post = repository.save(Posts.builder()
                .title("당장")
                .content("행복합시다~!")
                .author("준일")
                .build());

        Thread.sleep(1000);
        service.update(post.getId(), PostsUpdateRequestDto.builder()
                .title("내일")
                .content("출근을 하지 않겠습니다.")
                .build());

        // when
        List<Posts> postsList = repository.findAll();
        post = postsList.get(0);

        // then
        System.out.println("생성 시각:" + post.getCreatedDate());
        System.out.println("수정 시각:" + post.getModifiedDate());
    }
}