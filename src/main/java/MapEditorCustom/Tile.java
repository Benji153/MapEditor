/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author benji
 */
public abstract class Tile {
    
    Image img;
    Color color;
    
    public Tile(){
        
    }
    
    public void draw(Graphics g,int x,int y,int width,int height){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
}
