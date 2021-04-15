package ru.geekbrains.myapp.model.entiti;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Daily implements Parcelable {
    private final long dt;
    private final Temp temp;
//    private final List<Weather> weather;

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    protected Daily(Parcel in) {
        dt = in.readLong();
        temp = in.readParcelable(Temp.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
        dest.writeParcelable(temp, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };
}

