package org.dailyplastic.idnp.prueba.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Consumption implements Parcelable {
    private Integer id;
    private String user;
    private Plastic plastic;

    private Origin origin;
    private String image;
    private String description;
    private Integer units;
    private String updated;


    public Consumption() {
    }

    public Consumption(Integer id, String user, Plastic plastic, Origin origin, String image, String description, Integer units, String updated) {
        this.id = id;
        this.user = user;
        this.plastic = plastic;
        this.origin = origin;
        this.image = image;
        this.description = description;
        this.units = units;
        this.updated = updated;
    }

    protected Consumption(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        user = in.readString();
        image = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            units = null;
        } else {
            units = in.readInt();
        }
        updated = in.readString();
    }

    public static final Creator<Consumption> CREATOR = new Creator<Consumption>() {
        @Override
        public Consumption createFromParcel(Parcel in) {
            return new Consumption(in);
        }

        @Override
        public Consumption[] newArray(int size) {
            return new Consumption[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Plastic getPlastic() {
        return plastic;
    }

    public void setPlastic(Plastic plastic) {
        this.plastic = plastic;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", plastic=" + plastic +
                ", origin=" + origin +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", units=" + units +
                ", updated='" + updated + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(user);
        parcel.writeString(image);
        parcel.writeString(description);
        if (units == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(units);
        }
        parcel.writeString(updated);
    }
}
