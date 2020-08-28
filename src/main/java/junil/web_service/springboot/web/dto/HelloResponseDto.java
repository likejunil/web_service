package junil.web_service.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 이 선언된 filed 를 포함한 생성자를 만들어 준다.
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
