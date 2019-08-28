package ru.glas***.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
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
    @MockBean
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
    public void signUpSignOut() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new AnonymousAuthenticationToken("key", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
        SecurityContextHolder.setContext(securityContext);
        WebController webController = new WebController();
        ModelAndView modelAndView =  webController.signUpSignOut();
        Assert.assertEquals("SignUpPage", modelAndView.getViewName());
    }

    @Test
    public void signInMyProfile() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new AnonymousAuthenticationToken("key", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
        SecurityContextHolder.setContext(securityContext);
        WebController webController = new WebController();
        ModelAndView modelAndView =  webController.signInMyProfile();
        Assert.assertEquals("Login", modelAndView.getViewName());
    }

    @Test
    public void settings() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new AnonymousAuthenticationToken("key", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
        SecurityContextHolder.setContext(securityContext);
        WebController webController = new WebController();
        ModelAndView modelAndView =  webController.settings();
        Assert.assertEquals("Login", modelAndView.getViewName());
    }

   /* @Test
    public void VideoPage() throws Exception {
        this.mockMvc.perform(get("http://localhost:8090/watch?id=3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("dropdownMenuButton")));
    }*/

    @Test
    public void addVideo() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new AnonymousAuthenticationToken("key", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
        SecurityContextHolder.setContext(securityContext);
        VideoController videoController = new VideoController();
        ModelAndView modelAndView =  videoController.addVideo();
        Assert.assertEquals("Login", modelAndView.getViewName());
    }
}
