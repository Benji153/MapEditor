/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;

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
        Map map = new Map();

        JFrame window = new JFrame("Map Maker");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000, 750);

        //building the workspace
        WorkPanel mainSpace = new WorkPanel(map);
        mainSpace.addMouseListener(new WorkPanelListener(mainSpace));
        mainSpace.update.start();
        FileHandler fh = new FileHandler(map);
        ImageHandler ih = new ImageHandler();

        //Building the ListView
        JList list = new JList(map.tileSet.getTiles());
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(150, 10));
        list.addListSelectionListener((ListSelectionEvent e) -> {
            mainSpace.setTile((Tile) list.getSelectedValue());
        });

        //Building the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
        JMenu file = new JMenu("File");
        //Exit button
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener((ActionEvent ev) -> {
            System.exit(0);
        });
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener((ActionEvent ev) -> {

            JFileChooser fc = new JFileChooser();
            fc.setApproveButtonText("Save");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    fh.saveFile(fc.getSelectedFile().getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener((ActionEvent ev) -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    fh.loadFile(fc.getSelectedFile().getAbsolutePath());
                    list.setListData(map.tileSet.getTiles());
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //New Map Button
        JMenuItem newMap = new JMenuItem("New");
        newMap.addActionListener((ActionEvent ev) -> {

            JTextField xField = new JTextField(5);
            JTextField yField = new JTextField(5);

            JPanel newMapChooser = new JPanel();
            newMapChooser.setLayout(new BoxLayout(newMapChooser, BoxLayout.PAGE_AXIS));
            newMapChooser.add(new JLabel("Horizontal Tile Count:"));
            newMapChooser.add(xField);
            newMapChooser.add(Box.createHorizontalStrut(15)); // a spacer
            newMapChooser.add(new JLabel("Vertical Tile Count:"));
            newMapChooser.add(yField);

            int result = JOptionPane.showConfirmDialog(null, newMapChooser,
                    "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                System.out.println("x value: " + xField.getText());
                System.out.println("y value: " + yField.getText());
                try {
                    mainSpace.map.newMap(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "You need to enter numbers for the values!.",
                            "Wrong input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(save);
        file.add(load);
        file.add(newMap);
        file.add(exit);
        menuBar.add(file);

        //Tile Menu
        JMenu tile = new JMenu("Tiles");
        JMenuItem newSpriteSheet = new JMenuItem("Load Sprite Sheet");
        newSpriteSheet.addActionListener((ActionEvent ev) -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    JTextField xField = new JTextField(5);
                    JTextField yField = new JTextField(5);
                    JTextField wField = new JTextField(5);
                    JTextField hField = new JTextField(5);

                    JPanel newMapChooser = new JPanel();
                    newMapChooser.setLayout(new BoxLayout(newMapChooser, BoxLayout.PAGE_AXIS));
                    newMapChooser.add(new JLabel("Horizontal Sprite Count:"));
                    newMapChooser.add(xField);
                    newMapChooser.add(Box.createHorizontalStrut(15)); // a spacer
                    newMapChooser.add(new JLabel("Vertical Sprite Count:"));
                    newMapChooser.add(yField);
                    newMapChooser.add(Box.createHorizontalStrut(15)); // a spacer
                    newMapChooser.add(new JLabel("Sprite Width:"));
                    newMapChooser.add(wField);
                    newMapChooser.add(Box.createHorizontalStrut(15)); // a spacer
                    newMapChooser.add(new JLabel("Sprite Height:"));
                    newMapChooser.add(hField);

                    int result = JOptionPane.showConfirmDialog(null, newMapChooser,
                            "Please Sprite Sheet Information", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        System.out.println("x value: " + xField.getText());
                        System.out.println("y value: " + yField.getText());
                        try {
                            Image[] imgs = ih.sliceSpriteSheet(fc.getSelectedFile().getAbsolutePath(), Integer.parseInt(xField.getText()),
                                    Integer.parseInt(yField.getText()), Integer.parseInt(wField.getText()), Integer.parseInt(hField.getText()));
                            for (Image img : imgs) {
                                Tile t = new Tile();
                                map.tileSet.addTile(t, img);
                                list.setListData(map.tileSet.getTiles());
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null,
                                    "You need to enter numbers for the values!.",
                                    "Wrong input",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tile.add(newSpriteSheet);
        menuBar.add(tile);

        //adding components to the frame
        window.add(menuBar, BorderLayout.PAGE_START);
        window.add(mainSpace, BorderLayout.CENTER);
        window.add(scrollPane, BorderLayout.WEST);

        //display the frame
        window.setVisible(true);
    }

}
