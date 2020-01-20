/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author benji
 */
public class BlankTile extends Tile{
    Random rand = new Random();
    
    public BlankTile(){
        color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }
    
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(10, 10, 10, 10);
    }
    
}
