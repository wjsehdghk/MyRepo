package myggum.openapifordaumkakao;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Administrator on 2016-11-18.
 */

@Root(name="channel")
public class Channel {
    @Element(name="title")
    private String title;
    @Element(name="description")
    private String description;
    @Element(name="generator")
    private String generator;
    @Element(name="link")
    private String link;
    @Element(name="lastBuildDate")
    private String lastBuildDate;
    @Element(name="result")
    private Integer result;
    @Element(name="pageCount")
    private Integer pageCount;
    @Element(name="totalCount")
    private Integer totalCount;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenerator() {
        return generator;
    }

    public String getLink() {
        return link;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public Integer getResult() {
        return result;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    @ElementList(entry ="item",inline = true)
    private List<Item> itemList;

    public List<Item> getItemList(){
        return itemList;
    }





}
