/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Przemo
 */
public class MenuBuilder implements IMenuBuildListener{

    JMenuBar menuProduced=null;
    DefaultMenuHandler handler=null;
    private String address=null;
    public DefaultMenuHandler getHandler() {
        return handler;
    }
    JMenuItem currentItem, currentMenu=null;
    
    public JMenuBar getMenuProduced() {
        return menuProduced;
    }
    
    public class DefaultMenuHandler extends DefaultHandler  {
        
        private boolean mainElement=false;
        private boolean childElement = false;
        String menuName=null;
        String actionName = null;
        private int mainElementsCount=0;
        private List<String> actionCommands=null;
        
        public int getMainElementsCount() {
            return mainElementsCount;
        }

        public int getItemElementCount() {
            return itemElementCount;
        }

        public int getActionsCount() {
            if(actionCommands!=null){
                return actionCommands.size();
            } else {
                return 0;
            }
        }
        private int itemElementCount=0;
        
        List<IMenuBuildListener> buildListners;
        
        public DefaultMenuHandler(){
            this.buildListners = new ArrayList<>();
            actionCommands = new ArrayList<>();
        }
        
        public void addBuildListener(IMenuBuildListener l){
            if(l!=null && buildListners!=null){
              buildListners.add(l);  
            }
        }
        
        @Override
        public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException{
            switch (qName.toLowerCase()) {
                case "menu":
                    if(!mainElement){
                      mainElement = true; 
                      childElement=false;
                      menuName = attributes.getValue("name");
                      for(IMenuBuildListener l: buildListners){
                    l.addMenu(menuName);
                    }
                      mainElementsCount++;
                    }
                    break;
                case "item":
                    childElement = true;
                    mainElement=false;
                    menuName=null;
                    if(attributes.getValue("action")!=null){
                     actionName = attributes.getValue("action");  
                     actionCommands.add(actionName);
                    }                 
                    itemElementCount++;
                    break; 
            }
        }
        
        @Override
        public void endElement(String uri, String localName, String qName){
            if(qName.toLowerCase().equals("item") && childElement){
                childElement=false;
            } else if(qName.toLowerCase().equals("menu") && mainElement){
                mainElement=false;
            }
        }
        
        @Override
        public void characters(char ch[], int start, int length) throws SAXException{
            String currValue = new String(ch, start, length);
            if(childElement){
                for(IMenuBuildListener l: buildListners){
                    l.addMenuItem(currValue);
                }
            }
        }
    }
    
    public MenuBuilder(JMenuBar bar, String address) {
        if(bar!=null){
           menuProduced = bar; 
        } else {
            menuProduced = new JMenuBar();
        }      
        this.address = address;
        handler = new DefaultMenuHandler();   
    }
    
    public void build() throws SAXException, ParserConfigurationException, IOException{
        SAXParser reader = SAXParserFactory.newInstance().newSAXParser();
        handler.addBuildListener(this);
        reader.parse(address, handler);
    }
    
    @Override
    public JMenu addMenu(String name) {
        JMenu m = new JMenu(name);
        menuProduced.add(m);
        currentMenu = m;
        return m;
    }

    @Override
    public JMenuItem addMenuItem(String menu) {
        JMenuItem m = new JMenuItem(menu);
        currentMenu.add(m);
        currentItem=m;
        return m;
    }

    @Override
    public void addActionCommand(String actionString) {
        currentItem.setActionCommand(actionString);
    }
}
