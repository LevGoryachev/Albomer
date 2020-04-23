package ru.goryachev.albomer;


import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Editor extends JFrame {

		private JTextField nameField;
		private JTextField descField;
		private JTextField numberField;
		private JTextArea fileArea;
		
		Desktop desk = Desktop.getDesktop();
		
		
		private String newName;
		private String newExt;
		private int fromNo;
		private File path;
		private JButton renamerButton;
	
	public Editor() {
	
			Container Pane = this.getContentPane();
			SpringLayout layout = new SpringLayout();
	        Pane.setLayout(layout);
	        
			Component nameLabel = new JLabel("Название альбома");
			Pane.add(nameLabel);
			this.nameField = new JTextField(25);
			Pane.add(nameField);
					
			Component descLabel = new JLabel("Описание");
			Pane.add(descLabel);
			this.descField = new JTextField(50);
			Pane.add(descField);

			Component fileLabel = new JLabel("Перетащите файлы сюда");
			Pane.add(fileLabel);
			this.fileArea = new JTextArea(20,60);
			Pane.add(fileArea);
			
			JScrollPane jspArea = new JScrollPane(fileArea);
			Pane.add(jspArea);
						
					
			fileArea.setDropTarget(new DropTarget() {
			    public synchronized void drop(DropTargetDropEvent evt) {
			        try {
			            evt.acceptDrop(DnDConstants.ACTION_COPY);
			            
			            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			            
			            for (File file : droppedFiles) {

			            	// process files
			            	
			            	fileArea.append(file.toString() + "\n");
			            				            	
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			});
			
			
			
			fileArea.addMouseListener(new MouseAdapter() {
				
                public void mouseClicked(MouseEvent me) {
                	
                	//double click
                    if(me.getClickCount()==2) {
                    
                        int x = me.getX();
                        int y = me.getY();

                        int startOffset = fileArea.viewToModel(new Point(x, y));//where on jtextarea click was made
                        String text = fileArea.getText();
                        int searchAdress = 0;
                        int wordEndIndex = 0;
                        String[] words = text.split("\\s");//spliting the text to words. link will be a single word

                        for(String word:words) {
                        	
                        	//find the resource link
                            if(word.startsWith("https://") || word.startsWith("D:\\")) {
                            	searchAdress = text.indexOf(word);
                                wordEndIndex = searchAdress+word.length();
                                
                                if(startOffset>=searchAdress && startOffset<=wordEndIndex) {
                                    try {
                                    	
                                    	fileArea.select(searchAdress, wordEndIndex);
                                    	                                    	
                                    	desk.getDesktop().open(new File(word));
                                    	
                                    	                                    	
                                    	//Runtime r = Runtime.getRuntime();
                                    	//r.exec(word);
                                        //desk.browse(new URI(word)); //opening the link in browser. Desktop desk = Desktop.getDesktop();
                                    }
                                    catch(Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }                           
                    }

                }
            });
			
			
			this.renamerButton = new JButton("Сохранить");
			Pane.add(renamerButton);
			//renamerButton.addActionListener(btnClick);
			
			layout.putConstraint(SpringLayout.WEST , nameLabel, 10, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, nameLabel, 25, SpringLayout.NORTH, Pane);
			layout.putConstraint(SpringLayout.NORTH, nameField, 25, SpringLayout.NORTH, Pane);
			layout.putConstraint(SpringLayout.WEST , nameField, 20, SpringLayout.EAST , nameLabel);
						
			layout.putConstraint(SpringLayout.WEST , descLabel, 10, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, descLabel, 35, SpringLayout.NORTH, nameLabel);
			layout.putConstraint(SpringLayout.NORTH, descField, 35, SpringLayout.NORTH, nameLabel);
			layout.putConstraint(SpringLayout.WEST , descField, 20, SpringLayout.EAST , descLabel);

			layout.putConstraint(SpringLayout.WEST , fileLabel, 330, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, fileLabel, 35, SpringLayout.NORTH, descLabel);
			layout.putConstraint(SpringLayout.NORTH, jspArea, 35, SpringLayout.NORTH, fileLabel);
			layout.putConstraint(SpringLayout.WEST , jspArea, 50, SpringLayout.WEST , Pane);
			
			layout.putConstraint(SpringLayout.WEST , renamerButton, 330, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, renamerButton, 30, SpringLayout.SOUTH, jspArea);	
		
			
			
			setLocation(250, 300);
			setSize(850, 600);
			setTitle("ALBOMER by Lev Goryachev (version 1.0.1)");
			setVisible(true);
			setResizable(false);
			
			System.out.println("Editor");	
		}

	
	
}
