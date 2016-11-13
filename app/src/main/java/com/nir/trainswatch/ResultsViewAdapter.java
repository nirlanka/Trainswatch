package com.nir.trainswatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nirmal on 11/13/2016.
 */
public class ResultsViewAdapter extends RecyclerView.Adapter<ResultsViewAdapter.ViewHolder> {
    private ArrayList<Train> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View textView;
        public TextView trainStartTime, trainArrivalTime, trainEndTime;
        public ViewHolder(View v) {
            super(v);
            textView = v;
            trainStartTime = (TextView) v.findViewById(R.id.train_start_time);
            trainArrivalTime = (TextView) v.findViewById(R.id.train_arrival_time);
            trainEndTime = (TextView) v.findViewById(R.id.train_end_time);
        }
    }

    public ResultsViewAdapter(ArrayList<Train> _dataSet) {
        dataSet = _dataSet;
    }

    @Override
    public ResultsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_train, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textView.setText(dataSet[position]);
        // TODO: set texts in the result card
        Train train = dataSet.get(position);
        holder.trainStartTime.setText(train.startTime);
        holder.trainArrivalTime.setText(train.arrivalTime);
        holder.trainEndTime.setText(train.endTime);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
