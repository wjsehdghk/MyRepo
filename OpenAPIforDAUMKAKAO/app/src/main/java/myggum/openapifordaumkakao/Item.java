package myggum.openapifordaumkakao;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Administrator on 2016-11-28.
 */
@Root(name="item",strict = false)
public class Item{
    @Element(name = "height")
    private Integer height;
    @Element(name="width")
    private Integer width;
    @Element(name = "cp")
    private String cp;
    @Element (name= "thumbnail")
    private String thumbnail;
    @Element(name = "image")
    private String image;
    @Element(name="pubDate")
    private String pubDate;
    @Element(name="title")
    private String title;
    @Element(name="link")
    private String link;

    public String getPubDate() {
        return pubDate;
    }
    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }
}
