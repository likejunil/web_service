package junil.web_service.springboot.web;

import junil.web_service.springboot.domain.posts.Posts;
import junil.web_service.springboot.domain.posts.PostsRepository;
import junil.web_service.springboot.web.dto.PostsSaveRequestDto;
import junil.web_service.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @WebMvcTest 의 경우 JPA 기능이 작동하지 않는다.
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void 게시글_등록하기() {
        // given
        String title = "행복";
        String content = "아무말도 하지 않겠다.";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("준일")
                .build();

        String url = "http://localhost:" + port + "/api/v1//posts";

        // when
        ResponseEntity<Long> responseEntity =
                restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> postsList = repository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_수정하기() {
        // given
        Posts savedPost = repository.save(Posts.builder()
                .title("행복")
                .content("건강과 깨달음 그리고 관계")
                .author("준일")
                .build());

        Long updatedId = savedPost.getId();
        String updatedTitle = "물질적 행복";
        String updatedContent = "일단 로또 1등";
        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title(updatedTitle)
                .content(updatedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        HttpEntity<PostsUpdateRequestDto> requestEntity =
                new HttpEntity<>(updateRequestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = repository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(updatedTitle);
        assertThat(postsList.get(0).getContent()).isEqualTo(updatedContent);
    }
}