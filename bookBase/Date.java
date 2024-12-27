package bookBase;
/**
 * Object class for Date
 */
public class Date {
    private int year;
    private String month;
    private int day;

    /**
     * Create a new Date object
     * @param month is an abreviated string representation of the month
     * @param day is an int
     * @param year is an int
     * @throws Exception is any fields are blank or invalid
     */
    public Date(String month, int day, int year) throws Exception{
        if(validDay(month, day, year)){
            this.month = month;
            this.day = day;
            this.year = year;
        }
        else{
            throw new Exception("Error: Invalid Date.");
        }
        
    }

    /**
     * Get the date's year
     * @return the date's year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Get the date's month
     * @return the date's month
     */
    public String getMonth(){
        return this.month;
    }

    /**
     * Get the date's day
     * @return the date's day
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Set the date's year
     * @param year is the date's year
     */
    public void setYear(int year){
        this.year = year;
    }

    /**
     * Set the date's month
     * @param month is the date's month
     */
    public void setMonth(String month){
        this.month = month;
    }

    /**
     * Set the date's day
     * @param day is the date's day
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * Format a string representation of a date
     * @return string representation of a date
     */
    public String toString(){
        return month + " " + day + ", " + year;
    }

    /**
     * Checks if year is valid
     * @param year is the date's year
     * @return true if valid, false otherwise
     */
    public boolean validYear(int year){
        if(year<0 || year>2025){
            return false;
        }
        return true;
    }

    /**
     * Check if day is valid
     * @param month
     * @param day
     * @param year
     * @return true if day is valid, false otherwise
     */
    public boolean validDay(String month, int day, int year){
        if(!validYear(year)){
            return false;
        }
        String[] Months30 = {"Apr", "Jun", "Sept", "Nov"};
        for(int i = 0; i<4; i++){
            if(month.equals(Months30[i])){
                if(day>0 && day<=30){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        if(month.equals("Feb")){
            if(day>0 && day<=28){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            //must be a month with 31 days
            if(day>0 && day<=31){
                return true;
            }
            else{
                return false;
            }
        }
        
    }
}
