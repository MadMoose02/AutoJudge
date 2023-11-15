package com.team4.Evaluator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HierarchyEvaluator implements SyntaxEvaluator {

    private double score;

    public HierarchyEvaluator() {
        this.score = 0.0;
    }

    private double checksInheritance (File javaFile, String keyword){
        try (Scanner scan = new Scanner(javaFile)) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                
                if (line.contains(keyword)){
                    return 1.0;
                }
            } 
        }

        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }
        return 0.0;
    }


    //associationClass - eg. Flight/Passenger/LuggageSlip
    private double checksAssociation (File javaFile, String associationClass){
        try (Scanner scan = new Scanner(javaFile)) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                
                if (line.contains(associationClass)){
                    return 1.0;
                }
            } 
        }

        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }
        return 0.0;
    }


    @Override
    public double evaluate(File javaDocument) {
        String filename = javaDocument.getName();

        if (filename == "LuggageManagementSystem"){
            this.score += checksAssociation(javaDocument, "Flight");
            this.score += checksAssociation(javaDocument, "Passenger");
        }

        if (filename == "LuggageManifest"){
            this.score += checksAssociation(javaDocument, "LuggageSlip");
        }

        this.score += checksInheritance(javaDocument, "extends");

        return this.score;

    }
    
}
