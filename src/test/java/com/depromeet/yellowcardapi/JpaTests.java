package com.depromeet.yellowcardapi;

import com.depromeet.yellowcardapi.user.domain.User;
import com.depromeet.yellowcardapi.user.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndFind() {
        Long id = 1L;
        String name = "조성빈";
        String description = "안녕하세요.";

        User user = new User(id, name, null, null, description);
        userRepository.save(user);

        User savedUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        assertEquals(savedUser.getName(), name);
        assertEquals(savedUser.getDescription(), description);
    }
}
