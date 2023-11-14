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

    @Override
    public double evaluate(File javaDocument) {
        String filename = javaDocument.getName();

        if (filename == "LuggageManagementSystem"){
            if (checksAssociation(javaDocument, "Flight")){
                System.out.println("There exists an association between the LuggageManagementSystem and Flight classes");
            }
            else{
                System.out.println("No association between the LuggageManagementSystem and Flight classes");
            }

            if (checksAssociation(javaDocument, "Passenger")){
                System.out.println("There exists an association between the LuggageManagementSystem and Passenger classes");
            }
            else{
                System.out.println("No association between the LuggageManagementSystem and Passenger classes");
            }
        }

        if (filename == "LuggageManifest"){
            if (checksAssociation(javaDocument, "LuggageSlip")){
                System.out.println("There exists an association between the LuggageManifest and LuggageSlip classes");
            }
            else{
                System.out.println("No association between the LuggageManifest and LuggageSlip classes");
            }
        }
        return this.score;
    }
    
}
