package com.rifcoder.serialization.simple;

/**
 * User: rifcoder
 * Date: 11/04/16
 */
public class NonSerializable {
    private String myData = "";

    public String getMyData() {
        return myData;
    }

    public void setMyData(String myData) {
        this.myData = myData;
    }

    @Override
    public String toString() {
        return "NonSerializable{" +
                "myData='" + myData + '\'' +
                "}\n";
    }
}
