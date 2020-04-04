package kr.co.kwan.eatgo.interfaces;


import kr.co.kwan.eatgo.application.EmailNotExistedException;
import kr.co.kwan.eatgo.application.PasswordWrongException;
import kr.co.kwan.eatgo.application.UserService;
import kr.co.kwan.eatgo.domain.User;
import kr.co.kwan.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createWithValidAttribute() throws Exception {
        User mockUser = User.builder()
                            .id(1004L)
                            .name("Tester")
                            .level(1L)
                            .password("test")
                            .build();

        given(userService.authenticate("test@test.com", "test")).willReturn(mockUser);

        given(jwtUtil.createToken(1004L, "Tester", null)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}"))
                );

        verify(userService).authenticate(eq("test@test.com"), eq("test"));

    }

    @Test
    public void createWithInValidAttribute() throws Exception {
        given(userService.authenticate("test@test.com", "fail"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"fail\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("test@test.com"), eq("fail"));


    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("fail@test.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"fail@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("fail@test.com"), eq("test"));


    }

    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("test@test.com", "fail"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"fail\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("test@test.com"), eq("fail"));

    }

    @Test
    public void createRestaurantOwner() throws Exception {
        User mockUser = User.builder()
                            .id(1004L)
                            .name("Tester")
                            .level(50L)
                            .password("test")
                            .restaurantId(369L)
                            .build();

        given(userService.authenticate("test@test.com", "test")).willReturn(mockUser);

        given(jwtUtil.createToken(1004L, "Tester", 369L)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}"))
                );

        verify(userService).authenticate(eq("test@test.com"), eq("test"));

    }
}