package com.Dhavin_10120075_IF2.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Dhavin_10120075_IF2.database.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.Dhavin_10120075_IF2.view.activity.AddNoteActivity;
import com.Dhavin_10120075_IF2.view.activity.MainActivity;
import com.Dhavin_10120075_IF2.R;
import com.Dhavin_10120075_IF2.adapter.NoteAdapter;
import com.Dhavin_10120075_IF2.model.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */
public class NoteFragment extends Fragment  {

    private MainActivity mainActivity;
    private List<Note> note;
    private RecyclerView recyclerView;
    private com.Dhavin_10120075_IF2.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;
    private DatabaseReference databaseReference;
    private FirebaseHelper firebaseHelper;

    public NoteFragment() {
        // Initialize Firebase components
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseHelper = new FirebaseHelper();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_note, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().hide();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mynote);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNoteActivity.class));
        });

        RefreshListNote();
    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshListNote();
    }

    public void RefreshListNote() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference noteRef = databaseReference.child("note").child(userId);

            noteRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Note> notes = new ArrayList<>();
                    for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                        Note note = noteSnapshot.getValue(Note.class);
                        notes.add(note);
                    }

                    NoteAdapter adapter = new NoteAdapter(notes);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setHasFixedSize(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle the error, if any
                }
            });
        }
    }
}

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */