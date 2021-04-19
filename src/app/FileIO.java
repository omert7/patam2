package app;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Scanner;

import app.Commands.DefaultIO;

public class FileIO implements DefaultIO {

    Scanner in;
    PrintWriter out;

    public FileIO(String inputFileName, String outputFileName) {
        try {
            in = new Scanner(new FileReader(inputFileName));
            out = new PrintWriter(new FileWriter(outputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readText() {
        return in.nextLine();
    }

    @Override
    public void write(String text) {
        out.print(text);
        out.flush();
    }

    @Override
    public float readVal() {
        return in.nextFloat();
    }

    @Override
    public void write(float val) {
        out.print(val);
        out.flush();
    }

    public void close() {
        in.close();
        out.close();
    }
}
