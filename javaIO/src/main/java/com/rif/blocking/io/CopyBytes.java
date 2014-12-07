package com.rif.blocking.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Sergey.Savchuk@db.com
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(new File(CopyBytes.class.getClassLoader().getResource("xanadu.txt").toURI()));
            fos = new FileOutputStream("outagain.txt");
            int c; //uses only last 8-bit

            while ((c = fis.read()) != -1) {
                fos.write(c);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
