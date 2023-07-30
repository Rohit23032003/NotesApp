package com.example.notesapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.api.NotesApi
import com.example.notesapp.model.NoteRequest
import com.example.notesapp.model.NoteResponse
import com.example.notesapp.utils.Constants
import com.example.notesapp.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val notesApi: NotesApi){

    private val _notesLiveData = MutableLiveData<NetworkResult<List<NoteResponse>>>()
    val notesLiveData :LiveData<NetworkResult<List<NoteResponse>>>
    get() = _notesLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData :LiveData<NetworkResult<String>>
    get() = _statusLiveData


    suspend fun getNotes(){
         _notesLiveData.postValue(NetworkResult.Loading())
        val response = notesApi.getNotes()

        if(response.isSuccessful && response.body()!=null){
            _notesLiveData.postValue(NetworkResult.Success(response.body()))
        }
        else if(response.errorBody() != null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else {
            _notesLiveData.postValue(NetworkResult.Error("Something Went Wrong while getting data"))
        }
    }

    suspend fun createNote(noteRequest: NoteRequest){
        _statusLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.createNote(noteRequest)
        handleResponse(response,"Note Created")
    }

    private fun handleResponse(response: Response<NoteResponse> , message:String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(message))
        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something Went wrong while creating"))
        }
    }


    suspend fun deleteNote(noteId:String){
        _statusLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.deleteNote(noteId)
        handleResponse(response,"Note Deleted")

    }

    suspend fun updateNote(noteId:String , noteRequest: NoteRequest){
        _statusLiveData.postValue(NetworkResult.Loading())
        val response=notesApi.updateNote(noteId,noteRequest)
        handleResponse(response , "Note Updated")
    }

}