/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author benji
 */
public class WorkPanel extends JPanel{
    
    int tileWidth,tileHeight;
    Map map;
    
    public WorkPanel(){
        map = new Map();
        map.setClear();
        tileWidth = 20;
        tileHeight = 20;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        draw(g);
    }
    
    public void draw(Graphics g){
        map.drawMap(g, tileWidth, tileHeight);
    }
    
}
