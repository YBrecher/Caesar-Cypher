package edu.touro.mco364;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    private Decoder decoder = new Decoder();
    private CaesarCypher cc = new CaesarCypher();
    private File wordList = new File("C:\\Users\\Yaakov\\Downloads\\sgb-words.txt");
    private File input = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\source.txt");
    private File output = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\output.txt");
    private File tempFile = new File ("C:\\Users\\Yaakov\\Documents\\temp.txt");

    @Test
    void generateWordList() throws FileNotFoundException {

        ArrayList<String> validWords = decoder.generateWordList(wordList);
        assertEquals("which", validWords.get(0));
        assertEquals("there", validWords.get(1));
        assertEquals("their", validWords.get(2));

    }

    @Test
    void getCorrectShift() throws FileNotFoundException {
        ArrayList<String> validWords = decoder.generateWordList(wordList);

        cc.encrypt(input,output,10);
        int actual = decoder.getCorrectShift(output, validWords);
        assertEquals(16, actual);
        cc.encrypt(input,output,20);
        actual = decoder.getCorrectShift(output, validWords);
        assertEquals(6, actual);
    }

    @Test
    void decode() throws FileNotFoundException {

        cc.encrypt(input, output,6);
        decoder.decode(output, tempFile, wordList);

        Scanner scnr = new Scanner(input);
        String inputLine = scnr.nextLine();

        scnr = new Scanner((tempFile));
        String tempLine = scnr.nextLine();

        assertEquals(inputLine,tempLine);



    }
}