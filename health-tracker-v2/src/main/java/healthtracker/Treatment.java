package healthtracker;

/**
 *
 * @author Amy Schmieder
 */
public class Treatment {
    private int dbid, userid;
    private String name, notes, purpose, frequency;
    
    public Treatment(int dbid, int userid, String name, String notes, String purpose, String frequency){
        this.dbid = dbid;
        this.userid = userid;
        this.name = name;
        this.notes = notes;
        this.purpose = purpose;
        this.frequency = frequency;
    }
    /**
     * Gets the db id for this treatment. This should not be altered after instantiation.
     * @return int database ID for the treatment
     */
    public int getDBID(){return dbid;}
    /**
     * Gets the user id for the user this treatment is associated with. 
     * This should not be altered after instantiation.
     * @return int user ID for the treatment
     */
    public int getUserID(){return userid;}
    
    /**
     * The name or brief description of the Treatment, e.g. amoxicillin, or blood
     * pressure reading.
     * @return String name
     */
    public String getName(){return name;}
    
    /**
     * Sets the name of the treatment
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * The purpose of the Treatment.
     * @return String purpose
     */
    public String getPurpose(){return purpose;}
    
    /**
     * Set the purpose of the treatment.
     * @param  purpose
     */
    public void setPurpose(String purpose){
        this.purpose = purpose;
    }    
    
    
    /**
     * Notes about the Treatment.
     * @return String notes
     */
    public String getNotes(){return notes;}
    
    /**
     * Set notes about the treatment.
     * @param  notes
     */
    public void setNotes(String notes){
        this.notes = notes;
    }    
    
    public String getFrequency(){return frequency;}
    
    public void setFrequency(String freq){
        this.frequency = freq;
    }
    

}
