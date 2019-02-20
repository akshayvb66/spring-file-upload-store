package com.stackroute.controller;

import com.stackroute.service.UploadService;
import com.stackroute.service.UploadServiceInt;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@CrossOrigin(origins = "*")
@Controller
public class UploadController {

    @Autowired
    UploadServiceInt uploadservice;

//    private static String UPLOAD_DIR = System.getProperty("user.home") + "/Documents";


    List<String> files = new ArrayList<String>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            System.out.println(file);
            uploadservice.store(file);
            files.add(file.getOriginalFilename());
            message = "You successfully uploaded !";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
         }

//    @GetMapping("/getallfiles")
//    public ResponseEntity<List<String>> getListFiles(Model model) {
//        List<String> fileNames = files
//                .stream().map(fileName -> MvcUriComponentsBuilder
//                        .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok().body(fileNames);
//    }

//    @GetMapping("/api/file/all")
//    public List<String> getListFiles() throws IOException {
//        return uploadservice.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
//                        "getFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/getallfiles")
//    public List<String> getListFiles() {
//        File uploadDir = new File(UPLOAD_DIR);
//
//        File[] files = uploadDir.listFiles();
//
//        List<String> list = new ArrayList<String>();
//        for (File file : files) {
//            list.add(file.getName());
//        }
//        return list;
//    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = uploadservice.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/fileread")
    public  ResponseEntity<String> getAllContent()
    {
        return new ResponseEntity<String>(uploadservice.loadData(),HttpStatus.OK);
    }
}
