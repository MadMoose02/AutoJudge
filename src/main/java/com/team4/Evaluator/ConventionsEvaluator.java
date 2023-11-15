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

    private boolean validAccessModifier ( String AccessModifier){


        if(AccessModifier.equals("private")||AccessModifier.equals("public")||AccessModifier.equals("protected")){
            return true ; 
        }

        
        return false ; 

    }

    private boolean valid3wordAttributeDeclaration ( String AttributeLine[] ){

        if ( validAccessModifier(AttributeLine[0]) && validDataTypes(AttributeLine[1]) && isCamelCase(AttributeLine[2])) {

            return true ; 
        }

        return false ; 

    }

    private boolean valid4WordAttributeDeclaration ( String AttributeLine[] ){

        if (validAccessModifier(AttributeLine[0]) && AttributeLine[1].equals("static") && validDataTypes(AttributeLine[2]) && isCamelCase(AttributeLine[3])) {

            return true ; 
        }

        return false ; 


    }


    //take in line , or // take in parameters 
    private boolean AttributeSyntaxCheck ( String AttributeLine ){ 

        String[] attributeDeclaration = AttributeLine.split(" "); 

        if( attributeDeclaration.length < 1){
            return false ; 
        }

        else if ( attributeDeclaration.length <= 2 ){

            return valid3wordAttributeDeclaration(attributeDeclaration); 

        }
        else if( attributeDeclaration.length > 2 && attributeDeclaration.length<=3 ){

            return valid4WordAttributeDeclaration(attributeDeclaration);

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


    private boolean valid3wordClassLabel ( String [] classLabelLine , String filename ){

    if( validAccessModifier(classLabelLine[0]) && classLabelLine[1].equals("class") && classLabelLine[2].equals(filename)){

        return true ; 
    }
             
    return false ; 

    }

    private boolean valid4wordClassLabel (String [] classLabelLine , String filename){

         if( validAccessModifier(classLabelLine[0]) && (classLabelLine[1].equals("final") ||classLabelLine[1].equals("abstract"))  && classLabelLine[2].equals("class")
         && classLabelLine[3].equals(filename)){

                return true ; 
        }

             return false ; 

    }

    private boolean valid6wordClassLabel (String [] classLabelLine , String filename){


        if(validAccessModifier(classLabelLine[0]) &&(classLabelLine[1].equals("final") ||classLabelLine[1].equals("abstract"))  && classLabelLine[2].equals("class") && 
            classLabelLine[3].equals(filename) &&(classLabelLine[4].equals("implements") || (classLabelLine[4].equals("extends")))){
                //implement a class checker 
                return true ; 
             }

             return false ; 

    }


    private boolean checkClassLabel ( String ClassName , String filename  ){
       
        String classLabelLine[] = ClassName.split(" "); 

        if (classLabelLine.length == 3 ){
        
            return valid3wordClassLabel(classLabelLine , filename) ; 
        }

        else if ( classLabelLine .length == 4){

            return valid4wordClassLabel(classLabelLine, filename) ; 
           
        }
        else if ( classLabelLine .length == 6){

            return valid6wordClassLabel(classLabelLine, filename); 

        }

        else 
        return false; 
    }

    private boolean detectConstructor ( File javaDocument , String line){

        String fileName = javaDocument.getName() ; 
        if(line.contains(fileName) && line.contains("(") && line.contains(")")){
            return true ; 
        }
        return false ; 
    }

    //valid method declaration

    private boolean validMethodSyntax ( String AccessModifier , String type , String Name  ){

        if(validAccessModifier(AccessModifier) && validDataTypes(type) && isCamelCase(Name)){
            return true ; 
        }

        return false ; 


    }

   
    public double verifyConstructor (String fileName , String line  ){

        if(line.contains(fileName) && line.contains("(") && line.contains(")")){
            return 1.00 ; 
        }

        return 0.00 ; 



    }
    
 
//read line until consturctor , to run the attribute checks 
//eval the constructor , 

//after constructor then eval methods 
// after return that is when we can start

//assume constructor needs to be written

//! Once line is processed , we generate a result on that 
//! if it didnt pass then
    @Override
    public double evaluate(File javaDocument) {
        String Filename = javaDocument.getName() ; 
       
        try {

            Scanner sc = new Scanner(javaDocument); 

            String line = sc.nextLine() ; 

            if (line == null)
                return 0.00 ; 

            while(sc.hasNextLine()){

                if(!detectConstructor(javaDocument, line)){
                   boolean tf =  AttributeSyntaxCheck(line); 
                }



                

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
