//STUDENT ID - 816031341
import java.util.ArrayList;
import java.util.Iterator;
public class LuggageManifest{
    ArrayList<LuggageSlip> slips;
    
    LuggageManifest (){
        this.slips = new ArrayList<LuggageSlip>();       
    }
    
    public ArrayList<LuggageSlip> getSlips(){
        return this.slips;  
    }
    
    public String addLuggage(Passenger p, Flight f){
        String excessCost = "$";
        int extraLuggage;
        int counter = 0;
        if (p.getCabinClass() == 'F'){
            excessCost = getExcessLuggageCost(p.getNumLuggage(),3);
            
            if (p.getNumLuggage() < 3){
                for (counter=0; counter < p.getNumLuggage(); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }   
            }
            else{
                for (counter=0; counter < f.getAllowedLuggage(p.getCabinClass()); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }   
                extraLuggage = p.getNumLuggage() - 3;

                for (counter=0; counter < extraLuggage; counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f,"$35");
                    slips.add(newSlip);
                }   
            }
        }else if (p.getCabinClass() == 'B'){
            excessCost = getExcessLuggageCost(p.getNumLuggage(),2);
            
            if (p.getNumLuggage() < 2){
                for (counter=0; counter < p.getNumLuggage(); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }   
            }
            else{
                for (counter=0; counter < f.getAllowedLuggage(p.getCabinClass()); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }  
                extraLuggage = p.getNumLuggage() - 2;

                for (counter=0; counter < extraLuggage; counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f,"$35");
                    slips.add(newSlip);
                }   
            }
        }else if (p.getCabinClass() == 'P'){
            excessCost = getExcessLuggageCost(p.getNumLuggage(),1);
            
            if (p.getNumLuggage() < 1){
                for (counter=0; counter < p.getNumLuggage(); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }   
            }
            else{
                for (counter=0; counter < f.getAllowedLuggage(p.getCabinClass()); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                }   
                extraLuggage = p.getNumLuggage() - 1;

                for (counter=0; counter < extraLuggage; counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f,"$35");
                    slips.add(newSlip);
                }   
            }
        }else if (p.getCabinClass() == 'E'){
            excessCost = getExcessLuggageCost(p.getNumLuggage(),0);
            
            if (p.getNumLuggage() > 0){
                for (counter=0; counter < f.getAllowedLuggage(p.getCabinClass()); counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f);
                    slips.add(newSlip);
                } 
                extraLuggage = p.getNumLuggage();
    
                for (counter=0; counter < extraLuggage; counter++){
                    LuggageSlip newSlip = new LuggageSlip(p,f,"$35");
                    slips.add(newSlip);
                }   
            }
        }
        if (p.getNumLuggage() == 0)
            return "PP NO. " + p.getPassportNumber() + " NAME: " + 
            p.getFirstName().substring(0,1) + "." + p.getLastName() +
            " NUMLUGGAGE: " + p.getNumLuggage() + " CLASS: " + 
            p.getCabinClass() + "\n" + "No Luggage to add.";
        else
            return "PP NO. " + p.getPassportNumber() + " NAME: " + 
                p.getFirstName().substring(0,1) + "." + p.getLastName() +
                " NUMLUGGAGE: " + p.getNumLuggage() + " CLASS: " + 
                p.getCabinClass() + "\n" + "Pieces Added: (" + 
                p.getNumLuggage() + "). Excess Cost: " + excessCost;     
    }
    
    public String getExcessLuggageCost(int numPieces, int numAllowedPieces){
        if (numPieces > numAllowedPieces)
            return "$" + (35 * (numPieces - numAllowedPieces));
                
        return "$0";        
    }
    
    //Code from "Arraylist Handout" given in class was used to  
    //assist in the creation of this method
    public String getExcessLuggageCostByPassenger(String passportNumber){
        String totalCost = "$0";
        Iterator <LuggageSlip> iter = this.slips.iterator(); 
        while(iter.hasNext( )){ 
            LuggageSlip slip = iter.next( ); 
            if (slip.hasOwner(passportNumber) == true){
                totalCost = getExcessLuggageCost
                            (slip.owner.getNumLuggage(),Flight.getAllowedLuggage(slip.owner.getCabinClass()));
        
                if (totalCost.equals("$0"))
                    return "No cost";
                else 
                    return totalCost;
            }
        }
        
        return "Error! Passport Number not found.";
    }
    
    //Code from "Arraylist Handout" given in class was used to  
    //assist in the creation of this method
    public String toString(){
        String label;
        String manifest = "No Luggage Found.\n";
        
        Iterator <LuggageSlip> iter = this.slips.iterator(); 
        if (iter.hasNext()) 
            manifest = "";
            
        while(iter.hasNext()){
            LuggageSlip slip = iter.next( );
            
            if (slip.label.equals("$35"))
                label = "$35";
            else
                label = " ";
                
            manifest = manifest + slip.getLuggageSlipID() + " PP NO. " + 
                        slip.owner.getPassportNumber() + " NAME: " + 
                        slip.owner.getFirstName().substring(0,1) + "." 
                        + slip.owner.getLastName() + " NUMLUGGAGE: " + 
                        slip.owner.getNumLuggage() + " CLASS: " + 
                        slip.owner.getCabinClass() + " " + label + "\n";
                
        }    
        return manifest;
    }
}