package com.example.notesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.model.NoteRequest
import com.example.notesapp.model.NoteResponse
import com.example.notesapp.utils.Constants.TAG
import com.example.notesapp.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(){

    private  var _binding:FragmentNotesBinding? = null
    private  val binding get() = _binding!!
    private var note:NoteResponse? = null
    private val noteViewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        bindHandler()
        bindObserver()

    }

    private fun bindObserver() {
        noteViewModel.statusLiveData.observe(viewLifecycleOwner,{
            when(it){
                is NetworkResult.Error -> {
                    val errorMessage = it.message // Assuming the exception has a message property
                    Toast.makeText(requireContext(), "Failed to save notes: $errorMessage", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    findNavController().popBackStack()
                }
            }
        })


    }

    private fun bindHandler() {
        binding.btnDelete.setOnClickListener{
            note?.let {
                noteViewModel.deleteNotes(it._id)
            }
        }
        binding.btnSubmit.setOnClickListener{
            val title = binding.txtTitle.text.toString()
            val description = binding.txtDescription.text.toString()
            val noteRequest = NoteRequest(description,title)
            if(note == null){
                noteViewModel.createNotes(noteRequest)
            }
            else {
                noteViewModel.updateNotes(note!!._id,noteRequest)
            }
        }
    }

    private fun setInitialData() {
        val jsonNote = arguments?.getString("note")
        if(jsonNote!= null){
            note = Gson().fromJson(jsonNote,NoteResponse::class.java)
            note?.let {
                binding.txtTitle.setText(it.title)
                binding.txtDescription.setText(it.description)
            }
        }
        else {
            binding.addEditText.text = "Add Note"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}