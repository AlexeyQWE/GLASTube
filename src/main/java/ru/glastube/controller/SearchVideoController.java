package ru.glastube.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glastube.repository.VideoRepository;

@RestController
public class SearchVideoController {
    @Autowired
    private VideoRepository crudRep;

    @RequestMapping("/resultSearch")
    public String searchVideo(@RequestParam("text") String text, Model model){
        crudRep.findAll();

        return "indexStart";
    }

}
