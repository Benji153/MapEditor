/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.event.ListSelectionListener;

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
        ArrayList<Color> sprites = new ArrayList();
        sprites.add(Color.RED);
        sprites.add(Color.BLUE);
        sprites.add(Color.GREEN);

        JFrame window = new JFrame("Map Maker");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000, 750);

        //building the workspace
        WorkPanel mainSpace = new WorkPanel();
        mainSpace.addMouseListener(new WorkPanelListener(mainSpace));
        mainSpace.update.start();

        //Building the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
        JMenu file = new JMenu("File");
        //Exit button
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener((ActionEvent ev) -> {
            System.exit(0);
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
                    mainSpace.newMap(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "You need to enter numbers for the values!.",
                            "Wrong input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(exit);
        file.add(newMap);
        menuBar.add(file);

        //Building the ListView
        JList list = new JList(sprites.toArray());
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(150, 10));
        list.addListSelectionListener((ListSelectionEvent e) -> {
            mainSpace.setColor((Color) list.getSelectedValue());
        });

        //adding components to the frame
        window.add(menuBar, BorderLayout.PAGE_START);
        window.add(mainSpace, BorderLayout.CENTER);
        window.add(scrollPane, BorderLayout.WEST);

        //display the frame
        window.setVisible(true);
    }

}
