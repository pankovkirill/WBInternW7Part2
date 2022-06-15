package com.example.wbinternw7part2.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbinternw7part2.R
import com.example.wbinternw7part2.databinding.ActivityMainBinding
import com.example.wbinternw7part2.model.data.AppState
import com.example.wbinternw7part2.model.data.DataModel
import com.example.wbinternw7part2.view.details.DetailsFragment
import com.example.wbinternw7part2.view.main.adapter.MainAdapter
import com.example.wbinternw7part2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnHeroListItemClickListener =
        object : MainAdapter.OnHeroListItemClickListener {
            override fun onItemClick(dataModel: DataModel) {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, DetailsFragment.newInstance(dataModel))
                    .commit()
            }
        }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
    }

    private fun initView() {
        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.liveDataForViewToObserve.observe(this) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(this, appState.error.message, Toast.LENGTH_SHORT).show()
                Log.d("TAG", appState.error.message.toString())
            }
            is AppState.Loading -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                appState.dataModel?.let {
                    if (it.isEmpty())
                        Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
                    else
                        adapter.setData(it)
                }
            }
        }
    }
}