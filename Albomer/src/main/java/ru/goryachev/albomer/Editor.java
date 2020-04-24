package ru.goryachev.albomer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Editor extends JFrame implements HyperlinkListener {

		private JTextField nameField;
		private JTextField descField;
		private JTextField numberField;
		private JEditorPane editorField;
		private String txtInField;
		private JButton renamerButton;
		
		Desktop desk = Desktop.getDesktop();
		
			
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
			this.editorField = new JEditorPane();
			editorField.setPreferredSize(new Dimension (750, 300));
			Pane.add(editorField);
			
			JScrollPane jspArea = new JScrollPane(editorField);
			Pane.add(jspArea);
			
		//Tips
			JTextPane tips = new JTextPane();
			tips.setEditable(true);
			tips.setText("В поле можно вводить текст, но обязательно отделять пробелами от файлов");
						
			//hyperPane.setContentType("text/html");
						
			//hyperPane.setText("Ispytanie hyperssylky:<a href='mailto:michael@uml.com'>e-mail to</a> or do smth else");
			
			Pane.add(tips);
		//
						
					
			editorField.setDropTarget(new DropTarget() {
			    public synchronized void drop(DropTargetDropEvent evt) {
			        try {
			            evt.acceptDrop(DnDConstants.ACTION_COPY);
			            
			            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			            
			            for (File file : droppedFiles) {

			            	// process files
			            	
			            	//fileArea.append(file.toString() + "\n");
			            	//editorField.setText(file.toString() + "\n");
			            	
			            	editorField.setText(txtInField + "\n" + file.toString());
			            	
			            	txtInField = editorField.getText();
			            	
			            				            	
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			});
						
		
			editorField.addMouseListener(new MouseAdapter() {
				
                public void mouseClicked(MouseEvent me) {
                	
                	//double click
                    if(me.getClickCount()==2) {
                    
                        int x = me.getX();
                        int y = me.getY();

                        int startOffset = editorField.viewToModel(new Point(x, y));//where on jtextarea click was made
                        String text = editorField.getText();
                        int searchAdress = 0;
                        int wordEndIndex = 0;
                        String[] words = text.split("\\s");//spliting the text to words. link will be a single word

                        for(String word:words) {
                        	
                        	//find the resource link
                            if(word.startsWith("https://") || word.contains(":")) {
                            	searchAdress = text.indexOf(word);
                                wordEndIndex = searchAdress+word.length();
                                
                                if(startOffset>=searchAdress && startOffset<=wordEndIndex) {
                                    try {
                                    	
                                    	editorField.select(searchAdress, wordEndIndex);
                                    	                                    	
                                    	desk.getDesktop().open(new File(word));
                                	
                                    	
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
						
			layout.putConstraint(SpringLayout.WEST , tips, 200, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, tips, 10, SpringLayout.SOUTH, jspArea);	
			
			layout.putConstraint(SpringLayout.WEST , renamerButton, 375, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, renamerButton, 25, SpringLayout.SOUTH, tips);	
			
			setLocation(250, 300);
			setSize(850, 600);
			setTitle("ALBOMER by Lev Goryachev (version 1.0.1)");
			setVisible(true);
			setResizable(false);
			
			System.out.println("Editor");	
		}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
