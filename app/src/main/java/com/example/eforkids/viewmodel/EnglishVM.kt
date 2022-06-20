package com.example.eforkids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.eforkids.db.EnglishDB
import com.example.eforkids.model.*
import com.example.eforkids.repository.EnglishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnglishVM(application: Application) : AndroidViewModel(application) {
    private val englishDAO = EnglishDB.getDatabase(application).getEnglishDao()
    private val repository = EnglishRepository(englishDAO)
    val readAllTopic: LiveData<List<Topic>> = repository.readAllTopic
    val readAllStoryType: LiveData<List<StoryType>> = repository.readAllStoryType
    val readAllContent: LiveData<List<Content>> = repository.readAllContent
    fun readAllSubType(id: Int): LiveData<List<SubType>> = repository.readAllSubType(id)
    fun readWordByTopicId(id: Int): LiveData<List<Word>> = repository.readWordByTopicId(id)
    fun readSubContentById(id: Int): LiveData<List<SubContent>> = repository.readSubContentById(id)


    fun addWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(word)
        }
    }

    fun deleteWordById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWordById(id)
        }
    }

    fun deleteWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWord(word)
        }
    }

    fun updateSubContent(subContent: SubContent){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSubContent(subContent)
        }
    }

    fun updateContent(content: Content){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContent(content)
        }
    }

    fun updateWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }

    fun addTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTopic(topic)
        }
    }

    fun updateTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTopic(topic)
        }
    }

    fun deleteTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTopic(topic)
        }
    }

}