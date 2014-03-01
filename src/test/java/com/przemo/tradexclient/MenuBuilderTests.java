/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient;

import com.google.common.io.Resources;
import com.przemo.tradexclient.gui.MenuBuilder;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenuBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

/**
 *
 * @author Przemo
 */
public class MenuBuilderTests extends TestCase {
    
    MenuBuilder mb = null;
    JMenuBar b = null;
    final String menuRepo = "C:\\Users\\Przemo\\Documents\\NetBeansProjects\\TradexClient\\target\\classes\\Menu.xml";
    public MenuBuilderTests(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        b = new JMenuBar();
        mb=new MenuBuilder(b, menuRepo);
        mb.build();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        b=null;
        mb=null;
    }
    
    public void testSAXParser() throws ParserConfigurationException, SAXException, IOException{
      SAXParser reader = SAXParserFactory.newInstance().newSAXParser();
      assertNotNull(new File(menuRepo));
      assertNotNull(reader);     
      reader.parse(menuRepo, mb.new DefaultMenuHandler());
    }
    
    public void testMenuBuild(){
        JMenuBar bar = mb.getMenuProduced();
        assertNotNull(bar);
        assertEquals(mb.getHandler().getMainElementsCount(), bar.getMenuCount());
        int els=0;
        for(int i=0; i<mb.getHandler().getMainElementsCount(); i++){
            els+=bar.getMenu(i).getItemCount(); 
            for(int j=0; j<bar.getMenu(i).getItemCount(); j++){
             System.out.println(bar.getMenu(i).getItem(j).getText());   
            }          
        }      
        assertEquals(mb.getHandler().getItemElementCount(), els);
    }
}
