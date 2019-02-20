package com.stackroute.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService implements UploadServiceInt {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

//    public Stream<Path> loadAll() {
//        Path rootLocation1 = Paths.get("upload-dir");
//        try {
//            return Files.walk(rootLocation1, 1).filter(path -> !path.equals(rootLocation1)).map(rootLocation1::relativize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Stream<Path> loadAll() throws IOException {
//        try {
//            return Files.walk(this.rootLocation, 1)
//                    .filter(path -> !path.equals(this.rootLocation))
//                    .map(this.rootLocation::relativize);
//        }
//        catch (IOException e) {
//            throw new IOException("Failed to read stored files", e);
//        }
//    }
//
//    public void deleteAll() throws IOException {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }


    public String loadData() {

        String content=null;
        try
        {
            File file= ResourceUtils.getFile("upload-dir/springontology.csv");
            content=new String(Files.readAllBytes(file.toPath()));
        }
        catch(Exception e)
        {

        }
        return content;

    }
}