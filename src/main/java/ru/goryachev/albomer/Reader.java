package ru.goryachev.albomer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Reader {

    public HashMap readAlb (String fileName) throws IOException, ClassNotFoundException {

        try(FileInputStream fileIn = new FileInputStream(fileName/*"SomeName.albomer"*/))
        {
            try(ObjectInputStream objIn = new ObjectInputStream(fileIn)){
                HashMap reMap = (HashMap) objIn.readObject();
                return reMap;
            }
        }
    }
}
