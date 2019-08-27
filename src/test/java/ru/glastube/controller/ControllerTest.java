package ru.glastube.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private UserController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(controller).isNotNull();
    }

    @Test
    public void register() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/register?nickname=lexa&login=lex3221&password=qwerty")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));

    }
    @Test
    public void newLogin() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/newLogin?newLogin=alex3212313&login=lex322")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));

    }

    @Test
    public void newPassword() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/newPassword?newPassword=qwerty&Password=qwe&login=lex")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));

    }
    @Test
    public void SignUp() throws Exception {

        String expected = "container";

        this.mockMvc.perform(get("http://localhost:8090/sign_up")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));

    }
    @Test
    public void comments() throws Exception {

        String expected = "comments";

        this.mockMvc.perform(get("http://localhost:8090/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));

    }
}
