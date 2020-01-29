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
    TileSet tileSet;
    
    public Map(){
        tilesW = 20;
        tilesH = 20;
        tiles = new Tile[tilesW][tilesH];
        tileSet = new TileSet();
    }
    
    public Map(int x,int y){
        tilesW = x;
        tilesH = y;
        tiles = new Tile[tilesW][tilesH];
        tileSet = new TileSet();
    }
    
    public void setClear(){
        for(int y = 0;y<tilesH;y++){
            for(int x = 0;x<tilesW;x++){
                tiles[x][y] = new Tile();
            }
        }
        tileSet.clear();
    }
    
    public void drawMap(Graphics g,int tileWidth,int tileHeight){
        for(int y = 0;y<tilesH;y++){
            for(int x = 0;x<tilesW;x++){
                Tile t = tiles[x][y];
                if(t.id != 0){
                    System.out.print(tileSet.tiles.size() + " Tiles -  ");
                    System.out.println(tileSet.images.size() + " Images");
                    g.drawImage(tileSet.getImage(t.id), x*tileWidth,y*tileHeight, tileWidth, tileHeight,null);
                }else{
                    g.setColor(t.color);
                    g.fillRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
                }
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
    public void setTile(int x,int y,int tileW,int tileH,Tile t){
        int tx = x/tileW;
        int ty = y/tileH;
        if(tx <tilesW && ty<tileH){
            System.out.println(tiles[tx][ty].id);
            tiles[tx][ty] = t;
        }
    }
    
    public void newMap(int tX,int tY){
        tilesW = tX;
        tilesH = tY;
        tiles = new Tile[tilesW][tilesH];
        setClear();
    }
 
    public int getTileCount(){
        return tileSet.getTiles().length;
    }
    
}
