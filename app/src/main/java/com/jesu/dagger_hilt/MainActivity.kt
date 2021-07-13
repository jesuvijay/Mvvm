package com.jesu.dagger_hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesu.dagger_hilt.adapters.NoteRecyclerAdapter
import com.jesu.dagger_hilt.models.Blog
import com.jesu.dagger_hilt.viewmodels.MainViewModels
import com.jesu.dagger_hilt.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModels
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainrecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(MainViewModels::class.java)
        but = findViewById(R.id.btn_submit)
        but.setOnClickListener {
            addData()
        }
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {

        viewModel.lst.observe(this, {
            Log.i("data",it.toString())
            mainrecycler.adapter= NoteRecyclerAdapter(viewModel, it)
        })
    }

    private fun addData() {
        var txtplce = findViewById<EditText>(R.id.et_name)
        var title=txtplce.text.toString()
        if(title.isNullOrBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            var blog= Blog(title)
            viewModel.add(blog)
            txtplce.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }
    }
}