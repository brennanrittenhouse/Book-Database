package bookBase;
/**
 * Object class for Book
 */
public abstract class Book {
    private String title;
    private String author;
    private String genre;
    private int pubyear;
    private Date startDate;
    private Date endDate;
    private int userRating;

    /**
     * Book Constructor
     * @param title is the book's title
     * @param author is the book's author
     * @param genre is the book's genre
     * @param pubyear is the book's publication year
     * @param startDate is the day the user started the book
     * @param endDate is the day the user finished the book
     * @param userRating is the rating the user gave the book
     * @throws Exception if any fields are blank or invalid
     */
    public Book(String title, String author, String genre, int pubyear, Date startDate, Date endDate, int userRating) throws Exception{
        if(nonBlankString(title) && nonBlankString(author)){
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.pubyear = pubyear;
            this.startDate = startDate;
            this.endDate = endDate;
            this.userRating = userRating;
        }
        else{
            throw new Exception("Error: Couldn't create Book.\n Blank input.");
            
        }
        
        
    }

    /**
     * Get book title
     * @return book title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Get book author
     * @return author of the book
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * Get the book's genre
     * @return the book's genre
     */
    public String getGenre(){
        return this.genre;
    }

    /**
     * Get the book's publication year
     * @return book's publication year
     */
    public int getPubyear(){
        return this.pubyear;
    }

    /**
     * Get the day the user started the book
     * @return the start date
     */
    public Date getStartDate(){
        return this.startDate;
    }

    /**
     * Get the date the user finished the book
     * @return the end date
     */
    public Date getEndDate(){
        return this.endDate;
    }

    /**
     * Get the user's rating of the book
     * @return the user's rating of the book
     */
    public int getUserRating(){
        return this.userRating;
    }

    /**
     * Set the book's title
     * @param title is the book title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Set the book's author
     * @param author is the book author
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * Set the book's genre
     * @param genre is the book's genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }

    /**
     * set the book's publication year
     * @param pubyear is the book's publication year
     */
    public void setPubYear(int pubyear){
        this.pubyear = pubyear;
    }

    /**
     * set the date the user started the book
     * @param start is the start date
     */
    public void setStartDate(Date start){
        this.startDate = start;
    }

    /**
     * set the date the user finished the book
     * @param end is the end date
     */
    public void setEndDate(Date end){
        this.endDate = end;
    }

    /**
     * Rate the book
     * @param rating is the user's rating of the book
     */
    public void rate(int rating){
        this.userRating = rating;
    }

    /**
     * @return true if two books are equal
     */
    public abstract boolean equals(Object otherObject);

    /**
     * @return length of book
     */
    public abstract int getLength();

    /**
     * @return narrator of audiobook
     */
    public abstract String getNarrator();

    /**
     * @return string representation of a book
     */
    public String toString(){
        return (title + " by " + author + " (" + pubyear + ")\nGenre: " + genre
        + "\nStarted: " + startDate.toString() + "\nFinished: " + endDate.toString() +
        "\nRating: " + userRating + " stars");
    }

    /**
     * Check if string is blank
     * @param input is the string being checked
     * @return true if input is blank, false otherwise
     */
    public static boolean nonBlankString(String input){
        if(input == null || input.trim().isEmpty()){
            return false;
        }
        return true;
    }
    

}
