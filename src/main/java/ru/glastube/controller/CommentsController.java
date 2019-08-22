package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glas***.entity.Comments;
import ru.glas***.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;


    @RequestMapping("/all_comments")
    public List<Comments> getComments() {
        List<Comments> cms = new ArrayList<>();
        commentRepository.findAll().forEach(cms::add);
        return cms;
    }

    @RequestMapping("/add_comment")
    public Comments addComment(@RequestParam("id_user") Integer id_user, @RequestParam("date") Long date,
                               @RequestParam("text") String text){
        System.out.println(id_user + " " + date + " " + text);
        return commentRepository.save(new Comments(id_user, date, text));
    }
}
