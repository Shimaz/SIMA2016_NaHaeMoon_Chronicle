package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shimaz on 2016-04-22.
 */
public class DetailPageData implements Parcelable {

//    private int timelineNumber;
//    private int detailNumber;
    private int btnKind;
    private int btnPosition;
    private int imageCount;
    private int newsCount;


    public DetailPageData (int btnKind, int btnPosition, int imageCount, int newsCount){

//        this.timelineNumber = timelineNumber;
//        this.detailNumber = detailNumber;
        this.btnKind = btnKind;
        this.btnPosition = btnPosition;
        this.imageCount = imageCount;
        this.newsCount = newsCount;


    }


//    public int getTimelineNumber(){
//        return this.timelineNumber;
//    }
//
//    public int getDetailNumber(){
//        return this.detailNumber;
//    }

    protected DetailPageData(Parcel in) {
        btnKind = in.readInt();
        btnPosition = in.readInt();
        imageCount = in.readInt();
        newsCount = in.readInt();
    }

    public static final Creator<DetailPageData> CREATOR = new Creator<DetailPageData>() {
        @Override
        public DetailPageData createFromParcel(Parcel in) {
            return new DetailPageData(in);
        }

        @Override
        public DetailPageData[] newArray(int size) {
            return new DetailPageData[size];
        }
    };

    public int getBtnKind(){
        return this.btnKind;
    }

    public int getBtnPosition(){
        return this.btnPosition;
    }

    public int getImageCount(){
        return this.imageCount;
    }

    public int getNewsCount(){
        return this.newsCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(btnKind);
        dest.writeInt(btnPosition);
        dest.writeInt(imageCount);
        dest.writeInt(newsCount);
    }
}
