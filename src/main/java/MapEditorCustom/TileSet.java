/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author benji
 */
public class TileSet {
    
    ArrayList<Tile> tiles;
    ArrayList<Image> images; 
    
    public TileSet(){
        tiles = new ArrayList();
        images = new ArrayList();
    }
    
    public Tile[] getTiles(){
        Tile[] t = new Tile[tiles.size()];
        for(int i = 0;i<tiles.size();i++){
            t[i] = tiles.get(i);
        }
        return t;
    }
    
    public void setTiles(Tile[] t,Image[] img){
        tiles.clear();
        for(int i = 0;i<t.length;i++){
            tiles.add(t[i]);
            tiles.get(tiles.size()-1).setId(tiles.size());
            images.add(img[i]);
        }
    }
    
    public void addTiles(Tile[] t,Image[] img){
        for(int i = 0;i<t.length;i++){
            tiles.add(t[i]);
            tiles.get(tiles.size()-1).setId(tiles.size());
            images.add(img[i]);
        }
    }
    
    public void addTile(Tile t,Image img){
        tiles.add(t);
        t.setId(tiles.size());
        images.add(img);
    }
    
    //this code will probably cause problems as current tiles on map will not have their tile id updated yet
    public void removeTile(Tile t){
        int index = tiles.indexOf(t);
        tiles.remove(t);
        for(int i = index;i<tiles.size();i++){
            tiles.get(i).setId(i+1);
        }
    }
    
    public Image getImage(int id){
        for(int i = 1;i<=images.size();i++){
            if(i == id){
                return images.get(i-1);
                
            }
        }
        return null;
    }
    
    public void clear(){
        tiles.clear();
        images.clear();
    }
    
}
