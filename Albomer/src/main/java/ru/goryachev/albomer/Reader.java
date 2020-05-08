package ru.goryachev.albomer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Reader {
	
	
	public HashMap readAlb () throws IOException, ClassNotFoundException {
	
		FileInputStream fileIn = new FileInputStream("SomeName.albomer");
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		HashMap reMap = (HashMap) objIn.readObject();
		objIn.close();
		
		return reMap;
		
	}

}
