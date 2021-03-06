/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.dialog.JideOptionPane;
import com.przemo.tradex.data.Equities;
import com.przemo.tradexclient.ConnectionHolder;
import com.przemo.tradexclient.interfaces.IUpdateRequiring;
import com.przemo.tradexclient.remote.RemoteAction;
import com.przemo.tradexclient.remote.RemoteActionInitializationException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The form allows the users to place orders of a given equity.
 * If there is no value placed in the price field, clicking a button will place order at current price.
 * If there is a price typed in the text field, the order will be placed at the specified price.
 * @author Przemo
 */
public class PlaceOrderForm extends javax.swing.JFrame implements IUpdateRequiring, WindowListener {

    private Equities eq = null;
    
    /**
     * Creates new form PlaceOrderForm
     */
    public PlaceOrderForm(Equities equity) {

        if (equity != null) {
            initComponents();
            ConnectionHolder.registerUpdateRequiring(this);
            eq = equity;
            fillData();
        }

    }

    private void fillData(){
        if(eq!=null){
                lblAskPrice.setText(String.format("%.4f",eq.getAskPrice() ));
                lblBidPrice.setText(String.format("%.4f", eq.getBidPrice()));
                lblEquityName.setText(eq.getEquitySymbol());  
            } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEquityName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBidPrice = new javax.swing.JLabel();
        lblAskPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnSell = new javax.swing.JButton();
        btnBuy = new javax.swing.JButton();
        txtAmount = new javax.swing.JTextField();

        setTitle("Place Order");

        lblEquityName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEquityName.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Bid");

        jLabel2.setText("Ask");

        lblBidPrice.setText("jLabel3");

        lblAskPrice.setText("jLabel3");

        txtPrice.setText("0,00");

        btnSell.setText("Sell");
        btnSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellActionPerformed(evt);
            }
        });

        btnBuy.setText("Buy");
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });

        txtAmount.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEquityName)
                .addGap(166, 166, 166))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnSell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2))
                    .addComponent(lblAskPrice))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBidPrice)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)))
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEquityName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBidPrice))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAskPrice)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSell)
                    .addComponent(btnBuy))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        sendOrder(RemoteAction.ORDER_SELL);     
    }//GEN-LAST:event_btnSellActionPerformed

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        sendOrder(RemoteAction.ORDER_BUY);
    }//GEN-LAST:event_btnBuyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnSell;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAskPrice;
    private javax.swing.JLabel lblBidPrice;
    private javax.swing.JLabel lblEquityName;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

    
    @Override
    public void setVisible(boolean show) {
        if (eq == null && show==true) {
            JideOptionPane.showMessageDialog(this, "No equity selected");
            this.dispose();
        } else {
            super.setVisible(show);
        }
    }
    
    @Override
    public final void updateData() {
        try {
            Equities eqRequested = RemoteAction.getEquityQuotation(eq);
            if (eqRequested != null && eqRequested.getId() == eq.getId()) {
                eq = eqRequested;
                fillData();
            }
        } catch (RemoteActionInitializationException | RemoteException | NotBoundException ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void  sendOrder(int direction){
        if(eq!=null){
            double priceTyped=0, amount=0;
            try{
                priceTyped=Double.parseDouble(txtPrice.getText());
            } catch(NumberFormatException ex){
                System.err.println(ex.getMessage());
            }
            if(priceTyped==0){
                if(direction==RemoteAction.ORDER_SELL){
                    priceTyped=eq.getAskPrice();
                } else if(direction== RemoteAction.ORDER_BUY){
                    priceTyped=eq.getBidPrice();
                }
            }
            try{
                amount=Double.parseDouble(txtAmount.getText());
            } catch(NumberFormatException ex){
                System.err.println(ex.getMessage());
            }
            if(priceTyped>0){
                try {
                    String message = "Your order has been placed";
                    if(!RemoteAction.placeOrder(eq, amount, priceTyped, direction)){
                        message="Your order could NOT be placed!";
                    }
                    JideOptionPane.showMessageDialog(this, message);
                } catch (RemoteActionInitializationException | RemoteException | NotBoundException ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ConnectionHolder.unregisterUpdateRequiring(this);
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
}
