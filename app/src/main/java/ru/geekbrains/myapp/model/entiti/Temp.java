package ru.geekbrains.myapp.model.entiti;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Temp implements Parcelable {
    private double day;
    private double night;

    protected Temp(Parcel in) {
        day = in.readDouble();
        night = in.readDouble();
    }

    public int getDay() {
        return roundingNumber(day);
    }

    public int getNight() {
        return roundingNumber(night);
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(day);
        dest.writeDouble(night);
    }

    static int roundingNumber(double d){
        double newDouble = new BigDecimal(d).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
        return (int)newDouble;
    }
}
