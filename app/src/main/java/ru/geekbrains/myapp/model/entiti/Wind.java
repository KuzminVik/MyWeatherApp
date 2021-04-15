package ru.geekbrains.myapp.model.entiti;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wind implements Parcelable {
    private double speed;
    private double deg;

    protected Wind(Parcel in) {
        speed = in.readDouble();
        deg = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(speed);
        dest.writeDouble(deg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public int getSpeed() {
        return roundingNumber(speed);
    }

    public int getDeg() {
        return roundingNumber(deg);
    }

    static int roundingNumber(double d){
        double newDouble = new BigDecimal(d).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
        return (int)newDouble;
    }
}
