//816031395

public class LuggageSlip
{
    
    private Passenger owner ; 
    private static int luggageSlipIDCounter = 1 ;
    private  String luggageSlipID  ; 
    private String label  ; 
    
    
    public LuggageSlip  ( Passenger p , Flight f  ) {
        this.owner = p;
        this.label = ""; 
        this.luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_"+ 
        luggageSlipIDCounter ; 
        luggageSlipIDCounter++ ;
        
        
        
        
    }
    
    
    public LuggageSlip  ( Passenger p , Flight f , String label ) {
        
        this.owner = p;
        this.label = label; 
        this.luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_"+ 
        luggageSlipIDCounter ; 
        luggageSlipIDCounter++ ;
        
        
        
        
    }
    
    public String getLabel( ) {
        
        return this.label  ; 
        
    }
    
    public String getLuggageSlipID ( ){
        
        
        return this.luggageSlipID ; 
    }
    
    public Passenger getOwner () { 
        return this.owner ; 
    }
    
    public boolean hasOwner ( String passportNumber ){
        
        
        if( this.owner.getPassportNumber().equals(passportNumber)) {
            
            return true ; 
            
        }
        
        return false ; 
    }
    
    public String toString() {
     
        return getLuggageSlipID() + " " + this.owner.toString() + " " + getLabel() ; 
    }
    
    
    
    
    
    
    
    
}
