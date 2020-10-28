package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Match
import com.example.madlevel4task2.repository.MatchRepository
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.DividerItemDecoration

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private lateinit var matchRepository: MatchRepository
    private val matchHistory = arrayListOf<Match>()
    private val historyAdapter = HistoryAdapter(matchHistory)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchRepository = MatchRepository(requireContext())
        initRv()
    }

    private fun initRv() {
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        getMatches()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.action_delete -> { deleteAllMatches(); true }
            R.id.action_back -> {findNavController().navigate(R.id.mainFragment); true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getMatches() {
        CoroutineScope(Dispatchers.Main).launch {
            var matches = withContext(Dispatchers.IO){
                matchRepository.getAllMatches()
            }
            this@HistoryFragment.matchHistory.clear()
            this@HistoryFragment.matchHistory.addAll(matches)
            this@HistoryFragment.historyAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteAllMatches(){
        CoroutineScope(Dispatchers.Main).launch{
            withContext(Dispatchers.IO){
                matchRepository.deleteMatches()
            }
            getMatches()
        }
    }
}