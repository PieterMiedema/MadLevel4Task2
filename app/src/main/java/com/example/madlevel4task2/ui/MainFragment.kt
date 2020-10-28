package com.example.madlevel4task2.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Match
import com.example.madlevel4task2.repository.MatchRepository
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import java.util.*
import java.util.zip.Inflater

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var matchRepository: MatchRepository

    // rock:      1
    // paper:     2
    // scissors:  3
    // draw:      4
    // player:    5
    // computer:  6

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchRepository = MatchRepository(requireContext())

        rock.setOnClickListener { match(1) }
        paper.setOnClickListener { match(2) }
        scissors.setOnClickListener { match(3) }

        CoroutineScope(Dispatchers.Main).launch {
            updateStats()
        }
    }

    private suspend fun updateStats(){
        var wins = matchRepository.getWinCount()
        var losses = matchRepository.getLooseCount()
        var draws = matchRepository.getDrawCount()

        stats.text = context?.getString(R.string.all_time_stats, wins, losses, draws)
    }

    private fun updateDatabase(playerMove: Int, computerMove: Int, outcome: Int) {
        var draw = false
        var win = false
        var loose = false

        when(outcome) {
            4 -> draw = true
            5 -> win = true
            6 -> loose = true
        }

        var match = Match(playerMove, computerMove, Date(), outcome, draw, win, loose)

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                matchRepository.insertMatches(match)
                CoroutineScope((Dispatchers.Main)).launch {
                    updateStats()
                }
            }
        }
    }

    private fun match(playerMove: Int) {
        var computerMove = randomMove()
        var outcome = getWinner(playerMove, computerMove)

        setRPS(computerMove, computerRPS, requireContext())
        setRPS(playerMove, playerRPS, requireContext())
        setWinner(outcome, winner, requireContext())

        updateDatabase(playerMove, computerMove, outcome)
    }

    private fun getWinner(playerMove: Int, computerMove: Int): Int {
        if (playerMove == computerMove) { return 4 }
        if (playerMove == 1 && computerMove == 3) { return 5 }
        if (playerMove == 2 && computerMove == 1) { return 5 }
        if (playerMove == 3 && computerMove == 2) { return 5 }
        return 6
    }

    private fun setWinner(outcome: Int, resource: TextView, context: Context) {
        when(outcome) {
            4 -> resource.text = context.getString(R.string.draw)
            5 -> resource.text = context.getString(R.string.player_wins)
            6 -> resource.text = context.getString(R.string.computer_wins)
        }
    }

    private fun setRPS(rps: Int, resource: ImageView, context: Context) {
        when(rps) {
            1 -> resource.foreground = context.getDrawable(R.drawable.rock)
            2 -> resource.foreground = context.getDrawable(R.drawable.paper)
            3 -> resource.foreground = context.getDrawable(R.drawable.scissors)
        }
    }

    private fun randomMove(): Int {
        return (1..3).random()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item?.itemId) {
            R.id.action_history -> { findNavController().navigate(R.id.historyFragment); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}