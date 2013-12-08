package topuniversities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileIO {   
    
    public static void saveObjects(ArrayList<University> universities, ArrayList<University> entropy){       
        try {  
            FileOutputStream saveFile = new FileOutputStream("SaveObj.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(universities);
            save.writeObject(entropy);
            save.close();
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }
    } 
    
    public static ArrayList<University>[] loadObjects() {
        ArrayList<University> universities = new ArrayList<University>();
        ArrayList<University> entropy = new ArrayList<University>();
        try {
            FileInputStream saveFile = new FileInputStream("SaveObj.sav");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            universities = (ArrayList<University>)save.readObject();
            entropy = (ArrayList<University>)save.readObject();
            save.close();            
        }        
        catch(Exception exc){
            exc.printStackTrace();
        }
        ArrayList[] returnData = new ArrayList[2];
        returnData[0] = universities;
        returnData[1] = entropy;
        
        return returnData;
    }
}
