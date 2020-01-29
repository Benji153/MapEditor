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

    Color color;
    Random rand = new Random();
    int id;

    public Tile() {
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.id = 0;
    }
    
    public Tile(int id){
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.id = id;
    }
    
    public Tile(Tile other) {
        this.color = other.color;
        this.id = other.id;
    }
    
    public void setId(int id){
        this.id=id;
    }

}
