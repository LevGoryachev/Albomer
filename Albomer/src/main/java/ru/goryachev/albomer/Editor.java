package ru.goryachev.albomer;

import java.awt.Component;
import java.awt.Container;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Editor extends JFrame implements HyperlinkListener {

		private JTextField nameField;
		private JTextField descField;
		private JTextField numberField;
		private JTextArea fileArea;
		
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

			
			
		//HyperLink
			
			JTextPane hyperPane = new JTextPane();
			hyperPane.setEditable(false);
			hyperPane.setContentType("text/html");
			hyperPane.setText("Ispytanie hyperssylky:<a href='mailto:michael@uml.com'>e-mail to</a> or do smth else");
			hyperPane.addHyperlinkListener(new HyperlinkListener() {
		        @Override
		        public void hyperlinkUpdate(HyperlinkEvent e) {
		            if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		                System.out.println("HyperLink: " + e.getURL());
		                // write your logic here to process mailTo link.
		            }
		        }
		    });
			
			Pane.add(hyperPane);
			
			
			
			
			Component fileLabel = new JLabel("Перетащите файлы сюда");
			Pane.add(fileLabel);
			this.fileArea = new JTextArea(20,60);
			Pane.add(fileArea);
			
			
			
			fileArea.setDropTarget(new DropTarget() {
			    public synchronized void drop(DropTargetDropEvent evt) {
			        try {
			            evt.acceptDrop(DnDConstants.ACTION_COPY);
			            List<File> droppedFiles = (List<File>)
			                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			            for (File file : droppedFiles) {
			                // process files
			            	
			            	fileArea.append(file.toString() + "\n" + "Ispytanie hyperssylky:<a href='mailto:michael@uml.com'>e-mail to</a> or it s OK");
			            	
			            	
			            	//System.out.println("Dropped: " + file);	
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
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
			layout.putConstraint(SpringLayout.NORTH, fileArea, 35, SpringLayout.NORTH, fileLabel);
			layout.putConstraint(SpringLayout.WEST , fileArea, 50, SpringLayout.WEST , Pane);
			
			layout.putConstraint(SpringLayout.WEST , renamerButton, 330, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, renamerButton, 30, SpringLayout.SOUTH, fileArea);	
			
			layout.putConstraint(SpringLayout.WEST , hyperPane, 300, SpringLayout.WEST , Pane);
			layout.putConstraint(SpringLayout.NORTH, hyperPane, 10, SpringLayout.SOUTH, renamerButton);	
			
			
			
			
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
