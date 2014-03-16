/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.przemo.tradex.data.Equities;
import java.awt.Color;
import java.awt.Component;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemo
 */
public class AvailableEquitiesPanel extends JPanel{
    
    Set<Equities> equities =null;
    JTable tab = null;
    DefaultTableModel model = null;
    String[] columns = {"Equity", "Ask", "Bid"};
    
    public AvailableEquitiesPanel(){
        tab = new JTable();
        tab.setDefaultRenderer(Object.class, new ChangedValueCellRenderer());
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
        Object[][] res = convertEquitiesToTable(equities);
        if(res.length!=model.getRowCount()){
            model.setDataVector(res, columns);
        } else {
            for(int i=0; i<equities.size(); i++){
                for(int j=0; j<columns.length; j++){
                    if(model.getValueAt(i, j)!=res[i][j]){
                        model.setValueAt(res[i][j], i, j);
                        
                    }
                }
            }
        }
        tab.revalidate();
        if(tab.getDefaultRenderer(Object.class) instanceof ChangedValueCellRenderer){
                ((ChangedValueCellRenderer)tab.getDefaultRenderer(Object.class)).setPreviousState(res);
            }
    }

    private Object[][] convertEquitiesToTable(Set<Equities> equities) {
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
    
    private class ChangedValueCellRenderer extends DefaultTableCellRenderer{
       
        Object[][] previousState;

        public Object[][] getPreviousState() {
            return previousState;
        }

        public void setPreviousState(Object[][] previousState) {
            this.previousState = previousState;
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean isFocused, int row, int col){
            Component c = super.getTableCellRendererComponent(table, o, isSelected, isFocused, row, col);
            //we will check only against these two cases
            System.out.println("o class is."+ o.getClass().getName());
            if(o instanceof Double && getPreviousState()!=null){
                System.out.println("Checking values.");
                if((Double)o > (Double)getPreviousState()[row][col]){
                    c.setForeground(Color.green);
                } else {
                    c.setForeground(Color.red);
                }
            } else {
                c.setForeground(Color.black);
            }
            return c;            
        }       
    }
}
