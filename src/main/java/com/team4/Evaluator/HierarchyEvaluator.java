package com.team4.Evaluator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HierarchyEvaluator implements SyntaxEvaluator {

    private double score;

    public HierarchyEvaluator() {
        this.score = 0.0;
    }

    private boolean checksAssociation (File javaFile){
        Boolean flightAssociation = false;
        Boolean passengerAssociation = false;

        try {
            try (Scanner scan = new Scanner(javaFile)) {
                while (scan.hasNext()) {
                    if (scan.next() == "Flight" || scan.next() == "ArrayList<Flight>"){
                        flightAssociation = true ;
                    }

                    if (scan.next() == "Passenger" || scan.next() == "ArrayList<Passenger>"){
                        passengerAssociation = true;
                    }
                }
                scan.close();
            }
            if (flightAssociation == true && passengerAssociation == true){
                return true;
            }
            else{
                return false;
            }
            
        } 
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return true;
    }

    @Override
    public double evaluate(File javaDocument) {
        return this.score;
    }
    
}
