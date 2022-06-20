package com.example.eforkids.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.eforkids.model.*

@Dao
interface EnglishDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTopic(topic: Topic)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: Word)

    @Query("SELECT * FROM Topic ORDER BY id ASC")
    fun readAllTopic(): LiveData<List<Topic>>

    @Update
    suspend fun updateTopic(topic: Topic)

    @Update
    suspend fun updateSubContent(subContent: SubContent)

    @Update
    suspend fun updateContent(content: Content)

    @Delete
    suspend fun deleteTopic(topic: Topic)

    @Delete
    suspend fun deleteWord(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWord(word: Word)

    @Query("SELECT * FROM Content ORDER BY id ASC")
    fun readAllContent(): LiveData<List<Content>>

    @Query("SELECT * FROM StoryType ORDER BY id ASC")
    fun readAllStoryType(): LiveData<List<StoryType>>

    @Query("SELECT * FROM SubType WHERE typeId = :id")
    fun readAllSubType(id: Int): LiveData<List<SubType>>

    @Query("SELECT * FROM Word WHERE topicId =:id")
    fun readWordByTopicId(id: Int): LiveData<List<Word>>

    @Query("DELETE FROM Word WHERE topicId =:id")
    fun deleteWordById(id: Int)

    @Query("SELECT * FROM SubContent WHERE idContent =:id")
    fun readSubById(id: Int): LiveData<List<SubContent>>

}