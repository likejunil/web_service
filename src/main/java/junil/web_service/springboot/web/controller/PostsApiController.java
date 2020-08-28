package junil.web_service.springboot.web.controller;

import junil.web_service.springboot.service.posts.PostsService;
import junil.web_service.springboot.web.dto.PostsResponseDto;
import junil.web_service.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
/**
 * Spring 은 3가지 방법으로 Bean 을 주입한다.
 * 1.)Constructor, 2.)Setter, 3.)@Autowired
 * 다음 변수는 @RequiredArgsConstructor 에 의해 Bean 주입이 처리되고 있다.
 */
    private final PostsService service;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return service.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
