package ru.geekbrains.myapp.model.entiti;

import android.os.Parcel;
import android.os.Parcelable;

public class Wind implements Parcelable {
    private int speed;
    private int deg;

    protected Wind(Parcel in) {
        speed = in.readInt();
        deg = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(speed);
        dest.writeInt(deg);
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
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}
