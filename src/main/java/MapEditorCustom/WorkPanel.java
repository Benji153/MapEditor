/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author benji
 */
public class WorkPanel extends JPanel implements Runnable{
    
    int tileWidth,tileHeight;
    Map map;
    boolean running = true;
    Thread update;
    Color selectedColor = Color.blue;
    
    public WorkPanel(){
        map = new Map();
        map.setClear();
        tileWidth = 25;
        tileHeight = 25;
        update = new Thread(this);
    }
    
    public void newMap(int tilesX,int tilesY){
        map = new Map(tilesX,tilesY);
        map.setClear();
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.BLACK);
        int scrWidthDiv = this.getWidth() / 5;
        for(int i = 0;i<5;i++){
            g.drawLine(i * scrWidthDiv, this.getHeight(), (i+1)*scrWidthDiv, 0);
        }
        draw(g);
    }
    
    public void draw(Graphics g){
        map.drawMap(g, tileWidth, tileHeight);
    }

    
    public void run() {
        while(running){
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WorkPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void changeTile(int x,int y){
        map.getTile(x, y, tileWidth, tileHeight).color = selectedColor;
    }
    
    public void setColor(Color c){
        selectedColor = c;
    }
    
}
