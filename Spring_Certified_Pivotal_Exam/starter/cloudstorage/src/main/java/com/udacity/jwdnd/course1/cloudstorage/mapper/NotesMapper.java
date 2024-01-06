package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.NotesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<NotesEntity> getAllNotes(int userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    NotesEntity getSingleNote(int noteId);

    @Delete(value = "DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteSingleNote(int noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert (NotesEntity notesEntity);

    @Update("UPDATE NOTES SET notetitle= #{noteTitle},notedescription= #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(NotesEntity notesEntity);
}
