package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
// 실제 데이터베이스에 대해 테스트를 실행하기위해 (테스트DB 자동 구성)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // replace.NONE : 기본값대신 실제 DB에서 실행되게
@Rollback(value = false)    // DB에서 데이터를 커밋한 상태로 두기위해
public class UserRepositoryTests {
    // @Autowired???? 참조?
    @Autowired private UserRepository repo;

    // 사용자 생성 (행 생성)
    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("alex.stevenson@gmail.com");
        user.setPassword("alex123456");
        user.setFirstName("Alex");
        user.setLastName("Stevenson");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    // 사용자들 목록 출력
    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    // 특정 사용자 정보 업데이트 (첫번째 user의 비밀번호 변경)
    @Test
    public void testUpdate() {
        // id로 사용자 개체 읽어옴
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser. getPassword()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        // select userId = 2
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());     // id=2인 user의 정보를 출력함
    }

    @Test
    public void testDelete() {
        // delete user by id
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
