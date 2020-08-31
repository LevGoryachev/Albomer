package ru.goryachev.albomer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    private JEditorPane editorField;
    private String name;
    private String desc;
    private String txtInField;

    private JButton saveButton;

    final char dm = 34;

    public Editor (String savedName, String savedDesc, String savedField) {

        Container Pane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        Pane.setLayout(layout);

        Component nameLabel = new JLabel("Название альбома");
        Pane.add(nameLabel);
        this.nameField = new JTextField(25);
        this.name = savedName;
        nameField.setText(name);
        Pane.add(nameField);

        Component descLabel = new JLabel("Описание");
        Pane.add(descLabel);
        this.descField = new JTextField(50);
        this.desc = savedDesc;
        descField.setText(desc);
        Pane.add(descField);

        Component fileLabel = new JLabel("Перетащите файлы сюда");
        Pane.add(fileLabel);
        this.editorField = new JEditorPane();
        editorField.setPreferredSize(new Dimension (750, 300));
        editorField.setContentType("text/html");
        this.txtInField = savedField;
        editorField.setText(txtInField);
        Pane.add(editorField);

        JScrollPane jspArea = new JScrollPane(editorField);
        Pane.add(jspArea);

        //Tips
        JTextPane tips = new JTextPane();
        tips.setEditable(false);
        tips.setContentType("text/html");
        tips.setText("<b><font color=\"#556B2F\">В поле пока нельзя вводить текст</font></b>");
        Pane.add(tips);
        //


        editorField.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);

                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                    for (File file : droppedFiles) {

                        txtInField = txtInField + "<p>" + "<strong>" + "<a href=" + "file:/" + dm + file.toString().replaceAll("\u0020", "%20") + dm + ">" + file.toString() + "</a>" + "</strong>" + "<font color='green'>" + " - " + "</font>" +  "</p>";

                        editorField.setText(txtInField);

                        System.out.println(txtInField);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        editorField.setEnabled(true);
        editorField.setEditable(false);

        editorField.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if(Desktop.isDesktopSupported()) {
                        try {
                            //Desktop.getDesktop().browse(e.getURL().toURI()); // for http
                            Desktop.getDesktop().open(new File(e.getURL().toURI()));

                        } catch (IOException | URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        this.saveButton = new JButton("Сохранить");
        Pane.add(saveButton);
        saveButton.addActionListener((actionEvent) -> {name = nameField.getText();
                    desc = descField.getText();

                    Saver saver = new Saver(name, desc, txtInField);
                    try {
                        saver.saveAlb();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                    System.out.println("Save button: info saved in Albomer file");}
        );

        layout.putConstraint(SpringLayout.WEST , nameLabel, 10, SpringLayout.WEST , Pane);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 25, SpringLayout.NORTH, Pane);
        layout.putConstraint(SpringLayout.NORTH, nameField, 25, SpringLayout.NORTH, Pane);
        layout.putConstraint(SpringLayout.WEST , nameField, 20, SpringLayout.EAST , nameLabel);

        layout.putConstraint(SpringLayout.WEST , descLabel, 10, SpringLayout.WEST , Pane);
        layout.putConstraint(SpringLayout.NORTH, descLabel, 35, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, descField, 35, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST , descField, 20, SpringLayout.EAST , descLabel);

        layout.putConstraint(SpringLayout.WEST , fileLabel, 330, SpringLayout.WEST , Pane);
        layout.putConstraint(SpringLayout.NORTH, fileLabel, 45, SpringLayout.NORTH, descLabel);
        layout.putConstraint(SpringLayout.NORTH, jspArea, 20, SpringLayout.NORTH, fileLabel);
        layout.putConstraint(SpringLayout.WEST , jspArea, 50, SpringLayout.WEST , Pane);

        layout.putConstraint(SpringLayout.WEST , tips, 300, SpringLayout.WEST , Pane);
        layout.putConstraint(SpringLayout.NORTH, tips, 10, SpringLayout.SOUTH, jspArea);

        layout.putConstraint(SpringLayout.WEST , saveButton, 375, SpringLayout.WEST , Pane);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 25, SpringLayout.SOUTH, tips);

        setLocation(250, 300);
        setSize(850, 600);
        setTitle("ALBOMER by Lev Goryachev (version 1.0)");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Editor");
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        // TODO Auto-generated method stub
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getDescField() {
        return descField;
    }

    public void setDescField(JTextField descField) {
        this.descField = descField;
    }

    public JEditorPane getEditorField() {
        return editorField;
    }

    public void setEditorField(JEditorPane editorField) {
        this.editorField = editorField;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTxtInField() {
        return txtInField;
    }

    public void setTxtInField(String txtInField) {
        this.txtInField = txtInField;
    }
}
