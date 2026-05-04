package healthtracker;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TableModel for the HealthReminder Treatments Table
 * @author Amy Schmieder
 */
public class TreatmentTableModel extends AbstractTableModel {
    private final String[] columns = {"Treatment", "Dose", "Frequency", "Purpose", "Notes"};
    private List<Treatment> treatments;
    
    public TreatmentTableModel(List<Treatment> treatment){
        this.treatments = treatment;
    }
    
    @Override
    public int getRowCount(){return treatments.size();}
    
    @Override
    public int getColumnCount(){return columns.length;}
    
    @Override
    public Object getValueAt(int row, int col){
        Treatment t = treatments.get(row);
        if(t instanceof Medication){  //this is a little janky, but not sure what else to do
            Medication m = (Medication)t;
            return switch(col){
                case 0 -> m.getName();
                case 1 -> m.getDose();
                case 2 -> m.getFrequency();
                case 3 -> m.getPurpose();
                case 4 -> m.getNotes();
                default -> null;
            };            
        } else{
            return switch(col){
                case 0 -> t.getName();
                case 1 -> null;
                case 2 -> t.getFrequency();
                case 3 -> t.getPurpose();
                case 4 -> t.getNotes();
                default -> null;
            };
        }
    }
    
    public Treatment getTreatment(int row){
        return treatments.get(row);
    }
    
    public void addTreatment(Treatment t){
        treatments.add(t);
        fireTableRowsInserted(treatments.size()-1, treatments.size()-1);
    }
    
    public void updateTreatment(int row, Treatment t){
        treatments.set(row, t);
        fireTableRowsUpdated(row, row);
    }
    
    public void deleteTreatment(int row){
        treatments.remove(row);
        fireTableRowsDeleted(row,row);
    }
    
}
