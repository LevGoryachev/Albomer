package ru.goryachev.albomer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Saver {

    private String name;
    private String desc;
    private String txtInField;


    public Saver(String name, String desc, String txtInField) {

        this.name = name;
        this.desc = desc;
        this.txtInField = txtInField;

    }

    public void saveAlb () throws IOException {

        HashMap <Integer, String> hMap = new HashMap();
        hMap.put(1, name);
        hMap.put(2, desc);
        hMap.put(3, txtInField);

        FileOutputStream fileOut = new FileOutputStream("SomeName.albomer");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(hMap);
        objOut.close();
    }

}