/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.dialog.StandardDialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Przemo
 */
public class OKMessageBox extends StandardDialog implements ActionListener{

    String title, message;
    JFrame parent = null;
    
    public OKMessageBox( JFrame parent, String message, String title){
        this.title = title;
        this.message=message;
        this.parent = parent;
        this.setLayout(new FlowLayout());
    }
    
    @Override
    public JComponent createBannerPanel() {
         return new JLabel(title);
    }

    @Override
    public JComponent createContentPanel() {
        return new JLabel(message);
    }

    @Override
    public ButtonPanel createButtonPanel() {
        ButtonPanel p = new ButtonPanel(ButtonPanel.SAME_SIZE);
        JButton b = new JButton("OK");
        b.addActionListener(this);
        p.addButton(b);
        return p;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
    
}
