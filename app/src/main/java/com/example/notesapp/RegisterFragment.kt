package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentRegisterBinding
import com.example.notesapp.model.UserRequest
import com.example.notesapp.utils.NetworkResult
import com.example.notesapp.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding :FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var  tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)

        if(tokenManager.getToken()!=null){
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }

        return  binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener{
            val validationResult = validateUserInput()
            if(validationResult.first){
                authViewModel.registerUser(getUserRequest())
            }
            else {
                binding.txtError.text = validationResult.second
            }
        }
        binding.btnLogin.setOnClickListener {
           findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        bindObserver()
    }

    private fun validateUserInput():Pair<Boolean , String>{
        val userRequest = getUserRequest()
        return authViewModel.validateCredential(userRequest.username,userRequest.email,userRequest.password,false)
    }

    private fun getUserRequest():UserRequest{
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        val username = binding.txtUsername.text.toString()
        return UserRequest(emailAddress , password , username)
    }

    private fun bindObserver() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner,{
            binding.progressBar2.visibility = View.INVISIBLE
            binding.txtError.text = ""
            when(it){
                is NetworkResult.Success ->{
                    tokenManager.saveToken(it.data!!.token)
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }
                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }
                is NetworkResult.Loading -> {
                    binding.progressBar2.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}