package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class PassengerTestCase extends TestCase{

    public PassengerTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if ((line.contains("this.passportNumber") || line.contains("passportNumber")) && line.contains("=") && line.contains("passportNumber")){
                    return true;
                };

                if ((line.contains("this.firstName") || line.contains("firstName")) && line.contains("=") && line.contains("firstName")){
                    return true;
                };

                if ((line.contains("this.lastName") || line.contains("lastName")) && line.contains("=") && line.contains("lastName")){
                    return true;
                };

                if ((line.contains("this.flightNo") || line.contains("flightNo")) && line.contains("=") && line.contains("flightNo")){
                    return true;
                };

                if (line.contains("this.cabinClass") || line.contains("cabinClass") && line.contains("rand.nextInt")){
                    return true;
                }

                if (line.contains("this.numLuggage") || line.contains("numLuggage") && line.contains("rand.nextInt")){
                    return true;
                }
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testAssignRandomCabin(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                // if (line.contains("this.cabinClass") || line.contains("cabinClass") && line.contains("rand.nextInt")){
                //     return true;
                // }

                if (line.contains("switch") || line.contains("(cabinClass)")){
                    return true;
                }

                if (line.contains("case 1") && line.contains("cabinClass = 'F")){
                    return true;
                }

                if (line.contains("case 2") || line.contains("cabinClass = 'B")){
                    return true;
                }

                if (line.contains("case 3") || line.contains("cabinClass = 'P")){
                    return true;
                }

                if (line.contains("case 4") || line.contains("cabinClass = 'E")){
                    return true;
                }
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testToString(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("System.out.print")||line.contains("System.out.println")){
                    return true;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    @Override
    public boolean testCriteria() {
        if (testConstructor() && testAssignRandomCabin() && testToString()){
            return true;
        }
        return false;
    }
    
}
