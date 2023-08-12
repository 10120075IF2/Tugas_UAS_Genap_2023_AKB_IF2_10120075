package com.Dhavin_10120075_IF2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Dhavin_10120075_IF2.R;
import com.Dhavin_10120075_IF2.model.Note;
import com.Dhavin_10120075_IF2.view.activity.AddNoteActivity;

import java.util.List;

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.category.setText(notes.get(position).getCategory());
        holder.desc.setText(notes.get(position).getDesc());
        holder.date.setText(notes.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView category;
        TextView desc;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_note);
            category = itemView.findViewById(R.id.category_note);
            desc = itemView.findViewById(R.id.desc_note);
            date = itemView.findViewById(R.id.date_note);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Note clickedNote = notes.get(position);
                    Intent intent = new Intent(itemView.getContext(), AddNoteActivity.class);
                    intent.putExtra("Note", clickedNote);
                    itemView.getContext().startActivity(intent);
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