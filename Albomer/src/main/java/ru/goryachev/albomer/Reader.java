package ru.goryachev.albomer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Reader {
	
	
	public void readAlb () throws IOException, ClassNotFoundException {
	
		FileInputStream fileIn = new FileInputStream("SomeName.albomer");
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		HashMap reMap = (HashMap) objIn.readObject();
		objIn.close();
		
		Editor edit = new Editor(reMap.get(1).toString(), reMap.get(2).toString(), reMap.get(3).toString());
		
	}

}
