package com.team4.Evaluator;

import java.io.File;

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










    @Override
    public double evaluate(File javaDocument) {
        return this.score;
    }
}
