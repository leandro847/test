package healthtracker;

/**
 * Class to specify a medication
 * @author Amy Schmieder
 */
public class Medication extends Treatment {
    private String dose;
    private int quantLeft;
    
    /**
     * Instantiate a Medication
     * @param dbid
     * @param userid
     * @param name
     * @param notes
     * @param purpose
     * @param frequency
     * @param dose
     * @param quant 
     */
    public Medication(int dbid, int userid, String name, String notes, String purpose, int frequency, String dose, int quant){
        super(dbid, userid, name, notes, purpose, frequency);
        this.dose = dose;
        this.quantLeft = quant;
    }
    
    /**
     * Get the dosage of this medication.
     * @return dose
     */
    public String getDose(){return dose;}
    
    /**
     * Alter the dosage of this medication.
     * @param dose 
     */
    public void setDose(String dose){
        this.dose = dose;
    }
    
    /**
     * Get the number of doses remaining.
     * @return quantity remaining
     */
    public int getQuantityRemaining(){return quantLeft;}
    
    /**
     * Set the quantity of doses remaining.
     * @param quant 
     */
    public void setQuantityRemaining(int quant){
        this.quantLeft = quant;
    }
    
    /**
     * Decrement the quantity remaining by 1, after taking one dose.
     */
    public void decrementQuantityRemaining(){
        quantLeft--;
    }
    
    
}
