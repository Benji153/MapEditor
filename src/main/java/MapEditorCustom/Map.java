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
    int tilesX,tilesY,tilesW,tilesH;
    TileSet tileSet;
    
    public Map(){
        tilesX = 20;
        tilesY = 20;
        tilesW = 20;
        tilesH = 20;
        tiles = new Tile[tilesX][tilesY];
        tileSet = new TileSet();
    }
    
    public Map(int x,int y,int w,int h){
        tilesX = x;
        tilesY = y;
        tilesW = w;
        tilesH = h;
        tiles = new Tile[tilesX][tilesY];
        tileSet = new TileSet();
    }
    
    public void setClear(){
        for(int y = 0;y<tilesY;y++){
            for(int x = 0;x<tilesX;x++){
                tiles[x][y] = new Tile();
            }
        }
        tileSet.clear();
    }
    
    public void drawMap(Graphics g){
        for(int y = 0;y<tilesY;y++){
            for(int x = 0;x<tilesX;x++){
                Tile t = tiles[x][y];
                if(t.id != 0){
                    g.drawImage(tileSet.getImage(t.id), x*tilesW,y*tilesH, tilesW, tilesH,null);
                }else{
                    g.setColor(t.color);
                    g.fillRect(x*tilesW, y*tilesH, tilesW, tilesH);
                }
            }
        }
    }
    
    public Tile getTile(int x,int y,int tileW,int tileH){
        int tx = x/tileW;
        int ty = y/tileH;
        if(tx <tilesX && ty<tileH){
            return tiles[tx][ty];
        }
        else return null;
    }
    public void setTile(int x,int y,Tile t){
        int tx = x/tilesW;
        int ty = y/tilesH;
        if(tx <tilesX && ty<tilesY){
            System.out.println(tiles[tx][ty].id);
            tiles[tx][ty] = t;
        }
    }
    
    public void newMap(int tX,int tY,int tW,int tH){
        tilesX = tX;
        tilesY = tY;
        tilesW = tW;
        tilesH = tH;
        tiles = new Tile[tilesX][tilesY];
        setClear();
    }
 
    public int getTileCount(){
        return tileSet.getTiles().length;
    }
    
}
