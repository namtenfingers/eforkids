package com.example.eforkids.repository

import androidx.lifecycle.LiveData
import com.example.eforkids.db.EnglishDAO
import com.example.eforkids.model.*

class EnglishRepository(private val englishDAO: EnglishDAO) {
    val readAllTopic: LiveData<List<Topic>> = englishDAO.readAllTopic()
    val readAllStoryType: LiveData<List<StoryType>> = englishDAO.readAllStoryType()
    val readAllContent: LiveData<List<Content>> = englishDAO.readAllContent()
    fun readAllSubType(id: Int): LiveData<List<SubType>> = englishDAO.readAllSubType(id)
    fun readWordByTopicId(id: Int): LiveData<List<Word>> = englishDAO.readWordByTopicId(id)
    fun readSubContentById(id: Int): LiveData<List<SubContent>> = englishDAO.readSubById(id)

    suspend fun addWord(word: Word){
        englishDAO.addWord(word)
    }

    suspend fun deleteWord(word: Word){
        englishDAO.deleteWord(word)
    }

    fun deleteWordById(id: Int){
        englishDAO.deleteWordById(id)
    }

    suspend fun updateWord(word: Word){
        englishDAO.updateWord(word)
    }

    suspend fun updateSubContent(subContent: SubContent){
        englishDAO.updateSubContent(subContent)
    }

    suspend fun updateContent(content: Content){
        englishDAO.updateContent(content)
    }

    suspend fun addTopic(topic: Topic){
        englishDAO.addTopic(topic)
    }

    suspend fun updateTopic(topic: Topic){
        englishDAO.updateTopic(topic)
    }

    suspend fun deleteTopic(topic: Topic){
        englishDAO.deleteTopic(topic)
    }


}