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
        tilesW = 20;
        tilesH = 20;
        tiles = new Tile[tilesW][tilesH];
    }
    
    public Map(int x,int y){
        tilesW = x;
        tilesH = y;
        tiles = new Tile[tilesW][tilesH];
    }
    
    public void setClear(){
        for(int y = 0;y<tilesH;y++){
            for(int x = 0;x<tilesW;x++){
                tiles[x][y] = new Tile();
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
    
    public Tile getTile(int x,int y,int tileW,int tileH){
        int tx = x/tileW;
        int ty = y/tileH;
        if(tx <tilesW && ty<tileH){
            return tiles[tx][ty];
        }
        else return null;
    }
    
}
