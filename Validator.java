package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_Week2
 * @Created: Feb. 21, 2016
 * @Last Update: Mar. 13, 2016
 * @Description: Establishes methods for checking that files exist, strings are not empty, names are valid, 
 *              integers meet min/max parameters, and strings are parseable as doubles or integers
 * @Future Update: Add methods for checking min/max for doubles; use Generics to handle different number types
 */

//Imports
import java.io.File;
import javafx.scene.control.Alert.AlertType;

//Begin Class Validator
public class Validator
{
    /**
     * return true if there is a file at the indicated location
     * @param filePath
     * @return 
     */
    public boolean fileExists(String filePath)
    {
        boolean b = false;
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory())
        {
            b=true;
        } 
        return b;
    }
    
    /**
     * Check that string is not empty
     * @param s
     * @return true/false
     */
    public boolean isEmpty(String s)
    {
        boolean b = false;
        if("".equals(s))
        {
            b = true;
        }
        return b;
    }
    
    /**
     * Check that name field only contains characters, dots, apostrophes, dashes
     * @param s
     * @return boolean
     * Regex source: http://stackoverflow.com/a/15806080
     */
    public boolean isValidName(String s)
    {
        boolean b = false;
        String regx = "^[\\p{L} .'-]+$";
        if(s.matches(regx) && s.length() > 1 && s.length() < 36)//matches regx and has 2-35 char
        {
            b = true;
        }
        else
        {
            String errorMsg = "You did not enter a valid name.";
            AlertSetter alert = new AlertSetter(AlertType.WARNING, "Error", "There was an input error:", errorMsg);
        }
        
        return b;
    }
    
    /**
     * check that @num is greater than @min
     * @param num
     * @param min
     * @return 
     */
    public boolean inRange(int num, int min)
    {
        boolean isInRange = true;
        if(num < min)//input is not higher than min
        {
            isInRange = false;
            String sMin = Integer.toString(min);
            String errorMsg = "You cannot enter a number lower than " + sMin + ".";
            AlertSetter alert = new AlertSetter(AlertType.WARNING, "Error", "There was an input error:", errorMsg);
        }
        
        return isInRange;
    }    
    
    /**
     * Method overloading
     * check that @num is between @min and @max
     * @param num
     * @param min
     * @param max
     * @return 
     */
    public boolean inRange(int num, int min, int max)
    {
        boolean isInRange = true;
        if(num < min || num > max)//input is lower than min or greater than max
        {
            isInRange = false;
            String sMin = Integer.toString(min);
            String sMax = Integer.toString(max);
            String errorMsg = "You must enter a number between " + sMin + " and " + sMax + ".";
            AlertSetter alert = new AlertSetter(AlertType.WARNING, "Error", "There was an input error:", errorMsg);
        }
        
        return isInRange;
    }
    
    /**
     * check that @num doesn't exceed @max
     * @param num
     * @param max
     * @return boolean
     */
    public boolean exceedsMax(int num, int max)
    {
        boolean isInRange = true;
        if(num > max)//input exceeds max
        {
            isInRange = false;
            String sMax = Integer.toString(max);
            String errorMsg = "You cannot enter a number greater than " + sMax + ".";
            AlertSetter alert = new AlertSetter(AlertType.WARNING, "Error", "There was an input error:", errorMsg);
        }
        
        return isInRange;
    }
    
    /**
     * Check that intString can be parsed as integer
     * @param intString
     * @return 
     */
    public boolean isValidInt(String intString)
    {
        boolean isParseable= true;
        
        try
        {
            Integer.parseInt(intString);//it is an integer
        }
        catch(NumberFormatException e)//not an integer
        {
            isParseable = false;
        }
        
        return isParseable;
    }
    
    /**
     * Check that dblString can be parsed as double
     * @param dblString
     * @return 
     */
    public boolean isValidDouble(String dblString)
    {
        boolean isParseable= true;
        try
        {
            Double.parseDouble(dblString);//it is a double
        }
        catch(NumberFormatException e)//not an integer
        {
            isParseable = false;
        }
        
        return isParseable;
    }
}//End Class Validator