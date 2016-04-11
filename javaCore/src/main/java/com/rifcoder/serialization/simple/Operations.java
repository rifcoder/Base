package com.rifcoder.serialization.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * User: rifcoder
 * Date: 11/04/16
 */
public class Operations {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataObject dataObject = new DataObject();
        dataObject.setMyData("hahahaha ");
        File file = new File("store.bin");

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dataObject);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        DataObject restoredObject = (DataObject) ois.readObject();
        ois.close();
        System.out.println("Restored object = [" + restoredObject + "]");
    }
}
