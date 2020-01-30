/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author benji
 */
public class WorkPanel extends JPanel implements Runnable {

    int tilesX, tilesY, tilesW, tilesH;
    //unused scaling for later zoom
    double scale = 1.0;
    Map map;
    boolean running = true;
    Thread update;
    Tile selectedTile;

    public WorkPanel(Map map) {
        this.map = map;
        this.map.setClear();
        tilesX = 25;
        tilesY = 25;
        tilesW = 20;
        tilesH = 20;
        selectedTile = new Tile();
        update = new Thread(this);
    }

    public void newMap(int tilesX, int tilesY, int tilesW, int tilesH) {
        map = new Map(tilesX, tilesY, tilesW, tilesH);
        map.setClear();
        Tile tmp = new Tile();
        tmp.setId(0);
        selectedTile = tmp;

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        int scrWidthDiv = this.getWidth() / 5;
        for (int i = 0; i < 5; i++) {
            g.drawLine(i * scrWidthDiv, this.getHeight(), (i + 1) * scrWidthDiv, 0);
        }
        draw(g);
    }

    public void draw(Graphics g) {
        map.drawMap(g);
        Image selected = map.tileSet.getImage(selectedTile.id);
        if (selected != null) {
            g.drawImage(selected, 300, 300, this);
        }
    }

    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WorkPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changeTile(int x, int y) {
        map.setTile(x, y, new Tile(selectedTile));
    }

    public void setTile(Tile t) {
        selectedTile = t;
    }

}
