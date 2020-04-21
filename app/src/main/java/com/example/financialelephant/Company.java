package com.example.financialelephant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class Company implements Parcelable {
    private boolean checked = true;
    private String name;
    private int id;
    ArrayList<Attribute> attributes;

    protected Company(Parcel in) {
        checked = in.readByte() != 0;
        name = in.readString();
        id = in.readInt();
        attributes = in.createTypedArrayList(Attribute.CREATOR);
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    boolean isChecked() {
        return checked;
    }

    int getAttribute(Attribute attribute) {
        for (Attribute attributes : attributes) {
            if (attributes.name.equals(attribute.name)) {
                return attributes.value;
            }
        }
        return 0;
    }

    Company(String name, int id, ArrayList<Attribute> attributes) {
        this.name = name;
        this.id = id;
        this.attributes = attributes;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeTypedList(attributes);
    }
}




