/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.przemo.tradexclient.ConnectionHolder;
import com.przemo.tradexclient.datamodels.ModelCreator;
import com.przemo.tradexclient.interfaces.ILoginSensitive;
import com.przemo.tradexclient.interfaces.IUpdateRequiring;
import com.przemo.tradexclient.remote.RemoteAction;
import com.przemo.tradexclient.remote.RemoteActionInitializationException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemo
 */
public class AccountInfoPanel extends javax.swing.JPanel implements ILoginSensitive, IUpdateRequiring {

    DefaultTableModel activityModel, transactionsModel = null;
    
    /**
     * Creates new form AccountInfoPanel
     */
    public AccountInfoPanel() {
        initComponents();
        initTables();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountTabPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logActivityTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionsTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        accountTabPane.setName("Login Activity"); // NOI18N

        logActivityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(logActivityTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );

        accountTabPane.addTab("Login Activity", jPanel1);

        transactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(transactionsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );

        accountTabPane.addTab("Transactions History", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        accountTabPane.addTab("Open orders", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        accountTabPane.addTab("Account Balance", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountTabPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );

        accountTabPane.getAccessibleContext().setAccessibleName("accountTab");
        accountTabPane.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane accountTabPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable logActivityTable;
    private javax.swing.JTable transactionsTable;
    // End of variables declaration//GEN-END:variables

    private void initTables(){
        if(activityModel==null){
                activityModel= createEmptyModel(ModelCreator.getActivityModelColumns());
                logActivityTable.setModel(activityModel);
                activityModel.addTableModelListener(logActivityTable);
            }
        if(transactionsModel==null){
            transactionsModel = createEmptyModel(ModelCreator.getTransactionsModelColumns());
            transactionsTable.setModel(transactionsModel);
            transactionsModel.addTableModelListener(transactionsTable);
        }
    }
    
    @Override
    public void loginUpdate(boolean isLoggedin) {
                initTables();
                if(logActivityTable.getModel() instanceof DefaultTableModel){
                try {
                    ((DefaultTableModel)logActivityTable.getModel()).setDataVector(ModelCreator.createActivityModelData(RemoteAction.getSessionsHistory(ConnectionHolder.getSessionId())), ModelCreator.getActivityModelColumns() );
                } catch (        RemoteActionInitializationException | RemoteException | NotBoundException ex) {
                    Logger.getLogger(AccountInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    private DefaultTableModel createEmptyModel(Object[] columns){
        ReadOnlyTableCellModel ret = new ReadOnlyTableCellModel();
            //Create the model
            ret.setDataVector(null, columns);
        return ret;
    }

    @Override
    public void updateData() {
        //update other tables that need it
    }

}