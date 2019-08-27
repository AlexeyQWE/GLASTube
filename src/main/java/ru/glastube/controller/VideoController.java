package ru.glastube.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.glastube.entity.Video;
import ru.glastube.repository.VideoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class VideoController {
    @Autowired
    private VideoRepository crudRep;

    @RequestMapping("/watch")
    public String VideoPage(@RequestParam("id") Integer id, Model model) {
        /*List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(crud -> {users.add(crud);});
        users.forEach(User::printInf);*/
        System.out.println(crudRep.findById(id).get());
        model.addAttribute("video", crudRep.findById(id).get());

        return "VideoPage";
    }

    @RequestMapping(value="video/{filename:.+}", method= RequestMethod.GET)
    public void getDownload(HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
        InputStream myStream = new FileInputStream("video/" + filename);
        response.addHeader("Content-disposition", "attachment;filename=" + filename);
        response.setContentType("txt/plain");
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping("/add_video")
    public ModelAndView addVideo() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
            model.setViewName("addVideo");
            //return model;
        } else {
            model.setViewName("Login");
            //return model;
        }
        return model;
    }
}
