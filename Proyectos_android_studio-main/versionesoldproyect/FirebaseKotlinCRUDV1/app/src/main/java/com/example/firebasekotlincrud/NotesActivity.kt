package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasekotlincrud.databinding.ActivityNotesBinding
import com.example.firebasekotlincrud.repository.NotesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
class NotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotesBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun buildList() {

        val repository = NotesRepository.getRepository(this)

        val layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repository.allNotes.collect { notes ->
                binding.rvVehicles.apply {
                    adapter = NotesAdapter(notes)
                    setLayoutManager(layoutManager)
                }
            }
        }
    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
        }

    }
}