package ru.geekbrains.myapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelWeather implements Parcelable {

    private String city;
    private int temp;
    private int pressure;
    private int humidity;

    public ParcelWeather(String city, int temp, int pressure, int humidity) {
        this.city = city;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public int getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    protected ParcelWeather(Parcel in) {
        city = in.readString();
        temp = in.readInt();
        pressure = in.readInt();
        humidity = in.readInt();
    }

    public static final Creator<ParcelWeather> CREATOR = new Creator<ParcelWeather>() {
        @Override
        public ParcelWeather createFromParcel(Parcel in) {
            return new ParcelWeather(in);
        }

        @Override
        public ParcelWeather[] newArray(int size) {
            return new ParcelWeather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeInt(temp);
        dest.writeInt(pressure);
        dest.writeInt(humidity);
    }
}
