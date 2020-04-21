package com.example.financialelephant;

import android.os.Parcel;
import android.os.Parcelable;

public class Attribute implements Parcelable {
    String name;
    int value;
    boolean checked = true;

    protected Attribute(Parcel in) {
        name = in.readString();
        value = in.readInt();
        checked = in.readByte() != 0;
    }

    public static final Creator<Attribute> CREATOR = new Creator<Attribute>() {
        @Override
        public Attribute createFromParcel(Parcel in) {
            return new Attribute(in);
        }

        @Override
        public Attribute[] newArray(int size) {
            return new Attribute[size];
        }
    };

    public int getValue() {
            return value;
        }

        boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Attribute(String name) {
            this.name = name;
        }

        Attribute(String name, int value) {
            this.name = name;
            this.value = value;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(value);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}