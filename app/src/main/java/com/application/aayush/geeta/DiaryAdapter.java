package com.application.aayush.geeta;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aayush on 11/12/2017.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryHolder> {
    private List<DiaryContents> diaryContentsList;
    private LayoutInflater layoutInflater;
    CardView cardView;
    RelativeLayout layout;

    public DiaryAdapter(List<DiaryContents> diaryContentsList) {
        this.diaryContentsList = diaryContentsList;
    }

    @Override
    public DiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_repeat_layout,parent,false);
        cardView = (CardView)itemView.findViewById(R.id.cardView2);
        layout = (RelativeLayout)itemView.findViewById(R.id.diary);

        return null;
    }

    @Override
    public void onBindViewHolder(DiaryHolder holder, int position) {
        DiaryContents diaryContents = diaryContentsList.get(position);
        holder.textView1.setText(diaryContents.chapter_content);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class DiaryHolder extends RecyclerView.ViewHolder{
        Spinner spinner;
        TextView textView1,textView2;
        ImageView save,delete,share;

        public DiaryHolder(View itemView) {
            super(itemView);
            spinner = (Spinner)itemView.findViewById(R.id.spinner3);
            textView1 = (TextView)itemView.findViewById(R.id.textView34);
            textView2 = (TextView)itemView.findViewById(R.id.textView35);
            save = (ImageView)itemView.findViewById(R.id.imageView10);
            delete = (ImageView)itemView.findViewById(R.id.imageView11);
            share = (ImageView)itemView.findViewById(R.id.imageView12);
        }
    }
}
