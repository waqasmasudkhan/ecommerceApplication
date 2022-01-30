package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;


    @Test
    public void create_user_happy_path(){
        CreateUserRequest r = new CreateUserRequest();
        User user = new User();
        r.setUsername("waqas");
        r.setPassword("testPassword");
        r.setConfirmPassword("testPassword");
        user.setUsername(r.getUsername());
        user.setPassword(r.getPassword());
        Cart cart = new Cart();
        cart.setUser(user);
        doReturn(cart).when(cartRepository).save(any(Cart.class));
        doReturn(user).when(userRepository).save(any(User.class));
        doReturn("thisIsHashed").when(encoder).encode("testPassword");


        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        User u = response.getBody();
        assertEquals(0,u.getId());
        assertEquals("waqas",u.getUsername());
        assertEquals("thisIsHashed",u.getPassword());


    }
}
