package edu.touro.mco364;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\source.txt");
        File output = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\output.txt");
        File wordList = new File("C:\\Users\\Yaakov\\Downloads\\sgb-words.txt");

        CaesarCypher test = new CaesarCypher();
        test.encrypt(input,output,18);

//        Decoder test = new Decoder();
//        test.decode(input,output, wordList);








        }
    }

