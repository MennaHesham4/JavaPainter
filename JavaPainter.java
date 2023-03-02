

package com.mycompany.javapainter;

import javax.swing.JFrame;


public class JavaPainter extends JFrame
{

    public static void main(String[] args) 
    {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("Painter");
        myFrame.setSize(800, 800);
        myFrame.setLocationRelativeTo(null);
        
        MyPanel myPanel = new MyPanel();
        myPanel.setLayout(null); // removing java gui grid --> to be able to move buttons freely
        
        myFrame.setContentPane(myPanel);
        
        myFrame.setVisible(true);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
          
    }
    
}
