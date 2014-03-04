package com.przemo.tradexclient;

import com.przemo.tradexclient.gui.MainWindow;
import com.przemo.tradexclient.remote.RemoteAction;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) {
        RemoteAction.initRegistry(ConnectionHolder.SERVER_ADDR, 6020);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }
}
