package com.example.financialelephant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class Company implements Parcelable {
    private boolean checked = true;
    private String name;
    private int id;
    private String imgUrl;
    ArrayList<Attribute> attributes;

    public ArrayList<Attribute> getAttributes() {
        for (Attribute attribute : attributes) {
            if(attribute.isChecked())
                return attributes;
        }
        return null;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    protected Company(Parcel in) {
        checked = in.readByte() != 0;
        name = in.readString();
        id = in.readInt();
        imgUrl = in.readString();
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

    int getAttributeValue(Attribute attribute) {
        for (Attribute attributes : attributes) {
            if (attributes.name.equals(attribute.name)) {
                return attributes.value;
            }
        }
        return 0;
    }


    public Company(String name, int id, String imgUrl, ArrayList<Attribute> attributes) {
        this.name = name;
        this.id = id;
        this.imgUrl = imgUrl;
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


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        dest.writeString(imgUrl);
        dest.writeTypedList(attributes);
    }
}




