package edu.touro.mco364;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCypherTest {
    private CaesarCypher cypher = new CaesarCypher();

    @org.junit.jupiter.api.Test
    void encode() {
        char actual = cypher.encode('a', 4);
        assertEquals(actual, 'e');
        actual = cypher.encode('a', - 2);
        assertEquals(actual, 'y');
        actual = cypher.encode('C', 30);
        assertEquals(actual, 'G');
        actual = cypher.encode('Z', 5);
        assertEquals(actual, 'E');
        actual = cypher.encode('B', -35);
        assertEquals(actual, 'S');
        actual = cypher.encode('?', 15);
        assertEquals(actual, '?');
        actual = cypher.encode(' ', 8);
        assertEquals(actual, ' ');
        actual = cypher.encode('~', -12);
        assertEquals(actual, '~');

    }

    @org.junit.jupiter.api.Test
    void encrypt() throws IOException {

        File input = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\source.txt");
        File output = new File("C:\\Users\\Yaakov\\IdeaProjects\\Caesar Cypher\\output.txt");

        FileWriter writer = new FileWriter(input);
        writer.write("Hello World!");
        writer.close();

        cypher.encrypt(input,output,2);

        Scanner scnr = new Scanner(output);
        String line = scnr.nextLine();
        System.out.println(line);
        assertEquals("Jgnnq Yqtnf!", line);

    }
}