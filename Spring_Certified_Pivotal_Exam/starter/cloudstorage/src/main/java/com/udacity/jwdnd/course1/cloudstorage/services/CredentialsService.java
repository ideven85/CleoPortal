package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;


    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }
    public List<CredentialsEntity> getAllCredential(int userId){
        List<CredentialsEntity>credentialsEntities=credentialsMapper.getAllCreCredentials(userId);
        for(CredentialsEntity credentialsEntity: credentialsEntities){
            String decryptPass=encryptionService.decryptValue(credentialsEntity.getPassword(),credentialsEntity.getKey());
            credentialsEntity.setKey(decryptPass);
        }

        return credentialsEntities;
    }
    public CredentialsEntity getCredentialByid(int id){
        Optional<CredentialsEntity> optional= Optional.ofNullable(credentialsMapper.getSingleCredential(id));
        CredentialsEntity credentialsEntity=optional.orElseThrow(RuntimeException::new);
        return credentialsEntity;

    }
    public void deleteCredentialById(int credentialId){
        credentialsMapper.deleteSingleCredential(credentialId);
    }

    public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
    public Boolean credentialValidation(CredentialsEntity credentialsEntity,int userId){
       if(credentialsMapper.getSingleCredentialByUrl(credentialsEntity.getUrl(),userId)!=null){
           return false;
       }

    return true;
    }

    public int newCredential(int userId , CredentialsEntity credentialsEntity){

        credentialsEntity.setUserId(userId);
        credentialsEntity.setKey(alphaNumericString(6));
       String pass= encryptionService.encryptValue(credentialsEntity.getPassword(), credentialsEntity.getKey());

        return credentialsMapper.insert(new CredentialsEntity(null, credentialsEntity.getUrl(),
                credentialsEntity.getUsername(), credentialsEntity.getKey(),pass,credentialsEntity.getUserId()));
    }
    public int updateCredential(CredentialsEntity credentialsEntity){
        credentialsEntity.setKey(alphaNumericString(6));
        String pass= encryptionService.encryptValue(credentialsEntity.getPassword(), credentialsEntity.getKey());
        credentialsEntity.setPassword(pass);
        return credentialsMapper.updateCredential(credentialsEntity);
    }



}
