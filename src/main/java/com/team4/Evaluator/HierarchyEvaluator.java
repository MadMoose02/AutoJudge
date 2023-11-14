package com.team4.Evaluator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HierarchyEvaluator implements SyntaxEvaluator {

    private double score;

    public HierarchyEvaluator() {
        this.score = 0.0;
    }

    //associationClass - eg. Flight/Passenger/LuggageSlip
    private boolean checksAssociation (File javaFile, String associationClass){
        try (Scanner scan = new Scanner(javaFile)) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                
                if (line.contains(associationClass)){
                    return true;
                }
            }
            scan.close();
            return false;    
        }

        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }
        return false;
    }

    private void toString (File javaDocument, String associationClass1, String associationClass2){
        if (checksAssociation(javaDocument, associationClass2)){
            System.out.println("There exists an association between the " + associationClass1 + " and " + associationClass2 + " classes");
        }

        else{
             System.out.println("No association found between the " + associationClass1 + " and " + associationClass2 + " classes");
        }
    }

    @Override
    public double evaluate(File javaDocument) {
        String filename = javaDocument.getName();

        if (filename == "LuggageManagementSystem"){
            toString(javaDocument, filename, "Flight");
            toString(javaDocument, filename, "Passenger");
        }

        if (filename == "LuggageManifest"){
            toString(javaDocument, filename, "LuggageSlip");
        }
        return this.score;
    }
    
}
