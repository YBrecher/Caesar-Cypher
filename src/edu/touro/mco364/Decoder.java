package edu.touro.mco364;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Decoder {

    private CaesarCypher cypher = new CaesarCypher(); //we need to instantiate CaesarCypher to use its encode method

    void decode(File sourceFile, File resultFile, File wordList) throws FileNotFoundException {

        ArrayList<String> validWords = generateWordList(wordList); // first we generate our word list

        int correctShift = getCorrectShift(sourceFile,validWords); // using our word list we will find the correct number to shift

        cypher.encrypt(sourceFile, resultFile, correctShift); //call CaesarCypher encrypt method with correctShift to get decoded file

    }

    ArrayList<String> generateWordList(File wordList) throws FileNotFoundException {  //TODO change array list to a hash set for better efficiency

        ArrayList<String> validWords = new ArrayList<>();
        Scanner scnr = new Scanner(wordList);

        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            validWords.add(line);
        }
        return validWords;

    }

     int getCorrectShift(File sourceFile, ArrayList validWords) throws FileNotFoundException {

        ArrayList<String> wordsInLine = new ArrayList<>();
        int correctShift = 0;
        int highestCount = 0;

        for (int i = 1; i <= 25; i++) //we will check through all 25 possible shifts
        {
            int validWordCounter = 0;

            StringBuilder str = new StringBuilder();

            Scanner scnr = new Scanner(sourceFile);

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    char newChar = cypher.encode(line.charAt(j), i);
                    if (newChar >= 'A' && newChar <= 'Z' || newChar >= 'a' && newChar <= 'z') {
                        str.append(newChar);
                    }

                    //once we hit a space or the end of line we will add word to array list
                    if (j + 1 < line.length() && line.charAt(j + 1) == ' ' || j + 1 == line.length()) {
                        String realString = str.toString();
                        wordsInLine.add(realString);
                        str.delete(0, str.length()); //reset str for next word
                    }
                }
                for (String s : wordsInLine) {
                    if (validWords.contains(s.toLowerCase())) { //if our word is on the list we increment the counter by 1
                        validWordCounter++;
                    }
                }
                wordsInLine.clear(); //clear array list for next line

            }
            if (validWordCounter > highestCount) { //check which shift has the most valid words
                highestCount = validWordCounter;
                correctShift = i;
            }
        }
        return correctShift;
    }

}
