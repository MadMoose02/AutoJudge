package com.team4.Evaluator;

import java.io.File;
import java.util.Scanner;


public class ConventionsEvaluator implements SyntaxEvaluator {
    
    private double score; // store the items in the java file in these , attributes 
    private String filename;
    private String className;
    private String constructorName;
    private String FligthNoAttribute ; 
    private String passportNumberAttribute ; 
    private String firstNameAttribute ; 
    private String lastNameAttribute ; 
    private String numLuggageAttribute ; 
    private String cabinClassAttribute ; 

    private String methodName;
    private boolean correctSignature;
   


    // which class analyszes the java doc 
    // each attribute is checked for syntax and naming convention
    // we pull from file opened and store in attributes 


    //make a function combining syntax and camel case together 
    private boolean isCamelCase(String Name  ){

        if( Name == null || Name.isEmpty() || !Name.matches(".*[A-Z].*")){
            return false ; 
        }

        if(!Character.isLowerCase(Name.charAt(0))){
            return false ; 
        }

        for ( int i = 1 ; i < Name.length() ; i++){
            if(Character.isUpperCase(Name.charAt(i)) && Character.isUpperCase(Name.charAt(i-1))){
                return false ; 
            }

           
        }

         return true ; 

    }

    private boolean validDataTypes ( String dataType){

        if(dataType.equals("int")||dataType.equals("String")||dataType.equals("boolean")||
        dataType.equals("ArrayList<LuggageSlip>") || dataType.equals("Passenger")||dataType.equals("Flight")||
        dataType.equals("LuggageSlip")){

            return true ; 
        }

        return false ; 



    }


    //take in line , or // take in parameters 
    private boolean AttributeSyntaxCheck ( String AttributeLine ){ 

        String[] AccessModifier = AttributeLine.split(" "); 

        if( AccessModifier.length < 1){
            return false ; 
        }

        else if ( AccessModifier.length <= 2 ){

        if ((AccessModifier[0].equals("private")||AccessModifier[0].equals("public")||AccessModifier[0].equals("protected")
        && validDataTypes(AccessModifier[1])) && isCamelCase(AccessModifier[2])) {

            return true ; 
        }

        return false ; 

        }
        else if( AccessModifier.length > 2 && AccessModifier.length<=3 ){

        if ((AccessModifier[0].equals("private")||AccessModifier[0].equals("public")||AccessModifier[0].equals("protected")
        && AccessModifier[1].equals("static") && validDataTypes(AccessModifier[2])) && isCamelCase(AccessModifier[3])) {

            return true ; 
        }

        return false ; 

        }

     
        else
        return false ; 

    

    }


    // !if syntax , does not match up with required doc then fail 

    public boolean MethodCompare ( String expectedReturnType , String actualReturnType ){
        return expectedReturnType.equals(actualReturnType); 
    }



    public ConventionsEvaluator() {
        this.score = 0.0;
    }

    /* *
    private boolean testClassName(String className) {
        return this.className.equals(className);
    }

    private boolean testConstructorName(String constructorName) {
        return this.constructorName.equals(constructorName);
    }

    private boolean testFligthNoAttribute (String FligthNoAttribute){
        return this.FligthNoAttribute.equals(FligthNoAttribute) ; 
    }

    private boolean testPassportNumber ( String passportNumber){
        return this.passportNumberAttribute.equals(passportNumber); 
    }

    private boolean testFirstName ( String firstNameAttribute){
        return this.firstNameAttribute.equals(firstNameAttribute); 
    }

    private boolean 

    */ 




    // !how do we know when the attribtues are done then move to constructor 

    private boolean checkClassLabel ( String ClassName , String filename  ){
       
        String classLabelLine[] = ClassName.split(" "); 

        if (classLabelLine.length == 3 ){
        
            if( ( classLabelLine[0].equals("private") || classLabelLine[0].equals("public") || classLabelLine[0].equals("protected") ) &&
             classLabelLine[1].equals("class") && classLabelLine[2].equals(filename)){

                return true ; 
             }
             
             return false ; 
            
            //classLabelLine[3].equals(filename) ; 
        }

        else if ( classLabelLine .length == 4){

            if( ( classLabelLine[0].equals("private") || classLabelLine[0].equals("public") || classLabelLine[0].equals("protected") ) &&
             (classLabelLine[1].equals("final") ||classLabelLine[1].equals("abstract"))  && classLabelLine[2].equals("class")){

                return true ; 
             }
             return false ; 

        }

        else if ( classLabelLine .length == 6){

            if( ( classLabelLine[0].equals("private") || classLabelLine[0].equals("public") || classLabelLine[0].equals("protected") ) &&
             (classLabelLine[1].equals("final") ||classLabelLine[1].equals("abstract"))  && classLabelLine[2].equals("class") && 
             (classLabelLine[3].equals("implements") || (classLabelLine[3].equals("extends")))){
                //implement a class checker 
                return true ; 
             }

             return false ; 

        }

        else 
        return false; 




    }

    //private constructorBuil




    private boolean evaluateAttributes ( File javaDocument){

        String Filename = javaDocument.getName() ; 
       
        try {

            Scanner sc = new Scanner(javaDocument); 
            
            while(sc.hasNextLine()){


                String line = sc.nextLine() ; 

                if(line.equals())



            }
            
            
            
       sc.close() ;
        }
        
        catch(Exception e){
            System.out.println(" "+ e.getMessage() + " ") ;
        }


        return false ; 
    }


    
 
//read line until consturctor , to run the attribute checks 
//eval the constructor , 

//after constructor then eval methods 
// after return that is when we can start


    @Override
    public double evaluate(File javaDocument) {
        String Filename = javaDocument.getName() ; 
       
        try {

            Scanner sc = new Scanner(javaDocument); 
            
            while(sc.hasNextLine()){
                String line = sc.nextLine() ; 

                //if(line)



            }
            
            
            
       sc.close() ;
        }
        
        catch(Exception e){
            System.out.println(" "+ e.getMessage() + " ") ;
        }
        
        

        



        return this.score; 

    
    }
    
}
