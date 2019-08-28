package ru.glastube.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glastube.entity.Video;
import ru.glastube.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchVideoController {
    @Autowired
    private VideoRepository crudRep;

    @RequestMapping("/resultSearch")
    public List<Video> searchVideo(@RequestParam("text") String text, Model model){
        int a = 0;
        List<Video> videos = new ArrayList<>();
        System.out.println(videos.size());
        for (Video video: crudRep.findAll()){
            a = 1;
            if (video.getName().contains(text)) {
                videos.add(video);
                System.out.println(video.getName());
            }
        }
        if (a == 0) {model.addAttribute("notFound",a);}
        return videos;
    }
}
