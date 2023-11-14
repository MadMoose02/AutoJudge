//STUDENT ID - 816031341
import java.util.Random;
public class Passenger{
    String passportNumber;
    String flightNo;
    String firstName;
    String lastName;
    int numLuggage;
    char cabinClass;
    
    Passenger (String passportNumber, String firstName, String lastName, String flightNo){
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNo = flightNo;
        
        assignRandomLuggageNumber();
        assignRandomCabinClass();
                
    }
    
    public String getPassportNumber(){
        return this.passportNumber;  
    }
    
    public String getFlightNo(){
        return this.flightNo;  
    }
    
    public String getFirstName(){
        return this.firstName;  
    }
    
    public String getLastName(){
        return this.lastName;  
    }
    
    public int getNumLuggage(){
        return this.numLuggage;  
    }
    
    public char getCabinClass(){
        return cabinClass;  
    }
    
    //Code from lab 1 was used to assist in the creation of 
    //this method
    private void assignRandomLuggageNumber(){
        int[] numberLuggage = {0, 1, 2, 3};
        Random r = new Random();
        int randInt = r.nextInt(4);
        
        //System.out.println(numberLuggage[randInt]);
        
        this.numLuggage = numberLuggage[randInt];
    }
    
    //Code from lab 1 was used to assist in the creation of 
    //this method 
    public void assignRandomCabinClass(){
        char[] classVal = {'F', 'B', 'P', 'E'};
        Random r = new Random();
        int randInt = r.nextInt(4);
        
        //System.out.println(classVal[randInt]);
        
        this.cabinClass = classVal[randInt];
    }
    
    public String toString(){
        return "PP NO. " + this.passportNumber + " NAME: " 
        + this.firstName.substring(0,1) +"."+ this.lastName 
        + " NUMLUGGAGE: " + this.numLuggage + " CLASS: " 
        + this.cabinClass;
    }
    
}