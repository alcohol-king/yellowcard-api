package com.depromeet.yellowcardapi.utils;

import com.depromeet.yellowcardapi.domain.User;
import com.depromeet.yellowcardapi.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.lang.Long.valueOf;

@Component
@Profile({ "dev", "default" })
public class CreateDummyUser implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setId(valueOf(1));
        userRepository.save(user);
    }
}
