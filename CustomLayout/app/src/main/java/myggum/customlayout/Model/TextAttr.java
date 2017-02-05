package myggum.customlayout.Model;

/**
 * Created by Administrator on 2016-12-06.
 */

public class TextAttr {
    int Image;
    String textattrname;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
      Image = image;
    }


    public String getTextattrname() {
        return textattrname;
    }

    public void setTextattrname(String textattrname) {
        this.textattrname = textattrname;
    }

    public TextAttr(int image, String textattrname) {
        Image = image;
        this.textattrname = textattrname;
    }
}
