package ru.goryachev.albomer;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

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

        //Changing anonymous class to lambda expression sample
        createButton.addActionListener(/*new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtInField = "";
                Editor editor = new Editor(name, desc, txtInField);
                dispose();
                System.out.println("Create button: disposed the empty Editor");
            }
        }*/
        (actionEvent) -> {
            txtInField = "";
            Editor editor = new Editor(name, desc, txtInField);
            dispose();
            System.out.println("Create button: open the empty Editor");
        });

        this.openButton = new JButton("Открыть альбом");
        Pane.add(openButton);
        openButton.addActionListener((actionEvent) -> {Reader reader = new Reader();
            JFileChooser fileopen = new JFileChooser();

            fileopen.setFileFilter(new FileFilter() {
                public String getDescription() {
                    return "Albomer files (*.albomer)";
                }
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".albomer");
                    }
                }
            });

            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {

                File file = fileopen.getSelectedFile();

                if (fileopen.getName(file).endsWith(".albomer") && !fileopen.getName(file).isEmpty()) {

                    System.out.println("Opening file: " + fileopen.getName(file));

                    try {
                        reMap = reader.readAlb(fileopen.getName(file));
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        System.out.println("Albomer's file (with extension .albomer) not found");
                    }
                    name = (String) reMap.get(1).toString();
                    desc = (String) reMap.get(2).toString();
                    txtInField = (String) reMap.get(3).toString();

                    Editor editor = new Editor(name, desc, txtInField);
                    dispose();

                }
                System.out.println("The wrong file type!");
            }
            System.out.println("Open button: open the Editor filled from file");}
        );

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
}
