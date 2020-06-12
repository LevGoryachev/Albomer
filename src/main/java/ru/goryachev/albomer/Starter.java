package ru.goryachev.albomer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class Starter extends JFrame {

    private JButton createButton;
    private JButton openButton;
    private HashMap<Integer, String> reMap;
    private String name;
    private String desc;
    private String txtInField;

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

    public class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {


            if(e.getSource() == createButton) {

                txtInField = "";

                Editor editor = new Editor(name, desc, txtInField);
                dispose();

                System.out.println("Create");
            }

            if (e.getSource() == openButton) {

                Reader reader = new Reader();

                try {
                    reMap = reader.readAlb();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                name = (String) reMap.get(1).toString();
                desc = (String) reMap.get(2).toString();
                txtInField = (String) reMap.get(3).toString();

                Editor editor = new Editor(name, desc, txtInField);
                dispose();

                System.out.println("Open");
            }


        }

    }

}
