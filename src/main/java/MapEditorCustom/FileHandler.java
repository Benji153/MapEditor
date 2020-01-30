/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author benji
 */
public class FileHandler {

    Map map;

    public FileHandler(Map m) {
        map = m;
    }

    public void saveFile(String path) throws FileNotFoundException, IOException {
        System.out.println("Beans");
        File file = new File(path);
        file.mkdir();
        try (FileOutputStream saveFile = new FileOutputStream(path + "\\Tiles.ben"); ObjectOutputStream save = new ObjectOutputStream(saveFile)) {
            save.writeInt(map.tilesX);
            save.writeInt(map.tilesY);
            save.writeInt(map.tilesW);
            save.writeInt(map.tilesH);
            save.writeInt(map.getTileCount());
            for (int y = 0; y < map.tilesY; y++) {
                for (int x = 0; x < map.tilesX; x++) {
                    save.writeObject(map.tiles[x][y]);
                }
            }
            //needs to be fixed to support different tile sizes
            BufferedImage bufferedImage = new BufferedImage(map.getTileCount() * map.tilesW, map.tilesH, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            
            for (int i = 0; i < map.getTileCount(); i++) {
                g2d.drawImage(map.tileSet.getImage(i+1), i * map.tilesW, 0, map.tilesW, map.tilesH, null);
            }
            g2d.dispose();
            file = new File(path + "\\TileSet.png");
            ImageIO.write(bufferedImage, "png", file);
        }

    }

    public void loadFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (FileInputStream saveFile = new FileInputStream(path + "\\Tiles.ben"); ObjectInputStream restore = new ObjectInputStream(saveFile)) {
            int tX = restore.readInt();
            int tY = restore.readInt();
            int tW = restore.readInt();
            int tH = restore.readInt();
            int tileCount = restore.readInt();
            map.newMap(tX,tY,tW,tH);
            
            BufferedImage sheet = ImageIO.read(new File(path + "\\TileSet.png"));

            for (int i = 0; i < tileCount; i++) {
                Image img = sheet.getSubimage(i * map.tilesW, 0, map.tilesW, map.tilesH);
                Tile t = new Tile();
                map.tileSet.addTile(t,img);
            }

            for (int y = 0; y < map.tilesY; y++) {
                for (int x = 0; x < map.tilesX; x++) {
                    map.tiles[x][y] = (Tile) restore.readObject();
                    for (int i = 0; i < map.getTileCount(); i++) {
                        if (map.tiles[x][y] != null && map.tileSet.getTiles()[i] != null) {
                            if (map.tiles[x][y].id == map.tileSet.getTiles()[i].id) {
                                //map.tiles[x][y].img = map.tileSet.getTiles()[i].img;
                            }
                        }
                    }
                }
            }
            restore.close();
            saveFile.close();
        }
    }

    /*
    TileSet 
    -save full tileset as a single image(tilesheet)
    save url to tileset in a new map folder
    save all current tiles with image id refrence 
    
     */
}
