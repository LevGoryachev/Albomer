package ru.goryachev.albomer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;


public class Starter extends JFrame {

	private JButton createButton;
	private JButton openButton;
	
	
	Starter () {
	
		Container Pane = this.getContentPane();
		SpringLayout layout = new SpringLayout();
        Pane.setLayout(layout);
        
        this.createButton = new JButton("Создать альбом");
		Pane.add(createButton);
		createButton.addActionListener(btnClick);
		
		this.openButton = new JButton("Открыть альбом");
		Pane.add(openButton);
		openButton.addActionListener(btnClick);		
		
		
		layout.putConstraint(SpringLayout.WEST , createButton, 200, SpringLayout.WEST , Pane);
		layout.putConstraint(SpringLayout.NORTH, createButton, 100, SpringLayout.NORTH, Pane);
		
		layout.putConstraint(SpringLayout.WEST , openButton, 500, SpringLayout.WEST , Pane);
		layout.putConstraint(SpringLayout.NORTH, openButton, 100, SpringLayout.NORTH, Pane);	
        
		
	setLocation(250, 400);
	setSize(850, 250);
	setTitle("ALBOMER by Lev Goryachev (version 1.0.1)");
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	ButtonListener btnClick = new ButtonListener();
	
	public class ButtonListener implements ActionListener{
		
		 public void actionPerformed(ActionEvent e) {
			 
		 
			if(e.getSource() == createButton) {

				
				System.out.println("Create");
			}
			
			if (e.getSource() == openButton) {
			

				System.out.println("Open");
			}
			 
		
		}
		
	}
	
}
