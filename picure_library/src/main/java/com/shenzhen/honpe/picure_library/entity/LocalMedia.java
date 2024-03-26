package com.shenzhen.honpe.picure_library.entity;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.shenzhen.honpe.picure_library.config.PictureConfig;


/**
 * @author：luck
 * @date：2017-5-24 16:21
 * @describe：Media Entity
 */

public class LocalMedia implements Parcelable {
    /**
     * file to ID
     */
    private long id;
    /**
     * original path
     */
    private String path;

    /**
     * The real path，But you can't get access from AndroidQ
     * <p>
     * It could be empty
     * <p/>
     */
    private String realPath;

    /**
     * # Check the original button to get the return value
     * original path
     */
    private String originalPath;
    /**
     * compress path
     */
    private String compressPath;
    /**
     * cut path
     */
    private String cutPath;

    /**
     * Note: this field is only returned in Android Q version
     * <p>
     * Android Q image or video path
     */
    private String androidQToPath;
    /**
     * video duration
     */
    private long duration;
    /**
     * If the selected
     * # Internal use
     */
    private boolean isChecked;
    /**
     * If the cut
     */
    private boolean isCut;
    /**
     * media position of list
     */
    public int position;
    /**
     * The media number of qq choose styles
     */
    private int num;
    /**
     * The media resource type
     */
    private String mimeType;

    /**
     * Gallery selection mode
     */
    private int chooseModel;

    /**
     * If the compressed
     */
    private boolean compressed;
    /**
     * image or video width
     * <p>
     * # If zero occurs, the developer needs to handle it extra
     */
    private int width;
    /**
     * image or video height
     * <p>
     * # If zero occurs, the developer needs to handle it extra
     */
    private int height;

    /**
     * file size
     */
    private long size;

    /**
     * Whether the original image is displayed
     */
    private boolean isOriginal;

    /**
     * file name
     */
    private String fileName;

    /**
     * Parent  Folder Name
     */
    private String parentFolderName;

    /**
     * orientation info
     * # For internal use only
     */
    private int orientation = -1;

    /**
     * loadLongImageStatus
     * # For internal use only
     */
    public int loadLongImageStatus = PictureConfig.NORMAL;

    /**
     * isLongImage
     * # For internal use only
     */
    public boolean isLongImage;

    /**
     * bucketId
     */
    private long bucketId = -1;

    /**
     * isMaxSelectEnabledMask
     * # For internal use only
     */
    private boolean isMaxSelectEnabledMask;

    private Bitmap iconBit;


    public LocalMedia() {

    }

    public LocalMedia(String path, long duration, int chooseModel, String mimeType) {
        this.path = path;
        this.duration = duration;
        this.chooseModel = chooseModel;
        this.mimeType = mimeType;
    }

    public LocalMedia(long id, String path, String fileName, String parentFolderName, long duration, int chooseModel,
                      String mimeType, int width, int height, long size) {
        this.id = id;
        this.path = path;
        this.fileName = fileName;
        this.parentFolderName = parentFolderName;
        this.duration = duration;
        this.chooseModel = chooseModel;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public LocalMedia(long id, String path, String absolutePath, String fileName, String parentFolderName, long duration, int chooseModel,
                      String mimeType, int width, int height, long size, long bucketId) {
        this.id = id;
        this.path = path;
        this.realPath = absolutePath;
        this.fileName = fileName;
        this.parentFolderName = parentFolderName;
        this.duration = duration;
        this.chooseModel = chooseModel;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.size = size;
        this.bucketId = bucketId;
    }

    public LocalMedia(String path, long duration,
                      boolean isChecked, int position, int num, int chooseModel) {
        this.path = path;
        this.duration = duration;
        this.isChecked = isChecked;
        this.position = position;
        this.num = num;
        this.chooseModel = chooseModel;
    }




    public Bitmap getIconBit() {
        return iconBit;
    }

    public void setIconBit(Bitmap iconBit) {
        this.iconBit = iconBit;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public String getCutPath() {
        return cutPath;
    }

    public void setCutPath(String cutPath) {
        this.cutPath = cutPath;
    }

    public String getAndroidQToPath() {
        return androidQToPath;
    }

    public void setAndroidQToPath(String androidQToPath) {
        this.androidQToPath = androidQToPath;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCut() {
        return isCut;
    }

    public void setCut(boolean cut) {
        isCut = cut;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMimeType() {
        return TextUtils.isEmpty(mimeType) ? "image/jpeg" : mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getChooseModel() {
        return chooseModel;
    }

    public void setChooseModel(int chooseModel) {
        this.chooseModel = chooseModel;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }

    public void setParentFolderName(String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public long getBucketId() {
        return bucketId;
    }

    public void setBucketId(long bucketId) {
        this.bucketId = bucketId;
    }

    public boolean isMaxSelectEnabledMask() {
        return isMaxSelectEnabledMask;
    }

    public void setMaxSelectEnabledMask(boolean maxSelectEnabledMask) {
        isMaxSelectEnabledMask = maxSelectEnabledMask;
    }


    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(path);
        dest.writeString(realPath);
        dest.writeString(originalPath);
        dest.writeString(compressPath);
        dest.writeString(cutPath);
        dest.writeString(androidQToPath);
        dest.writeLong(duration);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeByte((byte) (isCut ? 1 : 0));
        dest.writeInt(position);
        dest.writeInt(num);
        dest.writeString(mimeType);
        dest.writeInt(chooseModel);
        dest.writeByte((byte) (compressed ? 1 : 0));
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeLong(size);
        dest.writeByte((byte) (isOriginal ? 1 : 0));
        dest.writeString(fileName);
        dest.writeString(parentFolderName);
        dest.writeInt(orientation);
        dest.writeInt(loadLongImageStatus);
        dest.writeByte((byte) (isLongImage ? 1 : 0));
        dest.writeLong(bucketId);
        dest.writeByte((byte) (isMaxSelectEnabledMask ? 1 : 0));
        dest.writeParcelable(iconBit, flags);
    }

    protected LocalMedia(Parcel in) {
        id = in.readLong();
        path = in.readString();
        realPath = in.readString();
        originalPath = in.readString();
        compressPath = in.readString();
        cutPath = in.readString();
        androidQToPath = in.readString();
        duration = in.readLong();
        isChecked = in.readByte() != 0;
        isCut = in.readByte() != 0;
        position = in.readInt();
        num = in.readInt();
        mimeType = in.readString();
        chooseModel = in.readInt();
        compressed = in.readByte() != 0;
        width = in.readInt();
        height = in.readInt();
        size = in.readLong();
        isOriginal = in.readByte() != 0;
        fileName = in.readString();
        parentFolderName = in.readString();
        orientation = in.readInt();
        loadLongImageStatus = in.readInt();
        isLongImage = in.readByte() != 0;
        bucketId = in.readLong();
        isMaxSelectEnabledMask = in.readByte() != 0;
        iconBit = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<LocalMedia> CREATOR = new Creator<LocalMedia>() {
        @Override
        public LocalMedia createFromParcel(Parcel in) {
            return new LocalMedia(in);
        }

        @Override
        public LocalMedia[] newArray(int size) {
            return new LocalMedia[size];
        }
    };
}
