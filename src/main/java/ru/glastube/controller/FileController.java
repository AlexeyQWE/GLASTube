package ru.glastube.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.glastube.entity.User;
import ru.glastube.entity.Video;
import ru.glastube.form.UploadForm;
import ru.glastube.repository.UserRepository;
import ru.glastube.repository.VideoRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    // Linux: /home/{user}/test
    // Windows: C:/Users/{user}/test
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/GLASTube/video";

    @Autowired
    UserRepository userRep;

    @Autowired
    VideoRepository videoRep;

    @RequestMapping("/rest/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(@RequestParam("login") String login, @ModelAttribute UploadForm form) {

        System.out.println("Name of the video:" + form.getDescription());

        String result = null;
        try {
            String hashFile = String.valueOf(new java.util.Date().hashCode());
            result = this.saveUploadedFiles(form.getFile(), hashFile);
            String name = form.getDescription();
            String filename = "video/" + hashFile;
            User user = userRep.findByLogin(login);
            Video video = new Video(name, user, filename);
            videoRep.save(video);

        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);

    }

    // Save Files
    private String saveUploadedFiles(MultipartFile file, String filename) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();


        if (!file.isEmpty()) {
//            String filename = String.valueOf(new java.util.Date().hashCode());
            String uploadFilePath = UPLOAD_DIR + "/" + filename;//file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("<br/>");
        }

        return sb.toString();
    }
}
