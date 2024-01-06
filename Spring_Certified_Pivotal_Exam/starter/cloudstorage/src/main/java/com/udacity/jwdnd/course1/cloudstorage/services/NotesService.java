package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.NotesEntity;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    private final NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<NotesEntity> getNotesByUserId(int userId){
        List<NotesEntity> notesEntities=notesMapper.getAllNotes(userId);

        return notesEntities;
    }
    public NotesEntity getNoteByid(int id){
        Optional<NotesEntity> optional= Optional.ofNullable(notesMapper.getSingleNote(id));
        NotesEntity notesEntity=optional.orElseThrow(RuntimeException::new);
        return notesEntity;

    }
    public void deleteNotebyid(int fileId){
        notesMapper.deleteSingleNote(fileId);
    }

    public int newNote(int userId ,NotesEntity notesEntity){
    notesEntity.setUserId(userId);
        return notesMapper.insert(new NotesEntity(null, notesEntity.getNoteTitle(),
                notesEntity.getNoteDescription(), notesEntity.getUserId()));
    }
    public int updateNote(NotesEntity notesEntity){
        notesMapper.updateNote(notesEntity);


     return 1;
    }




}
