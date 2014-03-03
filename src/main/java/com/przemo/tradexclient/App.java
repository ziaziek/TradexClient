package com.przemo.tradexclient;

import com.przemo.tradexclient.gui.MainWindow;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {

    static String sessionId= null;
    public static String SERVER_ADDR = "localhost"; //TODO: change to a property
    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        App.sessionId = sessionId;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }
}
