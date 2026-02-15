package com.example.algorithmapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var btnLogout: Button
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnLogout = view.findViewById(R.id.btnLogout)
        tvUsername = view.findViewById(R.id.tvUsername)
        tvEmail = view.findViewById(R.id.tvEmail)

        // Получаем данные пользователя из SharedPreferences
        val prefs = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "Пайдаланушы")
        val email = prefs.getString("email", "email@example.com")

        tvUsername.text = username
        tvEmail.text = email

        btnLogout.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        // Меняем только состояние сессии, данные пользователя остаются
        val prefs = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("isLoggedIn", false).apply()

        // Переход на LoginActivity
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
