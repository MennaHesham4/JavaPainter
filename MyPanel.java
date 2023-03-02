
package com.mycompany.javapainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.Integer.min;
import static java.lang.Math.abs;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MyPanel extends JPanel
{
    int x1;
    int y1;
    int x2;
    int y2;
    
    Color color;
    Stroke stroke;
    
    private Vector<Line> linesVec;
    private Vector<Rectangle> rectVec;
    private Vector<Oval> ovalVec;
    private Vector<Integer> undoVec;

    boolean isColorClicked;
    boolean isLineClicked;
    boolean isRectClicked;
    boolean isOvalClicked;
    boolean isFreeHandClicked;
    boolean isEraserClicked;
    boolean isFilledChecked;
    
    
    

    
    public MyPanel()
    {
        this.setBackground(Color.WHITE);
        
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        
        
        linesVec = new Vector<Line>();
        
        rectVec = new Vector<Rectangle>();
        
        ovalVec = new Vector<Oval>();
        
        undoVec = new Vector<Integer>();
        
        stroke = new BasicStroke(2f); // default stroke (Solid)
        
        
        
        this.setFocusable(false);   //make the default panel focus is false 
        
        MouseLis mLisObj = new MouseLis();   // Mouse Listener -> press & release
        this.addMouseListener(mLisObj);
        
        MouseMotLis mMLisObj = new MouseMotLis();  // Mouse Listener -> Drag 
        this.addMouseMotionListener(mMLisObj);
        
        
        
        JButton redBtn = new JButton();     //Red button
                            
        RedActionList redObj = new RedActionList();   
        redBtn.addActionListener(redObj);
        redBtn.setSize(30, 30);
        redBtn.setBackground(color.RED);
        redBtn.setLocation(50, 25);
        //redBtn.setFocusPainted(true);
        this.add(redBtn);
        
        JButton greenBtn = new JButton();   // Green button
        GreenActionList greenObj = new GreenActionList();   
        greenBtn.addActionListener(greenObj);
        greenBtn.setSize(30, 30);
        greenBtn.setBackground(color.GREEN);
        greenBtn.setLocation(90, 25);
        this.add(greenBtn);
        
        JButton blueBtn = new JButton();    //Blue button
        BlueActionList blueObj = new BlueActionList();
        blueBtn.addActionListener(blueObj);
        blueBtn.setSize(30, 30);
        blueBtn.setBackground(color.BLUE);
        blueBtn.setLocation(130, 25);
        this.add(blueBtn);
        
        
        JButton lineBtn = new JButton("Line");  // Draw line button
        LineActionList lineObj = new LineActionList();   
        lineBtn.addActionListener(lineObj);
        lineBtn.setSize(120, 30);
        lineBtn.setLocation(180, 25);
        this.add(lineBtn);
        
        JButton rectBtn = new JButton("Rectangle");   //Draw rectangle button
        RectActionList rectObj = new RectActionList();   
        rectBtn.addActionListener(rectObj);
        rectBtn.setSize(120, 30);
        rectBtn.setLocation(310, 25);
        this.add(rectBtn);
        
        JButton ovaltBtn = new JButton("Oval"); //Draw oval button
        OvalActionList ovalObj = new OvalActionList();  
        ovaltBtn.addActionListener(ovalObj);
        ovaltBtn.setSize(120, 30);
        ovaltBtn.setLocation(440, 25);
        this.add(ovaltBtn);
        
        JCheckBox dottedCB = new JCheckBox("Dotted");    //Dotted Checkbox
        DottedItemList dottedObj = new DottedItemList();
        dottedCB.addItemListener(dottedObj);
        dottedCB.setSize(70, 30);
        dottedCB.setLocation(580, 25);
        this.add(dottedCB);
        
        JCheckBox filledCB = new JCheckBox("Filled");    //Filled Checkbox
        FilledItemList filledObj = new FilledItemList();
        filledCB.addItemListener(filledObj);
        filledCB.setSize(70, 30);
        filledCB.setLocation(660, 25);
        this.add(filledCB);
        
        
        
        JButton freeBtn = new JButton("Free Hand");  // Free hand button
        FreeHandActionList fbreeObj = new FreeHandActionList();  
        freeBtn.addActionListener(fbreeObj);
        freeBtn.setSize(120, 30);
        freeBtn.setLocation(250, 70);
        this.add(freeBtn);
        
        
        JButton eraserBtn = new JButton("Eraser");    //Eraser button
        EraserActionList eraserObj = new EraserActionList();
        eraserBtn.addActionListener(eraserObj);
        eraserBtn.setSize(120, 30);
        eraserBtn.setLocation(380, 70);
        this.add(eraserBtn);
        
        JButton clearBtn = new JButton(" Clear All ");  // Clear All button
        ClearActionList clearObj = new ClearActionList();  
        clearBtn.addActionListener(clearObj);
        clearBtn.setSize(120, 30);
        clearBtn.setLocation(510, 70);
        this.add(clearBtn);
        
        
        JButton undoBtn = new JButton(" Undo ");  // Undo button
        UndoActionList undoObj = new UndoActionList();  
        undoBtn.addActionListener(undoObj);
        undoBtn.setSize(120, 30);
        undoBtn.setLocation(640, 70);
        this.add(undoBtn);
  
         //System.out.println(isFocusable());  // testing the panel foucus
    }
    

    
    
    public class MouseLis implements MouseListener   // Inner class to implement MouseListener  (press & release)
    {

        @Override
        public void mouseClicked(MouseEvent e) 
        {
            //System.out.println(isFocusable());  // testing the panel foucus
        }

        @Override
        public void mousePressed(MouseEvent e) 
        {
            x1 = e.getX();
            y1 = e.getY();
       
        }

        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if ( MyPanel.this.isFocusable() && isLineClicked)
            {
                x2 = e.getX();
                y2 = e.getY();
                linesVec.add(new Line(x1,y1,x2,y2,color,stroke));  // After mouse realsing -> store the Xs & Ys in a new Line object and then add the obj to the Vector
                undoVec.add(1); // add 1 in the undo vector as the last item 
            }
            
            else if(MyPanel.this.isFocusable() && isRectClicked)
            {
                rectVec.add(new Rectangle(min(x1, x2),min(y1, y2),abs(x1 - x2),abs(y1 - y2),color,stroke,isFilledChecked));  // After mouse realsing -> store the the drwan Xs & Ys in a new Rec object and then add the obj to the Vector //See drawRect comment
                undoVec.add(2);
            }
            
            else if(MyPanel.this.isFocusable() && isOvalClicked)
            {
                ovalVec.add(new Oval(min(x1, x2),min(y1, y2),abs(x1 - x2),abs(y1 - y2),color,stroke,isFilledChecked));   // After mouse realsing -> store the the drwan Xs & Ys in a new Oval object and then add the obj to the Vector //See drawOval comment
                undoVec.add(3);
            }
            
        }

        @Override
        public void mouseEntered(MouseEvent e) 
        { 
        }
        @Override
        public void mouseExited(MouseEvent e) 
        {
        }
       
    }
    
    
    public class MouseMotLis implements MouseMotionListener   // Inner class to implement MouseMotionListener  (drag)
    {

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            if ( MyPanel.this.isFocusable()  &&  ( isLineClicked || isRectClicked || isOvalClicked) )
            {    
                x2 = e.getX();
                y2 = e.getY();
                                         
                updateUI();
            }

            else if(MyPanel.this.isFocusable() && isFreeHandClicked)
            {
                Graphics g = getGraphics();  
                g.setColor(color);
                g.drawLine(x1, y1, e.getX(), e.getY());
                x1 = e.getX();
                y1 = e.getY();
            }
            
            else if ( MyPanel.this.isFocusable()  &&  isEraserClicked )  //Eraser dragging
            {    
                Graphics g = getGraphics(); 
                g.setColor(Color.WHITE);
                g.fillRect(e.getX(), e.getY(), 20, 15);
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) 
        {
        }
        
    }
    
                                        //*************** SHAPES BUTTONS  *****************\\
    
    public class LineActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(isColorClicked == true)
            {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw a line; 
                isLineClicked = true;
                isRectClicked = false;
                isOvalClicked = false;
                isFreeHandClicked= false;
                      isEraserClicked = false;
            }
            else if (isColorClicked == false)
            {
               JOptionPane.showMessageDialog (null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
    
    
    public class RectActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(isColorClicked == true)
            {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw a rect; 
                isRectClicked = true;
                isLineClicked = false;
                isOvalClicked = false;
                isFreeHandClicked= false;
                        isEraserClicked = false;
            }
            else if (isColorClicked == false)
            {
               JOptionPane.showMessageDialog (null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
    
    public class OvalActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            if(isColorClicked == true)
            {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click draw an oval; 
                isOvalClicked = true;
                isLineClicked = false;
                isRectClicked = false;
                isFreeHandClicked= false;
                        isEraserClicked = false;
            }
            else if (isColorClicked == false)
            {
               JOptionPane.showMessageDialog (null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
                                        //*************** Other BUTTONS  *****************\\
    
    public class FreeHandActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            if(isColorClicked == true && isFreeHandClicked == false)
            {
                MyPanel.this.setFocusable(true);  //setPanelFocus true after user choose color and click free hand; 
                isFreeHandClicked = true;
                isLineClicked = false;
                isRectClicked = false;
                isOvalClicked = false;
                
                boolean linbool = linesVec.isEmpty();   boolean recbool = rectVec.isEmpty();  boolean ovalbool = ovalVec.isEmpty();
                
                linesVec.clear();   //clear all vectors (remove shapes)
                rectVec.clear();
                ovalVec.clear();
                                    //update UI = (clear screen) if the eraser is not clicked OR if there is any shape stored in any vector  ===  Don't update UI in case of we clicked eraser OR vectors are already empty 
                if(isEraserClicked == false || !linbool || !recbool || !ovalbool )  //Since eraser is made mainly for free hand, if user erase some free drawing and click back to press free hand agin to continue, he will be able to continue free drawing
                MyPanel.this.updateUI();
                
            }
            else if (isColorClicked == false)
            {
               JOptionPane.showMessageDialog (null, "Please choose the color first", "Choose color", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
    
    public class EraserActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
                isEraserClicked = true;
                isRectClicked = false;
                isLineClicked = false;
                isOvalClicked = false;
                isFreeHandClicked= false;
            
        }
        
    }
    
    public class ClearActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            linesVec.clear();   //clear all vectors (remove shapes)
            rectVec.clear();
            ovalVec.clear();
            x1 = 0; x2 = 0; y1 = 0; y2 = 0;  // make values of Xs and Ys = 0, so that the last Xs & Ys stored values can't be drawn agin
            MyPanel.this.updateUI();
        }
        
    }
    
    public class UndoActionList implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (isFreeHandClicked) // if we clicked undo in free hand mode it will show an error message to the user
            {
                JOptionPane.showMessageDialog (null, "sorry, undo is not working in free hand mode", "undo in free hand", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                if ( undoVec.size() >= 1)  // if still any drawn object (shapes vectors have object) 
                {   
                    switch(undoVec.get( undoVec.size() - 1 ) )  //check the last number stored in undoVec
                    {
                        case 1:    // if the prevoius drawn shape is Line 
                            x1 = 0; y1 = 0; x2 = 0; y2 = 0;  // 0 current Xs and Ys
                            linesVec.remove( linesVec.size() - 1 );   // remove last stored line in lines Vec
                            undoVec.remove( undoVec.size() - 1 );     // remove last number in undo vec 
                            updateUI();   // update the ui after removing the prevoius drawn line
                            break;

                        case 2:   // if the prevoius drawn shape is Rec
                            x1 = 0; y1 = 0; x2 = 0; y2 = 0;
                            rectVec.remove( rectVec.size() - 1 );
                            undoVec.remove( undoVec.size() - 1 );
                            updateUI();
                            break;

                        case 3:    // if the prevoius drawn shape is Oval
                            x1 = 0; y1 = 0; x2 = 0; y2 = 0;
                            ovalVec.remove( ovalVec.size() - 1);
                            undoVec.remove( undoVec.size() - 1 );
                            updateUI();
                            break;
                    }

                }
                else if (undoVec.size() < 1)  // else if there is no object drawn in the screen = (shapes vectors are empty)
                {
                    JOptionPane.showMessageDialog (null, "Screen is already cleared", "Cleared screen", JOptionPane.ERROR_MESSAGE);
                }
            
            }
        }
        
    }
    
                                        //*************** Checklists  *****************\\
    
    public class DottedItemList implements ItemListener
    {
        
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
           if (e.getStateChange() == ItemEvent.SELECTED)  // if checkbox is checked
           {             
               float[] dashingPattern1 = {2f, 2f};
               stroke = new BasicStroke(2f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
               
               System.out.println("Dotted is Checked");
           }
           else   // else if the checkbox in unchecked
           {
               stroke = new BasicStroke(2f);
               
               System.out.println("Dotted Un Checked");
           }
           
        }
        
    }
    
    
    public class FilledItemList implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
           {
               isFilledChecked = true;
               
               System.out.println("Filled is Checked");
           }
           else
           {
               isFilledChecked = false;
               
               System.out.println("Filled is Un Checked");
           }
            
        }
        
    }
    
                                                    //*************** COLORS BUTTONS  *****************\\
    
    public class RedActionList implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            color = Color.RED;
            isColorClicked = true;
        }
    }
    
    public class GreenActionList implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            color = Color.GREEN;
            isColorClicked = true;
        }
    }
    
    public class BlueActionList implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            color = Color.BLUE;
            isColorClicked = true;
        }
    }
    
    
                            //*************** Paint Method  *****************\\
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); 
        
        Graphics2D g2d = (Graphics2D) g;
        
        if (this.isFocusable() == true && isLineClicked == true )  //painting lines
        {
            g2d.setColor(color);
            g2d.setStroke(stroke);
            
            g2d.drawLine(x1, y1, x2, y2);   // Draw current the last  current line 
        
            for(int i = 0 ; i < linesVec.size() ; i++)   // Draw all prevoius lines stored in the Vector
            {
                g2d.setColor(linesVec.get(i).getColor());
                g2d.setStroke(linesVec.get(i).getStroke());
                g2d.drawLine(linesVec.get(i).getxStart(), linesVec.get(i).getyStart(), linesVec.get(i).getxEnd(), linesVec.get(i).getyEnd());
            }
            
            for(int i = 0 ; i < rectVec.size() ; i++)   // and draw all prevoius rectangles stored in the Vector if any
            {
                g2d.setColor(rectVec.get(i).getColor());
                g2d.setStroke(rectVec.get(i).getStroke());
                g2d.drawRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
                
                if(rectVec.get(i).isIsFilled())
                    g2d.fillRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
            }
            
            for(int i = 0 ; i < ovalVec.size() ; i++)   // and draw all prevoius ovals stored in the Vector if any
            {
                g2d.setColor(ovalVec.get(i).getColor());
                g2d.setStroke(ovalVec.get(i).getStroke());
                g2d.drawOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
                
                if(ovalVec.get(i).isIsFilled())
                    g2d.fillOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
            }
            
        }
        
        
        
        if (this.isFocusable() == true && isRectClicked == true )   //painting Rectangles
        {
            g2d.setColor(color);
            g2d.setStroke(stroke);
            
            g2d.drawRect(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2) );  // the border //-> this way of drawing rect is to be able to draw the rec in all directions
            
            if(isFilledChecked) // if the current rect is checked to be filled, then fill it while drawing
            {
                g2d.fillRect(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2) );  //-> this way of drawing rect is to be able to draw the rec in all directions
            }
            
            
            for(int i = 0 ; i < rectVec.size() ; i++)   
            {
                g2d.setColor(rectVec.get(i).getColor());
                g2d.setStroke(rectVec.get(i).getStroke());
                g2d.drawRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
                
                if(rectVec.get(i).isIsFilled())   // if the rect was filled, then fill it while drawing it
                    g2d.fillRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
            }
            
            for(int i = 0 ; i < linesVec.size() ; i++)   
            {
                g2d.setColor(linesVec.get(i).getColor());
                g2d.setStroke(linesVec.get(i).getStroke());
                g2d.drawLine(linesVec.get(i).getxStart(), linesVec.get(i).getyStart(), linesVec.get(i).getxEnd(), linesVec.get(i).getyEnd());
            }
            
            for(int i = 0 ; i < ovalVec.size() ; i++)    
            {
                g2d.setColor(ovalVec.get(i).getColor());
                g2d.setStroke(ovalVec.get(i).getStroke()); 
                g2d.drawOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
                
                if(ovalVec.get(i).isIsFilled())   // if the oval was filled, then fill it while drawing
                    g2d.fillOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
            }
        
        }
        
        
        
        if (this.isFocusable() == true && isOvalClicked == true )   //painting Ovals
        {
            g2d.setColor(color);
            g2d.setStroke(stroke);
            
            g2d.drawOval(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2) );
            
            if(isFilledChecked)   // if the current oval is checked to be filled, then fill it while drawing
            {
                g2d.fillOval(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2) );
            }
            

            for(int i = 0 ; i < ovalVec.size() ; i++)   
            {
                g2d.setStroke(ovalVec.get(i).getStroke());
                g2d.setColor(ovalVec.get(i).getColor());
                g2d.drawOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
                
                if(ovalVec.get(i).isIsFilled())
                    g2d.fillOval(ovalVec.get(i).getX(), ovalVec.get(i).getY(), ovalVec.get(i).getWidth(), ovalVec.get(i).getHight());
            }
            
            for(int i = 0 ; i < linesVec.size() ; i++)   
            {
                g2d.setColor(linesVec.get(i).getColor());
                g2d.setStroke(linesVec.get(i).getStroke());
                g2d.drawLine(linesVec.get(i).getxStart(), linesVec.get(i).getyStart(), linesVec.get(i).getxEnd(), linesVec.get(i).getyEnd());
            }
            
            for(int i = 0 ; i < rectVec.size() ; i++)   
            {
                g2d.setColor(rectVec.get(i).getColor());
                g2d.setStroke(rectVec.get(i).getStroke());
                g2d.drawRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
                
                if(rectVec.get(i).isIsFilled())
                    g2d.fillRect(rectVec.get(i).getX(), rectVec.get(i).getY(), rectVec.get(i).getWidth(), rectVec.get(i).getHight());
            }
        
        }
        
    }

}
