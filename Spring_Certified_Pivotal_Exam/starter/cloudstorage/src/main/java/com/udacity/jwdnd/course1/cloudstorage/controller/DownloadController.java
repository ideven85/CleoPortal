package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.FilesEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DownloadController {

    private final FilesService filesService;


    public DownloadController(FilesService filesService) {
        this.filesService = filesService;

    }
    @GetMapping(value = "/downloadfile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(value = "id") Long id) throws Exception
    {
        try
        {
            FilesEntity filesEntity = filesService.getSingleFileById(Math.toIntExact(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(filesEntity.getContenttype()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filesEntity.getFilename() + "\"")
                    .body(new ByteArrayResource(filesEntity.getFiledata()));
        }
        catch(Exception e)
        {
            throw new Exception("Error downloading file");
        }
    }



}




