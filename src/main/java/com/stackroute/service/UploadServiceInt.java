package com.stackroute.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface UploadServiceInt  {

    public void store(MultipartFile file);

    public Resource loadFile(String filename);

    public String loadData();

}
