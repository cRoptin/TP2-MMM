package fr.istic.mmmtp1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class ParcelableObj implements Parcelable {

    private String lblName = "";
    private String lblDate = "";
    private String lblCity = "";

    public static final Parcelable.Creator<ParcelableObj> CREATOR = new Parcelable.Creator<ParcelableObj>() {
        @Override
        public ParcelableObj createFromParcel(Parcel source) {
            return new ParcelableObj(source);
        }

        @Override
        public ParcelableObj[] newArray(int size) {
            return new ParcelableObj[size];
        }
    };

    public ParcelableObj (String txtName, String txtDate, String txtCity) {
        this.lblName = txtName;
        this.lblDate = txtDate;
        this.lblCity = txtCity;
    }

    public ParcelableObj (Parcel in) {
        this.lblName = in.readString();
        this.lblDate = in.readString();
        this.lblCity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lblName);
        dest.writeString(lblDate);
        dest.writeString(lblCity);
    }

    public String getLblName() {
        return this.lblName;
    }

    public String getLblDate() {
        return this.lblDate;
    }

    public String getLblCity() {
        return this.lblCity;
    }

}
