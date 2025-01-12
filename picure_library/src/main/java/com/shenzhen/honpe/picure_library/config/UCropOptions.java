package com.shenzhen.honpe.picure_library.config;

import android.os.Parcel;
import android.os.Parcelable;

import com.shenzhen.honpe.ucrop.UCrop;


/**
 * @author：luck
 * @date：2020-01-09 13:33
 * @describe： UCrop Configuration items
 */
public class UCropOptions extends UCrop.Options implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public UCropOptions() {
    }

    protected UCropOptions(Parcel in) {
    }

    public static final Creator<UCropOptions> CREATOR = new Creator<UCropOptions>() {
        @Override
        public UCropOptions createFromParcel(Parcel source) {
            return new UCropOptions(source);
        }

        @Override
        public UCropOptions[] newArray(int size) {
            return new UCropOptions[size];
        }
    };
}
