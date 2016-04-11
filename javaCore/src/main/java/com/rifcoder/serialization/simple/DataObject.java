package com.rifcoder.serialization.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * User: rifcoder
 * Date: 11/04/16
 */
public class DataObject extends NonSerializable implements Serializable {
    private int i = 5;
    private String s = "aaaaa";
    transient String[] def = new String[7];
    private CustomObject customObject;

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(getMyData());
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        setMyData((String) stream.readObject());
    }

    @Override
    public String toString() {
        return super.toString() +
                "DataObject{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", def=" + Arrays.toString(def) +
                ", customObject=" + customObject +
                "}\n";
    }
}
