package junil.web.springboot.controller.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬봄_기능_확인() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // Junit 의 assertThat() 이 아니라 assertj 의 asserThat() 을 사용함
        assertThat(dto.getName()).isEqualTo(name); // assertj 라는 테스트 검증 라이브러리 사용
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}