package bookBase;
/**
 * Object class for Audiobook
 */
public class Audiobook extends Book{
    private String narrator;
    private int lengthMinutes;

    /**
     * Creates a new Audiobook object
     * @param title is the Audiobook's title
     * @param author is the Audiobook's author
     * @param genre is the genre of the Audiobook
     * @param pubyear is the Audiobook's publication year
     * @param startDate is the day the user started the audiobook
     * @param endDate is the day the user finished the audiobook
     * @param userRating is the rating the user gave the audiobook
     * @param narrator is the narrator of the audiobook
     * @param lengthMinutes is the lenght of the audiobook in minutes
     * @throws Exception if any of the fields are blank or invalid
     */
    public Audiobook(String title, String author, String genre, int pubyear, Date startDate, Date endDate, int userRating, String narrator, int lengthMinutes) throws Exception{
        super(title, author, genre, pubyear, startDate, endDate, userRating);
        this.narrator = narrator;
        this.lengthMinutes = lengthMinutes;
    }

    /**
     * returns Audiobook's narrator
     */
    public String getNarrator(){
        return this.narrator;
    }

    /**
     * returns Audiobook's length
     */
    public int getLength(){
        return this.lengthMinutes;
    }

    /**
     * Sets Audiobook narrator
     * @param narrator is the narrator
     */
    public void setNarrator(String narrator){
        this.narrator = narrator;
    }

    /**
     * Sets length of audiobook
     * @param length is the length of the audiobook
     */
    public void setLength(int length){
        this.lengthMinutes = length;
    }

    /**
     * @return true if audiobooks are equal, false otherwise
     */
    public boolean equals(Object otherObject){
        if(otherObject == null){
            return false;
        }
        else if(getClass()!= otherObject.getClass()){
            return false;
        }
        else{
            Audiobook otherBook = (Audiobook)otherObject;
            return(getTitle().equals(otherBook.getTitle()) && getAuthor().equals(otherBook.getAuthor())
            && getGenre().equals(otherBook.getGenre()) && getPubyear() == otherBook.getPubyear() && getStartDate().equals(otherBook.getStartDate())
            && getEndDate().equals(otherBook.getEndDate()) && getUserRating() == otherBook.getUserRating() && getNarrator().equals(otherBook.getNarrator())
            && getLength() == otherBook.getLength());
        }
    }

    /**
     * @return string representation of audiobook
     */
    public String toString(){
        String output = super.toString();
        output = output + "\nLength: " + lengthMinutes + "\nNarrator: " + narrator;
        return output;
    }

    /**
     * Determines if string is blank
     * @param input is the input string
     * @return true if string is blank, false otherwise
     */
    public static boolean nonBlankString(String input){
        if(input == null || input.trim().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * @return -1 because audiobooks don't have pagecounts
     */
    public int getPageCount(){
        return -1;
    }
}
