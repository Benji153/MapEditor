
package MapEditorCustom;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class ImageHandler {
    
    ArrayList<Image> images = new ArrayList();
    
    public Image[] sliceSpriteSheet(String url,int xTiles,int yTiles,int tileWidth,int tileHeight) throws IOException{
        BufferedImage sheet = ImageIO.read(new File(url));
        for(int y = 0;y<yTiles;y++){
            for(int x = 0;x<xTiles;x++){
                Image img = sheet.getSubimage(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
                images.add(img);
            }
        }
        Image[] imgs = new Image[images.size()];
        for(int i = 0;i<images.size();i++){
            imgs[i] = images.get(i);
        }
        return imgs;
        
    }
    
    
}
