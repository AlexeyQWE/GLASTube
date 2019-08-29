package ru.glas***.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.glas***.repository.VideoRepository;
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
    private VideoRepository crudRep;

    @Test
    public void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(controller).isNotNull();
    }

    @Test
    public void register() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/register?login=lex&password=qwerty")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }
    @Test
    public void newLogin() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/newLogin?newLogin=alex32&login=lex")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void newPassword() throws Exception {

        String expected = "";

        this.mockMvc.perform(get("http://localhost:8090/newPassword?newPassword=qwe&Password=qwerty&login=lex")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void signUp() throws Exception {

        String expected = "container";

        this.mockMvc.perform(get("http://localhost:8090/sign_up")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void indexStart() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Search")));
    }

    @Test
    public void signUpSignOut() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/signup_signout")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Please sign up")));

    }

    @Test
    public void signInMyProfile() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/signin_myprofile")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ready")));
    }

    @Test
    public void settings() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/settings")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ready")));
    }

    @Test
    public void addVideo() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/add_video")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ready")));
    }

    @Test
    public void SearchPage() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/resultSearch?text=lol")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Search video")));
    }
}
