package com.example.sumit_thakur.ibilive.Model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * setting up the data
 */
public class SetDataModel implements Parcelable {

    public static final Creator<SetDataModel> CREATOR = new Creator<SetDataModel>() {
        @Override
        public SetDataModel createFromParcel(final Parcel in) {
            return new SetDataModel(in);
        }

        @Override
        public SetDataModel[] newArray(final int size) {
            return new SetDataModel[size];
        }
    };
    private String mCityName, mLocation, mName, mTime, mReviews, mInfo;

    /**
     * constructor
     *
     * @param mCityName city name
     * @param mLocation loction
     * @param mName     name
     * @param mTime     time
     * @param mReviews  reviews
     * @param mInfo     information
     */
    public SetDataModel(final String mCityName, final String mLocation,
                        final String mName, final String mTime, final String mReviews, final String mInfo) {
        this.mCityName = mCityName;
        this.mLocation = mLocation;
        this.mName = mName;
        this.mTime = mTime;
        this.mReviews = mReviews;
        this.mInfo = mInfo;
    }

    /**
     * @param in in
     */
    protected SetDataModel(final Parcel in) {
        mCityName = in.readString();
        mLocation = in.readString();
        mName = in.readString();
        mTime = in.readString();
        mReviews = in.readString();
        mInfo = in.readString();
    }

    /**
     * set city
     *
     * @param mCityName city name
     */
    public void setmCityName(final String mCityName) {
        this.mCityName = mCityName;
    }

    /**
     * set loction
     *
     * @param mLocation loaction
     */
    public void setmLocation(final String mLocation) {
        this.mLocation = mLocation;
    }

    /**
     * set name
     *
     * @param mName name
     */
    public void setmName(final String mName) {
        this.mName = mName;
    }

    /**
     * set time
     *
     * @param mTime time
     */
    public void setmTime(final String mTime) {
        this.mTime = mTime;
    }

    /**
     * set reviewa
     *
     * @param mReviews reviewa
     */
    public void setmReviews(final String mReviews) {
        this.mReviews = mReviews;
    }

    /**
     * set info
     *
     * @param mInfo information
     */
    public void setmInfo(final String mInfo) {
        this.mInfo = mInfo;
    }

    /**
     * @return city name
     */
    public String getmCityName() {
        return mCityName;
    }

    /**
     * @return location
     */
    public String getmLocation() {
        return mLocation;
    }

    /**
     * @return name
     */
    public String getmName() {
        return mName;
    }

    /**
     * @return time
     */
    public String getmTime() {
        return mTime;
    }

    /**
     * @return reviews
     */
    public String getmReviews() {
        return mReviews;
    }

    /**
     * @return information
     */
    public String getmInfo() {
        return mInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mCityName);
        dest.writeString(mLocation);
        dest.writeString(mName);
        dest.writeString(mTime);
        dest.writeString(mReviews);
        dest.writeString(mInfo);
    }
}
