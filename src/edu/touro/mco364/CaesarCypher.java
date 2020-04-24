package edu.touro.mco364;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class CaesarCypher {

    void encrypt(File sourceFilename, File destinationFilename, int letterShift) throws FileNotFoundException {

        Scanner scnr = new Scanner(sourceFilename);
        PrintWriter pw = new PrintWriter(destinationFilename);

        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            for (int i = 0; i < line.length(); i++)
            {
                char newChar = encode(line.charAt(i), letterShift);
                pw.print(newChar);
            }
            pw.println();
        }
        pw.close(); //TODO switch to input/output streams and buffer for better efficiency
    }

    char encode(char chr, int letterShift){

        letterShift %= 26; //we mod it by 26 in case it's too high

        if (letterShift < 0) { // if letterShift is negative we add 26
            letterShift += 26;
        }

        int ascii = chr;
        if (ascii >= 'A' && ascii <= 'Z' || ascii >= 'a' && ascii <= 'z'){ //only perform shift if its a letter

            if (ascii <= 'Z' && ascii + letterShift > 'Z' || ascii >= 'a' && ascii + letterShift > 'z'){
                ascii += letterShift - 26; //if ascii plus the shift is too high we will subtract by 26
            }else{
                ascii += letterShift; //otherwise we will do a simple shift
            }

        }
        return (char) ascii;
    }
}

