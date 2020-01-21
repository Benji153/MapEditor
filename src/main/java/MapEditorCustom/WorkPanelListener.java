/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorCustom;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author benji
 */
public class WorkPanelListener implements MouseListener{

    WorkPanel myPanel;
    
    public WorkPanelListener(WorkPanel p){
        myPanel = p;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("X: " + e.getX() + " - Y: " + e.getY());
        myPanel.changeTile(e.getX(),e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
