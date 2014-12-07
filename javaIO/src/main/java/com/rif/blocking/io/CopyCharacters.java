package com.rif.blocking.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Sergey.Savchuk@db.com
 */
public class CopyCharacters {
    public static void main(String[] args) throws IOException, URISyntaxException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader(new File(CopyCharacters.class.getClassLoader().getResource("xanadu.txt").toURI()));
            outputStream = new FileWriter("characteroutput.txt");

            int c; //uses last 16-bit
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
