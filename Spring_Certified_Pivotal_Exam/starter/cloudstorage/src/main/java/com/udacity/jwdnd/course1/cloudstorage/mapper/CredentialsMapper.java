package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<CredentialsEntity> getAllCreCredentials(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    CredentialsEntity getSingleCredential(int credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url} and userid = #{userId}")
    CredentialsEntity getSingleCredentialByUrl(String url,int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username} and userid = #{userId}")
    CredentialsEntity getSingleCredentialByUsername(String username,int userId);

    @Delete(value = "DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteSingleCredential(int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username,key,password,userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(CredentialsEntity credentialsEntity);

    @Update("UPDATE CREDENTIALS SET url= #{url},username= #{username} ,key= #{key} ,password= #{password} WHERE credentialid = #{credentialId}")
    int updateCredential(CredentialsEntity credentialsEntity);


}
