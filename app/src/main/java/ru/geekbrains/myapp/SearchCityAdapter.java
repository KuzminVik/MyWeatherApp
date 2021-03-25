package ru.geekbrains.myapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.ViewHolder>{
    private static final String TAG = SearchCityAdapter.class.getSimpleName();
    private final String[] dataSours;
    private OnItemClickListener itemClickListener;
    public SearchCityAdapter(String[] dataSours) {
        this.dataSours = dataSours;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(dataSours[position]);
    }

    @Override
    public int getItemCount() {
        return dataSours.length;
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public void setData(String text){
            getTextView().setText(text);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;


            // Обработчик нажатий на этом ViewHolder
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

        }
    }
}
