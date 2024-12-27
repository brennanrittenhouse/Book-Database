package bookBase;
/**
 * Object class for TypedBook
 */
public class TypedBook extends Book{
    private int pageCount;

    /**
     * Creates new TypedBook object
     * @param title is the book's title
     * @param author is the book's author
     * @param genre is the book's genre
     * @param pubyear is the book's publication year
     * @param startDate is the date the user started the book
     * @param endDate is the date the user finished the book
     * @param userRating is the rating the user gave the book
     * @param pageCount is the book's pagecount
     * @throws Exception is any of the fields are blank or invalid
     */
    public TypedBook(String title, String author, String genre, int pubyear, Date startDate, Date endDate, int userRating, int pageCount) throws Exception{
        super(title, author, genre, pubyear, startDate, endDate, userRating);
        this.pageCount = pageCount;
    }

    /**
     * @return book's page length
     */
    public int getLength(){
        return this.pageCount;
    }

    /**
     * @return book's narrator
     */
    public String getNarrator(){
        return "None";
    }

    /**
     * set page count
     * @param count is the book's page count
     */
    public void setPageCount(int count){
        this.pageCount = count;
    }

    /**
     * Determine if two TypedBooks are equal
     * @return true if equal, false otherwise
     */
    public boolean equals(Object otherObject){
        if(otherObject == null){
            return false;
        }
        else if(getClass()!= otherObject.getClass()){
            return false;
        }
        else{
            TypedBook otherBook = (TypedBook)otherObject;
            return(getTitle().equals(otherBook.getTitle()) && getAuthor().equals(otherBook.getAuthor())
            && getGenre().equals(otherBook.getGenre()) && getPubyear() == otherBook.getPubyear() && getStartDate().equals(otherBook.getStartDate())
            && getEndDate().equals(otherBook.getEndDate()) && getUserRating() == otherBook.getUserRating() && getLength() == otherBook.getLength());
        }
    }

    /**
     * @return string representation of TypedBook
     */
    public String toString(){
        String output = super.toString();
        output = output + "\nPage Count: " + pageCount;
        return output;
    }
}
