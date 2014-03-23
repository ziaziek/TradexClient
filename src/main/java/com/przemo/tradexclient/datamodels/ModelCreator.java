/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.datamodels;

import com.przemo.tradex.data.Transactions;
import com.przemo.tradex.data.UserSessions;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class ModelCreator {
    
    static Object[] activityModelColumns = new Object[] {"Login date", "Logout date", "IP Address"};

    public static Object[] getActivityModelColumns() {
        return activityModelColumns;
    }

    public static Object[] getTransactionsModelColumns() {
        return transactionsModelColumns;
    }
    
    static Object[] transactionsModelColumns = new Object[]{"Equity name", "Date", "Quantity", "Price", "Buy/Sell"};
    
    public static Object[][] createActivityModelData(List<UserSessions> list){
        Object[][] ret = null;
        if(list!=null && !list.isEmpty()){
            ret = new Object[list.size()][activityModelColumns.length];
            //implement the logic
            int p = 0;
        for(UserSessions us : list){
            ret[p][0]=us.getLoginTime();
            ret[p][1]=us.getLogoutTime();
            ret[p][2]=us.getLoginIp();
            p++;
        }
            
        }
        return ret;
    }
    
    public static Object[][] createTransactionsModelData(List<Transactions> list) {
        Object[][] ret = null;
        if (list != null && !list.isEmpty()) {
            ret = new Object[list.size()][transactionsModelColumns.length];
            //implement the logic
            int p = 0;
            for (Transactions us : list) {
                ret[p][0] = us.getEquities().getEquitySymbol();
                ret[p][1] = us.getTransactionDate();
                ret[p][2] = us.getQuantity();
                ret[p][3] = us.getPrice();
                ret[p][4] = us.getOrdersByBuyerOrderId().getOrderTypes().getOrderType();
                p++;
            }
        }
        return ret;
    }
    
}
