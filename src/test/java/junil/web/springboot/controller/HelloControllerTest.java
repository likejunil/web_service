package junil.web.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // Springboot 와 JUnit 사이의 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class) // Controller 테스트를 위해 사용한다.
public class HelloControllerTest {
    @Autowired // Spring 이 관리하는 Bean 을 주입 받는다.
    private MockMvc mvc; // Web API 를 테스트할 때 사용한다. Spring MVC 테스트의 시작점이다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // header
                .andExpect(content().string(hello)); // body
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "권준일";
        int amount = 12345;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}