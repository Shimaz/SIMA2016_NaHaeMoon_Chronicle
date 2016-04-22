package kr.tangomike.sima2016_nahaemoon_chronocle;

/**
 * Created by shimaz on 2016-04-22.
 */
public class DetailPageData {

    private int timelineNumber;
    private int detailNumber;
    private int btnKind;
    private int btnPosition;
    private int imageCount;
    private int newsCount;


    public DetailPageData (int timelineNumber, int detailNumber, int btnKind, int btnPosition, int imageCount, int newsCount){

        this.timelineNumber = timelineNumber;
        this.detailNumber = detailNumber;
        this.btnKind = btnKind;
        this.btnPosition = btnPosition;
        this.imageCount = imageCount;
        this.newsCount = newsCount;


    }


    public int getTimelineNumber(){
        return this.timelineNumber;
    }

    public int getDetailNumber(){
        return this.detailNumber;
    }

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

}
