package ru.geekbrains.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import ru.geekbrains.myapp.model.entiti.Daily;

public class WeekRequest implements Parcelable {
    private double lon;
    private double lat;
    private String timezone;
    private String timezone_offset;
    private Daily[] daily;


    protected WeekRequest(Parcel in) {
        lon = in.readDouble();
        lat = in.readDouble();
        timezone = in.readString();
        timezone_offset = in.readString();
        daily = in.createTypedArray(Daily.CREATOR);
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public Daily[] getDaily() {
        return daily;
    }

    public static final Creator<WeekRequest> CREATOR = new Creator<WeekRequest>() {
        @Override
        public WeekRequest createFromParcel(Parcel in) {
            return new WeekRequest(in);
        }

        @Override
        public WeekRequest[] newArray(int size) {
            return new WeekRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lon);
        dest.writeDouble(lat);
        dest.writeString(timezone);
        dest.writeString(timezone_offset);
        dest.writeTypedArray(daily, flags);
    }
}
