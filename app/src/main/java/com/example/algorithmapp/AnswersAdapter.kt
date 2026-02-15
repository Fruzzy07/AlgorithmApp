package com.example.algorithmapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnswersAdapter(private val questions: List<QuestionWithExplanation>) :
    RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {

    data class QuestionWithExplanation(
        val question: String,
        val options: List<String>,
        val correctAnswerIndex: Int,
        val explanation: String
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.tvQuestion.text = question.question
        holder.tvCorrectAnswer.text = "Дұрыс жауап: ${question.options[question.correctAnswerIndex]}"
        holder.tvExplanation.text = question.explanation
    }

    override fun getItemCount() = questions.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestionAnswer)
        val tvCorrectAnswer: TextView = itemView.findViewById(R.id.tvCorrectAnswer)
        val tvExplanation: TextView = itemView.findViewById(R.id.tvExplanation)
    }
}