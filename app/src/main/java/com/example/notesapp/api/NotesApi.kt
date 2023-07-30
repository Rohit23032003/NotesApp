package com.example.notesapp.api

import android.util.Log
import com.example.notesapp.model.NoteRequest
import com.example.notesapp.model.NoteResponse
import retrofit2.Response
import retrofit2.http.*

interface NotesApi {

    @GET("/notes")
    suspend fun getNotes():Response<List<NoteResponse>>

    @POST("/notes")
    suspend fun createNote(@Body noteRequest: NoteRequest):Response<NoteResponse>

    @PATCH("/notes/{noteId}")
    suspend fun updateNote(@Path("noteId") nodeId:String ,@Body noteRequest: NoteRequest):Response<NoteResponse>

    @DELETE("/notes/{noteId}")
    suspend fun deleteNote(@Path("noteId") noteId:String):Response<NoteResponse>

}