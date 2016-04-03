package sml;

import com.sun.tools.corba.se.idl.constExpr.Divide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Properties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

    // Provide full path to the file
    private static final String SRC = "/Users/gergelymeszaros/Documents/Birkbeck/work-SDP2016/cw-one/sml/src";
    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    public Translator(String fileName) {
        this.fileName = SRC + "/" + fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

        try (Scanner sc = new Scanner(new File(fileName))) {
            // Scanner attached to the file chosen by the user
            labels = lab;
            labels.reset();
            program = prog;
            program.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            // Each iteration processes line and reads the next line into line
            while (line != null) {
                // Store the label in label
                String label = scan();

                if (label.length() > 0) {
                    Instruction ins = getInstruction(label);
                    if (ins != null) {
                        labels.addLabel(label);
                        program.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.out.println("File: IO error " + ioE.getMessage());
            return false;
        }
        return true;
    }

    // line should consist of an MML instruction, with its label already
    // removed. Translate line into an instruction with label label
    // and return the instruction
    public Instruction getInstruction(String label) {

        int labelParam = 0;
        int constructorParam = 1;

        // Setup new Properties object
        Properties prop = new Properties();

        // Scan data
        String instData = scan();

        // Start with empty instruction
        Instruction instructionObject = null;

        try {
            FileInputStream fileStream = new FileInputStream(SRC + "/default.properties");
            prop.load(fileStream);
            String className = prop.getProperty(instData);

            // Get the instructions based on the specified classname
            Class<?> instructionClass = Class.forName(className);

            // Array of instructions
            Constructor<?>[] constructors = instructionClass.getConstructors();
            Constructor<?> constructor = constructors[constructorParam];
            Class<?>[] parameterTypes = constructors[constructorParam].getParameterTypes();

            // Create object of same length as parameterTypes which will be passed to the constructor
            Object[] objectData = new Object[parameterTypes.length];

            // Label is set based on the labelParam int value
            objectData[labelParam] = label;

            for (int i = 1; i < objectData.length; i++) {
                if (parameterTypes[i] == int.class){
                    objectData[i] = scanInt();
                }
                else if (parameterTypes[i] == (Class.forName("java.lang.String"))){
                    objectData[i] = scan();
                }
            }

            // Instruction object
            instructionObject = (Instruction) constructor.newInstance(objectData);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the created instruction
        return instructionObject;
    }

    /*
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0)
            return "";

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    // Return the first word of line as an integer. If there is
    // any error, return the maximum int
    private int scanInt() {
        String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}