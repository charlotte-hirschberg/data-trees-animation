package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Created Date: Feb 28, 2016
 * @Last Update: Mar. 6, 2016
 * @Description: Constructs and displays Alert using given parameters
 */

//Imports
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

//Begin Class AlertSetter
public class AlertSetter
{
    Optional<ButtonType> result = null;//for use if Alert is Confirmation type
    
    /**
     * accepts AlertType and three strings that populate Title, HeaderText, and ContentText
     * @param alType
     * @param title
     * @param header
     * @param content 
     */
    public AlertSetter(Alert.AlertType alType, String title, String header, String content)
    {
        Alert al = new Alert(alType);
        al.setTitle(title);
        al.setHeaderText(header);
        al.setContentText(content);
        
        if(alType == Alert.AlertType.CONFIRMATION)
        {
            //store user's choice (OK or Cancel) in result
            result = al.showAndWait();
        }
        
        else
        {
            al.show();
        }
    }
    
    /**
     * Output result
     * @return 
     */
    public Optional<ButtonType> getChoice()
    {
        return result;    
    }
}//End Class AlertSetter
