package ru.geekbrains.myapp.model.entiti;

import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable {
    private int temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;

    protected Main(Parcel in) {
        temp = in.readInt();
        feels_like = in.readDouble();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
        pressure = in.readInt();
        humidity = in.readInt();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public int getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(temp);
        dest.writeDouble(feels_like);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
        dest.writeInt(pressure);
        dest.writeInt(humidity);
    }
}
