package com.unibague.fitto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.unibague.fitto.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var auth : FirebaseAuth;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth

        //


        binding.btnLogin.setOnClickListener()
        {
            val gmail = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString();

            auth.signInWithEmailAndPassword(gmail,password).addOnCompleteListener{ task -> //esperar hasta que responda
                if (task.isSuccessful)
                {
                    val toast = Toast.makeText(context,"Hello World " + gmail, Toast.LENGTH_SHORT)
                    toast.show();
                }
                else{
                    val toast = Toast.makeText(context,"Incorrecto " + gmail, Toast.LENGTH_SHORT)
                    toast.show();
                }

            }




        }





        //
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
