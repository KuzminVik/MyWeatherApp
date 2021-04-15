package ru.geekbrains.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ru.geekbrains.myapp.model.WeekRequest;
import ru.geekbrains.myapp.model.entiti.Daily;

public class WeekWeatherAdapter extends RecyclerView.Adapter<WeekWeatherAdapter.ViewHolder> {
    private static final String TAG = SearchCityAdapter.class.getSimpleName();
    private final WeekRequest weekRequest;
    private final String[] dataMonths;

    public WeekWeatherAdapter(WeekRequest weekRequest, String[] dataMonths) {
        this.weekRequest = weekRequest;
        this.dataMonths = dataMonths;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week, parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull WeekWeatherAdapter.ViewHolder holder, int position) {
        Daily daily = weekRequest.getDaily()[position];
        TextView tw_d = holder.tw_date;
        String[] s = timeUnixToNormal(daily.getDt()).split(" ");
        tw_d.setText(s[0] + " "+dataMonths[Integer.parseInt(s[1])-1]);
        TextView tw_td = holder.tw_tempDay;
        tw_td.setText(String.valueOf(daily.getTemp().getDay()));
        TextView tw_tn = holder.tw_tempNight;
        tw_tn.setText(String.valueOf(daily.getTemp().getNight()));

    }

    @Override
    public int getItemCount() {
        return weekRequest.getDaily().length;
    }

    public String timeUnixToNormal(long unixTime){
        Date date = new Date(unixTime*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("d M");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tw_date;
        private final TextView tw_tempDay;
        private final TextView tw_tempNight;
        private final ImageView iw_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tw_date = (TextView) itemView.findViewById(R.id.tw_date);
            tw_tempDay = (TextView) itemView.findViewById(R.id.tw_tempDay);
            tw_tempNight = (TextView) itemView.findViewById(R.id.tw_tempNight);
            iw_icon = (ImageView) itemView.findViewById(R.id.iw_icon);
        }


    }
}
