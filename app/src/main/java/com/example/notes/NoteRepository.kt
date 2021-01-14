package com.example.notes

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}