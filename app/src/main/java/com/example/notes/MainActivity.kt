package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        addButton.setOnClickListener {
            val text = input.text.toString()
            if (text.isEmpty())
                Toast.makeText(this, "No Note Entered!!", Toast.LENGTH_SHORT).show()
            else {
                viewModel.insertNote(Note(text))
                input.text.clear()
                Toast.makeText(this, "Note \"$text\" saved successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Note \"${note.text}\" deleted successfully", Toast.LENGTH_SHORT).show()
    }
}