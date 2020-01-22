/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author benji
 */
public class Tile implements java.io.Serializable{
    
    Image img;
    Color color;
    Random rand = new Random();
    
    public Tile(){
        color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }
    
    public void draw(Graphics g,int x,int y,int width,int height){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
}
