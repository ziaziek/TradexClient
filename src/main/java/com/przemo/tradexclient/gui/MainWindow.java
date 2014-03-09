/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.dialog.JideOptionPane;
import com.jidesoft.swing.JideMenu;
import com.przemo.tradex.data.Equities;
import com.przemo.tradexclient.ConnectionHolder;
import com.przemo.tradexclient.interfaces.ILoginSensitive;
import com.przemo.tradexclient.remote.RemoteAction;
import com.przemo.tradexclient.remote.RemoteActionInitializationException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 *
 * @author Przemo
 */
public class MainWindow extends JFrame implements ActionListener, WindowListener, ILoginSensitive {
    
    JideMenu mainMenu = null;
    JPanel mainPanel = null;
    JPanel statusBar=null;
    JLabel loggedInAs=null;
    AvailableEquitiesPanel eqPanel = null;
    
    public MainWindow(){
        super();
        this.setPreferredSize(new Dimension(800,300));
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        this.setTitle("Tradex Client");
        try{
            buildMenu();    
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            this.dispose();
        }       
        mainPanel(); 
        buildStatusPanel();
        buildEquitiesPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addWindowListener(this);
        ConnectionHolder.registerLoginSensitive(this);
    }
    
    final void buildEquitiesPanel(){
        if(eqPanel==null){
            eqPanel = new AvailableEquitiesPanel();
            eqPanel.setPreferredSize(new Dimension(250, this.getHeight()));
            this.add(eqPanel, BorderLayout.WEST);
        }     
    }
    final void mainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setPreferredSize(new Dimension(150, 150));
        this.add(mainPanel, BorderLayout.CENTER);
    }
    
    final void buildMenu() throws Exception {
        JMenuBar cmBar = new JMenuBar();
        MenuBuilder mb = new MenuBuilder(cmBar);
        mb.setAcListener(this);
        mb.build();
        this.setJMenuBar(mb.getMenuProduced());
    }

    final void buildStatusPanel(){
        statusBar = new JPanel(new BorderLayout());
        loggedInAs = new JLabel("NOT logged in");
        statusBar.add(loggedInAs, BorderLayout.EAST);
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }
    
    private void handleClosing(){
        if (JideOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Tradex Client", JideOptionPane.YES_NO_OPTION) == JideOptionPane.YES_OPTION) {
            if (ConnectionHolder.getSessionId() != null) {
                try {
                    RemoteAction.logOut();
                } catch (RemoteActionInitializationException | RemoteException | NotBoundException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.exit(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentOption=null;
        if(e.getSource() instanceof JMenuItem){
            currentOption = ((JMenuItem)e.getSource()).getText();
        } else {
            return;
        }
        switch(currentOption){
            case MenuBuilder.EXIT:
                handleClosing();
            break;
            case MenuBuilder.CONNECT:
                LoginForm lf = new LoginForm();
                lf.setVisible(true);
                Dimension dScreen = Toolkit.getDefaultToolkit().getScreenSize();
                lf.setLocation((int)((dScreen.getWidth()- lf.getWidth())/2) , (int)((dScreen.getHeight() - lf.getHeight())/2));
                break;
            case MenuBuilder.DISCONNECT:
                try {
                    RemoteAction.logOut();
                } catch (RemoteActionInitializationException | RemoteException | NotBoundException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case MenuBuilder.SHOW_AVAILABLE:
                
                break;
            case MenuBuilder.RELOAD_AVAILABLE:
                refreshEquitiesPanel();
                break;
            case MenuBuilder.OPEN_CHART_WINDOW:
                
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        handleClosing();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void loginChanged(boolean isLoggedin) {
        if(isLoggedin){
            loggedInAs.setText("Logged In");
            refreshEquitiesPanel();    
        } else {
            loggedInAs.setText("NOT logged in");
        }
    }

    private void refreshEquitiesPanel(){
        try {
                eqPanel.setEquities(RemoteAction.getEquities(ConnectionHolder.getSessionId()));
            } catch (RemoteActionInitializationException | RemoteException | NotBoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
