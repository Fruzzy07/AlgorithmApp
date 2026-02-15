package com.example.algorithmapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class AlgorithmsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_algorithms, container, false)

        val btnLinear = view.findViewById<Button>(R.id.btnLinear)
        val btnBranching = view.findViewById<Button>(R.id.btnBranching)
        val btnCyclic = view.findViewById<Button>(R.id.btnCyclic)

        btnLinear.setOnClickListener {
            val intent = Intent(activity, AlgorithmDetailActivity::class.java)
            intent.putExtra("TYPE", "linear")
            startActivity(intent)
        }

        btnBranching.setOnClickListener {
            val intent = Intent(activity, AlgorithmDetailActivity::class.java)
            intent.putExtra("TYPE", "branching")
            startActivity(intent)
        }

        btnCyclic.setOnClickListener {
            val intent = Intent(activity, AlgorithmDetailActivity::class.java)
            intent.putExtra("TYPE", "cyclic")
            startActivity(intent)
        }

        return view
    }
}