package junil.web.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 자동 설정, Spring Bean 생성과 읽기가 모두 자동, program 의 시작
public class Application {
    public static void main(String[] args) {
        /* SpringApplication.run() 으로 인해 내장 Web Application Server(WAS)가 실행된다.
         * 따라서 별도로 tomcat 을 설치할 필요가 없다.
         * Springboot 에서는 내장 WAS를 사용하는 것을 권장한다.
         * 언제 어디서나 같은 환경에서 Springboot 를 배포할 수 있기 때문이다.
         * tomcat 역시 servlet 으로 이루어진 java application 이다.
         */
        SpringApplication.run(Application.class, args);
    }
}
