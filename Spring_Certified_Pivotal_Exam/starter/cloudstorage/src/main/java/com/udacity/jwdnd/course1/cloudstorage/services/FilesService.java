package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.FilesEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import org.apache.ibatis.type.BlobInputStreamTypeHandler;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FilesService {

    private final FilesMapper filesMapper;

    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;

    }
    public List<FilesEntity> getFilesByUserId(int userId){
        List<FilesEntity> fileslist=filesMapper.getFiles(userId);

        return fileslist;
    }
    public FilesEntity getSingleFileById(int fileId){
        FilesEntity file=filesMapper.getSingleFile(fileId);

        return file;
    }
    public boolean checkFileSize(MultipartFile multipartFile){
        if(multipartFile.getSize()>1000000){
            return false;
        }
        else
        return true;

    }

    public Boolean FileValidation(MultipartFile multipartFile, int userId){
        if(filesMapper.getSingleFileByfileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()),userId)!=null){
            return false;
        }
    return true;}

    public int uploadFile(int userId , MultipartFile multipartFile) throws IOException {
        FilesEntity filesEntity=new FilesEntity();
        filesEntity.setUserid(userId);
        filesEntity.setFilename(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        filesEntity.setContenttype(multipartFile.getContentType());
        filesEntity.setFilesize(new StringBuilder().append(multipartFile.getSize()).toString());
        filesEntity.setFiledata(multipartFile.getBytes());

        filesMapper.insert(filesEntity);
        return 1;
    }
    public void deletefilebyid(int fileId){
        filesMapper.deleteSingleFile(fileId);
    }

}
