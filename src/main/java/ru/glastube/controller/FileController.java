package ru.glas***.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.glas***.form.UploadForm;

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

    @RequestMapping("/rest/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {

        System.out.println("Description:" + form.getDescription());

        String result = null;
        try {

            result = this.saveUploadedFiles(form.getFile());

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
    private String saveUploadedFiles(MultipartFile file) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();


        if (!file.isEmpty()) {
            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("<br/>");
        }

        return sb.toString();
    }
}
