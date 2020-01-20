/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Graphics;

/**
 *
 * @author benji
 */
public class Map {
    
    Tile[][] tiles;
    int tilesW,tilesH;
    
    public Map(){
        tilesW = 10;
        tilesH = 10;
        tiles = new Tile[tilesW][tilesH];
    }
    
    public void setClear(){
        for(int y = 0;y<tilesH;y++){
            for(int x = 0;x<tilesW;x++){
                tiles[x][y] = new BlankTile();
            }
        }
    }
    
    public void drawMap(Graphics g,int tileWidth,int tileHeight){
        for(int y = 0;y<tilesH;y++){
            for(int x = 0;x<tilesW;x++){
                tiles[x][y].draw(g, x*tileWidth,y*tileHeight, tileWidth, tileHeight);
            }
        }
    }
    
}
