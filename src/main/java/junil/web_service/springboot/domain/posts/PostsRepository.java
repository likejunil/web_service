package junil.web_service.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository<Entity 클래스, PK 타입> 을 상속하는 인터페이스를 만든다.
 * 그러면 기본적인 CRUD method 가 자동으로 생성된다.
 * Entity 클래스와 Entity Repository 클래스는 같은 곳에 위치해야 한다.
 * 따라서 ${Domain} 패키지를 만들어 함께 관리하는 것이 좋다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
