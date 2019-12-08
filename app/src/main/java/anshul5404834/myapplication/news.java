package anshul5404834.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


public class news {
    public news(String content, String description, String date, String image,String url){
        this.content=content;
        this.date=date;
        this.description=description;
        this.Image= image ;
        this.url=url;

    }
    public news(){}


    private String content;

    private String date;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String url;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        int a = date.indexOf('T');
        String date1 = date.substring(0,a);
        return date1;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String Image;
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }
    private String description;





}
