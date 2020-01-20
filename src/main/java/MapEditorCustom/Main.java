/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 *
 * @author benji
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        //initialize list of images for map building
        ArrayList<String> sprites = new ArrayList();
        sprites.add("Test Because i just learned of preffered width");
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        
        //building the workspace
        WorkPanel mainSpace = new WorkPanel();
        
        
        //Building the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
        JMenu file = new JMenu("File");
        menuBar.add(file);
        
        //Building the ListView
        JList list = new JList(sprites.toArray());
        JScrollPane scrollPane = new JScrollPane(list);
        list.setPreferredSize(new Dimension(100,10));
        
        //adding components to the frame
        window.add(menuBar, BorderLayout.PAGE_START);
        window.add(mainSpace , BorderLayout.CENTER);
        window.add(scrollPane, BorderLayout.WEST);
        
        //display the frame
        window.setVisible(true);
    }
    
}
