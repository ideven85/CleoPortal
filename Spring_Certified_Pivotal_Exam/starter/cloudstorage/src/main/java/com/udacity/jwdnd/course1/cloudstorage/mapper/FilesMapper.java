package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.FilesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FilesEntity getSingleFile(int fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<FilesEntity> getFiles(int userid);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} and userid = #{userId}")
    FilesEntity getSingleFileByfileName(String filename, int userId);

    @Delete(value = "DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteSingleFile(int fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert (FilesEntity filesEntity);




}
