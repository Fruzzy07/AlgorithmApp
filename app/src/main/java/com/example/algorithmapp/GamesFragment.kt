package com.example.algorithmapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)

        val btnMatchingGame = view.findViewById<Button>(R.id.btnMatchingGame)
        val btnFindErrorGame = view.findViewById<Button>(R.id.btnFindErrorGame)
        val btnSortCodeGame = view.findViewById<Button>(R.id.btnSortCodeGame)
        val btnTest = view.findViewById<Button>(R.id.btnTest)

        btnMatchingGame.setOnClickListener {
            startActivity(Intent(activity, MatchingGameActivity::class.java))
        }

        btnFindErrorGame.setOnClickListener {
            startActivity(Intent(activity, FindErrorGameActivity::class.java))
        }

        btnSortCodeGame.setOnClickListener {
            startActivity(Intent(activity, SortCodeGameActivity::class.java))
        }

        btnTest.setOnClickListener {
            startActivity(Intent(activity, TestActivity::class.java))
        }

        return view
    }
}