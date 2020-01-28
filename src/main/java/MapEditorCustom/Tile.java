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
public class Tile implements java.io.Serializable {

    transient Image img = null;
    Color color;
    Random rand = new Random();

    public Tile() {
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public Tile(Image img) {
        this.img = img;
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public Tile(Tile other) {
        this.img = other.img;
        this.color = other.color;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        if (img != null) {
            g.drawImage(img, x, y, width, height, null);
        } else {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

}
