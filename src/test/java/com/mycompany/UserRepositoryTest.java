package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test

    public void testAddNew(){
        User user = new User();
        user.setEmail("rak010193@gmail.com");
        user.setPassword("rakesh12345");
        user.setFirstname("rakesh");
        user.setLastname("kumar");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testUpdate(){
        Integer userId=64;
        Optional<User> optionalUser= repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
    }
    @Test
    public void testGet(){
        Integer userId=64;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public  void testDelete(){
      Integer userId=64;
      repo.deleteById(userId);
      Optional<User> optionalUser = repo.findById(userId);
      Assertions.assertThat(optionalUser).isNotPresent();
    }
}
