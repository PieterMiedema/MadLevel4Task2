package com.example.madlevel4task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Match
import kotlinx.android.synthetic.main.item_match.view.*

class HistoryAdapter (private val matches: List<Match>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(match: Match) {
            var context = itemView.context

            when (match.computerMove) {
                "rock" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.rock)
                "paper" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.paper)
                "scissors" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.scissors)
            }
            when (match.playerMove) {
                "rock" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.rock)
                "paper" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.paper)
                "scissors" -> itemView.computerRPS.foreground = context.getDrawable(R.drawable.scissors)
            }
            when (match.result) {
                "computer" -> itemView.winner.text = context.getText(R.string.computer_wins)
                "player" -> itemView.winner.text = context.getText(R.string.player_wins)
                "draw" -> itemView.winner.text = context.getText(R.string.draw)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(matches[position])
    }
}