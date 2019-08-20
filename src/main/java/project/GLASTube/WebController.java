package project.GLASTube;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String indexStart(Model model) {
        model.addAttribute("name", "Welcome, You!");

        return "indexStart";
    }
    @RequestMapping("/sign_up")
    public String s(Model model) {
        model.addAttribute("name", "Sign Up");

        return "s";
    }
}
