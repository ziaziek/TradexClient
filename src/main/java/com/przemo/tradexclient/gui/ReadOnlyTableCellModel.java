/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemo
 */
public class ReadOnlyTableCellModel extends DefaultTableModel {
   
    @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
}
