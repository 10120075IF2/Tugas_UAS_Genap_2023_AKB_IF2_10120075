package com.Dhavin_10120075_IF2.database;

import com.Dhavin_10120075_IF2.model.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */
public class FirebaseHelper {
    private DatabaseReference mDatabase;

    public FirebaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addNote(String id, String title, String category, String desc, String date) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        Note catatanModel = new Note(id, title, category, desc, date);
        mDatabase.child("note").child(userId).child(id).setValue(catatanModel);
    }

    public void updateNote(String noteId, String newTitle, String newCategory, String newDesc) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference updatedNoteRef = mDatabase.child("note").child(userId).child(noteId);
        updatedNoteRef.child("title").setValue(newTitle);
        updatedNoteRef.child("category").setValue(newCategory);
        updatedNoteRef.child("desc").setValue(newDesc);
    }

    public void deleteNote(String noteId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference noteRef = mDatabase.child("note").child(userId).child(noteId);
        noteRef.removeValue();

    }
}
