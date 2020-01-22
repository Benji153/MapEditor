/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.io.*;

/**
 *
 * @author benji
 */
public class FileHandler {
    
    Map map;
    
    public FileHandler(Map m){
        map = m;
    }
    
    public void saveFile(String path) throws FileNotFoundException, IOException{
        System.out.println("Beans");
        try (FileOutputStream saveFile = new FileOutputStream(path); ObjectOutputStream save = new ObjectOutputStream(saveFile)) {
            save.writeInt(map.tilesW);
            save.writeInt(map.tilesH);
            for(int y =0;y< map.tilesH;y++){
                for(int x=0;x<map.tilesW;x++){
                    save.writeObject(map.tiles[x][y]);
                }
            }
        }
    }
    
    public void loadFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        try (FileInputStream saveFile = new FileInputStream(path); ObjectInputStream restore = new ObjectInputStream(saveFile)) {
            int tX = restore.readInt();
            int tY = restore.readInt();
            map.newMap(tX,tY);
            for(int y =0;y< map.tilesH;y++){
                for(int x=0;x<map.tilesW;x++){
                    map.tiles[x][y] = (Tile)restore.readObject();
                }
            }
            restore.close();
            saveFile.close();
        }
    }
    
    
}
