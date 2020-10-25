package com.example.madlevel4task2.ui

import Moves
import Outcomes
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rock.setOnClickListener { match(Moves.Rock) }
        paper.setOnClickListener { match(Moves.Paper) }
        scissors.setOnClickListener { match(Moves.Scissors) }
    }

    private fun match(playerMove: Moves) {
        var computerMove = randomMove()
        var outcome = getWinner(playerMove, computerMove)

        setRPS(computerMove, computerRPS, requireContext())
        setRPS(playerMove, playerRPS, requireContext())
        setWinner(outcome, winner, requireContext())
    }

    private fun getWinner(playerMove: Moves, computerMove: Moves): Outcomes {
        if (playerMove == computerMove) { return Outcomes.Draw }
        if (playerMove == Moves.Rock && computerMove == Moves.Scissors) { return Outcomes.Player }
        if (playerMove == Moves.Paper && computerMove == Moves.Rock) { return Outcomes.Player }
        if (playerMove == Moves.Scissors && computerMove == Moves.Paper) { return Outcomes.Player }
        return Outcomes.Computer
    }

    private fun setWinner(outcome: Outcomes, resource: TextView, context: Context) {
        when(outcome) {
            Outcomes.Draw -> resource.text = context.getString(R.string.draw)
            Outcomes.Player -> resource.text = context.getString(R.string.player_wins)
            Outcomes.Computer -> resource.text = context.getString(R.string.computer_wins)
        }
    }

    private fun setRPS(rps: Moves, resource: ImageView, context: Context) {
        when(rps) {
            Moves.Rock -> resource.foreground = context.getDrawable(R.drawable.rock)
            Moves.Paper -> resource.foreground = context.getDrawable(R.drawable.paper)
            Moves.Scissors -> resource.foreground = context.getDrawable(R.drawable.scissors)
        }
    }

    private fun randomMove(): Moves {
        var move = Moves.Rock
        when((1..3).random()) {
            1 -> move = Moves.Rock
            2 -> move = Moves.Paper
            3 -> move = Moves.Scissors
        }
        return move
    }
}