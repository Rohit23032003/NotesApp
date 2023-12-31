package com.example.notesapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Repository.NoteRepository
import com.example.notesapp.model.NoteRequest
import com.example.notesapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) :ViewModel() {


    val notesLiveData get() = noteRepository.notesLiveData
    val statusLiveData get() = noteRepository.statusLiveData

    fun getNotes(){
        viewModelScope.launch {
            noteRepository.getNotes()
        }
    }

    fun createNotes(noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.createNote(noteRequest)
        }
    }

    fun updateNotes(noteId : String , noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.updateNote(noteId , noteRequest)
        }
    }

    fun deleteNotes(noteId:String){
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }

}