package readability;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("The text is:\n" + line + "\n");
                String[] sentences = line.split("[!?.]\\s*");
                int s = sentences.length;
                int c = line.replaceAll("\\s", "").length();
                String[] words = line.split("\\s");
                int w = words.length;
                int syllables = countSyllables(words);
                int p_syllables = countPolySyllables(words);
                Scanner scanner2 = new Scanner(System.in);
                printScore(s, c, w, syllables, p_syllables);
                String choice = scanner2.nextLine();
                double ari = ((double) c / w) * 4.71f + ((double) w / s) * 0.5d - 21.43d;
                double fk = 0.39 * (double) w / s + 11.8 * (double) syllables / w - 15.59;
                double smog = 3.1291 + 1.043 * Math.sqrt((double) p_syllables * 30 / s);
                double cl = -15.8 + 0.0588 * c * 100 / w - 0.296 * s * 100 /w;
                switch (choice) {
                    case "ARI":
                        printARI(ari);
                        break;
                    case "FK":
                        printFK(fk);
                        break;
                    case "SMOG":
                        printSMOG(smog);
                        break;
                    case "CL":
                        printCL(cl);
                        break;
                    case "all":
                        printARI(ari);
                        printFK(fk);
                        printSMOG(smog);
                        printCL(cl);
                        break;
                    default:
                        break;
                }

            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("ooops!  " + e);
        }

    }

    public static int countSyllables(String[] words) {
        int result = 0;
        for(String word : words) {
            int score = 0;
            char[] chars = word.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                if (isVowel(chars[i]) && !((chars[i]) == 'e' && i == chars.length - 1)) {
                    score++;
                    while(i < chars.length - 1 && isVowel(chars[i + 1])) {
                        i++;
                    }
                }
            }
            if(score == 0)
                result++;
            else
                result += score;
        }
        return result;
    }

    public static int countPolySyllables(String[] words) {
        int result = 0;
        for(String word : words) {
            int score = 0;
            char[] chars = word.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                if (isVowel(chars[i]) && !((chars[i]) == 'e' && i == chars.length - 1)) {
                    score++;
                    while(i < chars.length - 1 && isVowel(chars[i + 1])) {
                        i++;
                    }
                }
            }
            if(score > 2){
                result++;
            }

        }
        return result;
    }


    public static boolean isVowel(char i) {
        return i == 'a' || i == 'i' || i == 'e' || i == 'o' || i == 'u' || i == 'y';
    }

    public static void printScore(int s, int c, int w, int syllables, int p_syllables) {

        System.out.println("Words: " + w);
        System.out.println("Sentences: " + s);
        System.out.println("Characters: " + c);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + p_syllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
    }

    public static void printARI(double score) {

        System.out.printf("\nAutomated Readability Index: %.2f ", Math.floor(score*100)/100f);
        printAge(score);
    }

    public static void printFK(double score) {

        System.out.printf("\nFlesch–Kincaid readability tests: %.2f ", Math.floor(score*100)/100f);
        printAge(score);
    }

    public static void printSMOG(double score) {

        System.out.printf("\nSimple Measure of Gobbledygook: %.2f ", Math.floor(score*100)/100f);
        printAge(score);
    }

    public static void printCL(double score) {

        System.out.printf("\nColeman–Liau index: %.2f ", Math.floor(score*100)/100f);
        printAge(score);
    }

    public static void printAge(double score) {

        if(score > 12)
            System.out.print("(about 18-year-olds)");
        else if(score > 11)
            System.out.print("(about 17-year-olds)");
        else if(score > 10)
            System.out.print("(about 16-year-olds)");
        else if(score > 9)
            System.out.print("(about 15-year-olds)");
        else if(score > 8)
            System.out.print("(about 14-year-olds)");
        else if(score > 7)
            System.out.print("(about 13-year-olds)");
        else if(score > 6)
            System.out.print("(about 12-year-olds)");
        else if(score > 5)
            System.out.print("(about 11-year-olds)");
        else if(score > 4)
            System.out.print("(about 10-year-olds)");
        else if(score > 3)
            System.out.print("(about 9-year-olds)");
        else if(score > 2)
            System.out.print("(about 7-year-olds)");
        else if(score > 1)
            System.out.print("(about 6-year-olds)");
    }
}
