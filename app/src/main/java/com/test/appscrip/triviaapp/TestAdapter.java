package com.test.appscrip.triviaapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ItemHolder> {
    private List<Test> item = new ArrayList<>();
    private static final String TAG = "TestAdapterItems";
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);
        return new ItemHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Test currentItem = item.get(position);
        holder.datetime.setText(currentItem.getDatetime());
        holder.name.setText(currentItem.getName());
        holder.question1.setText(currentItem.getQuestion1());
        Log.d(TAG, " Data is " + currentItem.getName() + " " + currentItem.getQuestion1());
    }
    @Override
    public int getItemCount() {
        return item.size();
    }
    public void setNotes(List<Test> item) {
        this.item = item;
        notifyDataSetChanged();
    }
    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView datetime;
        private TextView name;
        private TextView question1;
        public ItemHolder(View itemView) {
            super(itemView);
            datetime = itemView.findViewById(R.id.datetime1);
            name = itemView.findViewById(R.id.name1);
            question1 = itemView.findViewById(R.id.question1);
        }
    }
}