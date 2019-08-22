package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.glastube.entity.Video;
import ru.glastube.repository.VideoRepository;

import java.util.Optional;

@Controller
public class VideoController {
    @Autowired
    private VideoRepository crudRep;

    @RequestMapping("/test_video")
    public String VideoPage(@RequestParam("id") Integer id, Model model) {
        /*List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(crud -> {users.add(crud);});
        users.forEach(User::printInf);*/
        System.out.println(crudRep.findById(id).get());
        model.addAttribute("video", crudRep.findById(id).get());

        return "VideoPage";
    }


}
