package me.mw.springboard;

import me.mw.springboard.user.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void view() throws Exception {
        Account account = new Account();
        mockMvc.perform(post("/view"));
    }
}