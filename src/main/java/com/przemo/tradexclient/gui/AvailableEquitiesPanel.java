/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.przemo.tradex.data.Equities;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemo
 */
public class AvailableEquitiesPanel extends JPanel{
    
    Set<Equities> equities=null;
    JTable tab = null;
    DefaultTableModel model = null;
    String[] columns = {"Equity", "Ask", "Bid"};
    
    public AvailableEquitiesPanel(){
        tab = new JTable();
        build();
    }
    
    final void build(){
        this.add(tab);
    }
    
    public void setEquities(Set<Equities> eq){
        equities=eq;
        fillEquitiesPanel();
    }
    
    private void fillEquitiesPanel(){   
        if(model==null){
            model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int col){
                    return false;
                }
            };
            model.addTableModelListener(tab);
            tab.setModel(model);
        }    
        model.setDataVector(convertEquitiesToTable(), columns);
    }

    private Object[][] convertEquitiesToTable() {
        Object[][] o = new Object[equities.size()][columns.length];
        Iterator<Equities> it = equities.iterator();
        Equities eq=null;
        int i = 0;
        while(it.hasNext() && (eq=it.next())!=null){
            o[i][0]=eq.getEquitySymbol();
            o[i][1]=eq.getAskPrice();
            o[i][2]=eq.getBidPrice();
            i++;
        }
        return o;
    }
}
