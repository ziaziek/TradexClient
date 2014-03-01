package com.przemo.tradexclient;

import com.przemo.tradexclient.gui.MainWindow;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }
}
