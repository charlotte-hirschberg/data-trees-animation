package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Last Updated: Feb 21, 2016
 * @Description: Creates a button with the word "Exit" that closes all open panes
 */

//Imports
import javafx.scene.control.Button;

//Begin Class ExitButton
/**
 * Button that closes all panes on click
 * @author Charlotte
 */
public class ExitButton extends Button
{
    final private Button exBtn = new Button("Exit");
    public ExitButton()
    {
        setText("Exit");
        setPrefWidth(75);
        setOnAction(e ->
        {
            System.exit(0);
        });
    }
}//End Class ExitButton