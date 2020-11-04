package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int key = 0;
        int i = 0;
        String input = "";
        String action = "enc";
        String resultString = "";
        String alg = "";
        String fileNameIn = "";
        String fileNameOut = "";


        boolean data = false;

        Context unicodeEncrypt = new Context(new UnicodeEncryption());
        Context unicodeDecrypt = new Context(new UnicodeDecrypt());
        Context shiftEncrypt = new Context(new ShiftEncryption());
        Context shiftDecrypt = new Context(new ShiftDecryption());


        for (; i < args.length; i++) {
            if (("-data").equals(args[i])) {
                data = true;
                input = args[i + 1];
            }

            if (("-mode").equals(args[i])) {
                action = args[i + 1];

            }

            if (("-key").equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            }

            if (("-in").equals(args[i]) && !data) {
                fileNameIn = args[i + 1];
            }

            if (("-out").equals(args[i]) && !data) {
                fileNameOut = args[i + 1];
            }

            if (("-alg").equals(args[i])) {
                alg = args[i + 1];
            }

            i++;
        }


        switch (alg) {

            case "shift":
                if (!fileNameIn.isEmpty() && input.isEmpty()) {
                    input = readFrom(fileNameIn);

                }


                switch (action) {
                    case "enc":
                        resultString = shiftEncrypt.executeStrategy(input, key);

                        break;
                    case "dec":
                        resultString = shiftDecrypt.executeStrategy(input, key);
                        break;
                }


                if (!fileNameOut.isEmpty()) {
                    writeTo(fileNameOut, resultString);
                } else {
                    System.out.println(resultString);
                }
                break;

            default:

                if (!fileNameIn.isEmpty() && input.isEmpty()) {
                    input = readFrom(fileNameIn);

                }


                switch (action) {
                    case "enc":
                        resultString = unicodeEncrypt.executeStrategy(input, key);
                        break;
                    case "dec":
                        resultString = unicodeDecrypt.executeStrategy(input, key);
                        break;
                }


                if (!fileNameOut.isEmpty()) {
                    writeTo(fileNameOut, resultString);
                }else {
                    System.out.println(resultString);
                }
                break;

        }


    }


    public static void writeTo(String out, String Text) throws IOException {
        FileWriter writer = new FileWriter(out);
        writer.write(Text);
        writer.close();

    }


    public static String readFrom(String in) throws IOException { // enter file path
        String fileText = "";

        File n = new File(in);
        try (Scanner sc = new Scanner(n)) {
            while (sc.hasNext()) {
                fileText = (sc.nextLine());
                break;
            }
        }
        return fileText;

    }

}

// my Terminal is not working if i do everything on different files, so i had to implement all in here


// here i tried to implement the strategy pattern


interface Strategy {
    StringBuilder sb = new StringBuilder();

    String doOperation(String message, int key);

}

class UnicodeEncryption implements Strategy {

    @Override
    public String doOperation(String message, int key) {
        String encripted = "";
        char[] stringChar = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            stringChar[i] = (char) (message.charAt(i) + key);
            encripted = String.valueOf(sb.append(stringChar[i]));
        }
        return encripted;

    }
}

class UnicodeDecrypt implements Strategy {

    @Override
    public String doOperation(String message, int key) {
        String decripted = "";

        char[] stringChar = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            stringChar[i] = (char) (message.charAt(i) - key);
            decripted = String.valueOf(sb.append(stringChar[i]));
        }
        return decripted;

    }
}

class ShiftEncryption implements Strategy {

    @Override
    public String doOperation(String message, int key) {
        String encripted = "";
        char[] stringChar = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {

            if (message.charAt(i) >= 65 && message.charAt(i) + key <= 90 ||
                    message.charAt(i) >= 97 && message.charAt(i) + key <= 122) {

                stringChar[i] = (char) (message.charAt(i) + key);

            } else if (message.charAt(i) + key > 'Z') {

                stringChar[i] = (char) ((message.charAt(i) + key) - 26);
            } else {

                stringChar[i] = message.charAt(i);
            }


            encripted = String.valueOf(sb.append(stringChar[i]));

        }
        return encripted;

    }
}

class ShiftDecryption implements Strategy {

    @Override
    public String doOperation(String message, int key) {
        String decripted = "";
        char[] stringChar = new char[message.length()];


        for (int i = 0; i < message.length(); i++) {

            if (message.charAt(i) - key >= 'a' && message.charAt(i) <= 'z' ||
                    message.charAt(i) - key >= 'A' && message.charAt(i) <= 'Z') {

                stringChar[i] = (char) (message.charAt(i) - key);

            } else if (message.charAt(i) + key > 'a' ||
                    message.charAt(i) + key > 'A') {

                stringChar[i] = (char) ((message.charAt(i) - key) + 26);
            } else {

                stringChar[i] = message.charAt(i);
            }


            decripted = String.valueOf(sb.append(stringChar[i]));


        }
        return decripted;

    }
}

class Context {
    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String message, int key) {
        return strategy.doOperation(message, key);
    }
}