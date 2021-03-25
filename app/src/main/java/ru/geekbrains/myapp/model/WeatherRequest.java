package ru.geekbrains.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.geekbrains.myapp.model.entiti.Clouds;
import ru.geekbrains.myapp.model.entiti.Main;
import ru.geekbrains.myapp.model.entiti.Weather;
import ru.geekbrains.myapp.model.entiti.Wind;

public class WeatherRequest implements Parcelable {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private String name;


    protected WeatherRequest(Parcel in) {
        weather = in.createTypedArrayList(Weather.CREATOR);
        main = in.readParcelable(Main.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(weather);
        dest.writeParcelable(main, flags);
        dest.writeParcelable(wind, flags);
        dest.writeParcelable(clouds, flags);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherRequest> CREATOR = new Creator<WeatherRequest>() {
        @Override
        public WeatherRequest createFromParcel(Parcel in) {
            return new WeatherRequest(in);
        }

        @Override
        public WeatherRequest[] newArray(int size) {
            return new WeatherRequest[size];
        }
    };

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<WeatherRequest> getCREATOR() {
        return CREATOR;
    }
}
