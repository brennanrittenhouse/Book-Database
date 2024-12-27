package bookBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Object class for Database
 */
public class Database {
    private ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Creates database object
     * @param books is an arraylist of Books
     */
    public Database(ArrayList<Book> books){
        this.books = books;
    }

    /**
     * Add a book to the arraylist if it's not already in it
     * @param book is the book to add
     * @return a string representation of the added book
     */
    public String addBook(Book book){
        String output = "";
        if(bookExists(book)){
            output = "Sorry. Book Already Exists";
            return output;
        }
        books.add(book);
        output = book.toString();
        return output;
    }

    /**
     * Print all books in database
     */
    public void printAll(){
        for(int i = 0; i<books.size(); i++){
            System.out.println(books.get(i).toString() + "\n\n\n");
        }
    }

    /**
     * @return String representation of the database
     */
    public String toString(){
        String output = "";
        for(int i = 0; i<books.size(); i++){
            output = output + books.get(i).toString() + "\n"; 
        }
        return output;
    }

    /**
     * Check if book is already in database
     * @param book is the book being searched for
     * @return true if book is found, false otherwise
     */
    public boolean bookExists(Book book){
        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getTitle().equals(book.getTitle()) && books.get(i).getAuthor().equals(book.getAuthor())
            && books.get(i).getPubyear() == book.getPubyear()){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if title exists in database
     * @param title is the string being searched for
     * @return true if found, false otherwise
     */
    public boolean titleExists(String title){
        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

    /**
     * Get a book from database according to its title
     * @param title is the title of the book
     * @return the book that is found
     */
    public Book getBook(String title){
        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                return books.get(i);
            }
        }
        return null;
    }

    /**
     * Get average rating of books in database
     * @return average rating or -1 if no books in database
     */
    public double avgRating(){
        if(books.size()==0){
            return -1;
        }
        double total = 0;
        for(int i = 0; i<books.size(); i++){
            total += books.get(i).getUserRating();
        }
        double average = total/books.size();
        return average;
    }


    /**
     * Get average page length of books in database
     * @return average page length or -1 if no typed books in database
     */
    public double avgPageLength(){
        if(books.size()==0){
            return -1;
        }
        double total = 0;
        double numTypedBooks = 0;
        for(int i = 0; i<books.size(); i++){
            //if it's a typed book, add to total length and update typed book count
            try{
                Date testDate = new Date("jan", 20, 2000);
                TypedBook typed = new TypedBook("title", "author", "fiction", 2000, testDate, testDate, 4, 200);
                if(books.get(i).getClass() == typed.getClass()){
                    numTypedBooks++;
                    total = total + books.get(i).getLength();
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            } 
        }
        if(numTypedBooks == 0){
            return -1;
        }
        else{
            return (total/numTypedBooks);
        }
    }

    /**
     * @return number of books in database
     */
    public int getNumBooks(){
        return books.size();
    }


    /**
     * Format database as a string for saving purposes
     * @return formated string representation of database
     */
    public String fileFormatData(){
        String output = "";
        int type = -1;
        for(int i = 0; i<books.size(); i++){
            if(books.get(i) instanceof TypedBook){
                type = 1;
            }
            else if(books.get(i) instanceof Audiobook){
                type = 2;
            }
            output = output + "type = \"" + type + "\"" + "\n";
            output = output + "title = \"" + books.get(i).getTitle() + "\"" + "\n";
            output = output + "author = \"" + books.get(i).getAuthor() + "\"" + "\n";
            output = output + "pubyear = \"" + books.get(i).getPubyear() + "\"" + "\n";
            output = output + "genre = \"" + books.get(i).getGenre() + "\"" + "\n";
            output = output + "rating = \"" + books.get(i).getUserRating() + "\"" + "\n";
            output = output + "length = \"" + books.get(i).getLength() + "\"" + "\n";
            output = output + "narrator = \"" + books.get(i).getNarrator() + "\"" + "\n";
            output = output + "start month = \"" + books.get(i).getStartDate().getMonth() + "\"" + "\n";
            output = output + "start day = \"" + books.get(i).getStartDate().getDay() + "\"" + "\n";
            output = output + "start year = \"" + books.get(i).getStartDate().getYear() + "\"" + "\n";
            output = output + "end month = \"" + books.get(i).getEndDate().getMonth() + "\"" + "\n";
            output = output + "end day = \"" + books.get(i).getEndDate().getDay() + "\"" + "\n";
            output = output + "end year = \"" + books.get(i).getEndDate().getYear() + "\"" + "\n\n";
        }
        return output;
    }

    /**
     * Find author with most books in database
     * @return top author
     */
    public String topAuthor(){
        String topAuthor = "";
        if(books.size() == 0){
            topAuthor = "No books in your database";
            return topAuthor;
        }
        
        HashMap<String, Integer> authors = new HashMap<String, Integer>();
        for(int i = 0; i<books.size(); i++){
            //if author is already in list, update count
            if(authors.containsKey(books.get(i).getAuthor())){
                int count = authors.get(books.get(i).getAuthor());
                authors.put(books.get(i).getAuthor(), (count + 1));
            }
            //if not in list, set author count to 1
            else{
                authors.put(books.get(i).getAuthor(), 1);
            }
        }
        //go through hashmap and find author with most books
        Iterator<HashMap.Entry<String, Integer>> iter = authors.entrySet().iterator();
        int max = 0;
       
        while(iter.hasNext()){
            HashMap.Entry<String, Integer> entry = iter.next();
            if(entry.getValue()>max){
                max = entry.getValue();
                topAuthor = entry.getKey();
            }
        }
        return topAuthor;     
    }

    /**
     * Find most common genre in database
     * @return top genre
     */
    public String topGenre(){
        String topGenre = "";
        if(books.size() == 0){
            topGenre = "No books in your database";
            return topGenre;
        }
        
        HashMap<String, Integer> genres = new HashMap<String, Integer>();
        for(int i = 0; i<books.size(); i++){
            //if genre already in list, update count
            if(genres.containsKey(books.get(i).getGenre())){
                int count = genres.get(books.get(i).getGenre());
                genres.put(books.get(i).getGenre(), (count+1));
            }
            //if not in list, set genre count to 1
            else{
                genres.put(books.get(i).getGenre(), 1);
            }
        }

        Iterator<HashMap.Entry<String, Integer>> iter = genres.entrySet().iterator();
        int max = 0;
        while(iter.hasNext()){
            HashMap.Entry<String, Integer> entry = iter.next();
            if(entry.getValue()>max){
                max = entry.getValue();
                topGenre = entry.getKey();
            }
        }
        return topGenre;
    }

    /**
     * Find all 5 star books in database
     * @return string representation of all 5 star books in database
     */
    public String getFiveStars(){
        if(books.size() == 0){
            return "No books in database";
        }
        String fiveStarBooks = "";
        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getUserRating() == 5){
                fiveStarBooks = fiveStarBooks + books.get(i).toString() + "\n\n";
            }
        }

        //if no five star books...
        if (fiveStarBooks == null || fiveStarBooks.trim().isEmpty()){
            fiveStarBooks = "No Five Star Books";
        }
        return fiveStarBooks;
    }

    /**
     * Search database by title, author, gnere, pubyear or rating
     * @param title is the book's title
     * @param author is the book's author
     * @param genre is the book's genre
     * @param pubyear is the book's publication year
     * @param rating is the book's rating
     * @return string representation of all books that match search criteria
     */
    public String search(String title, String author, String genre, int pubyear, int rating){
        String results = "";
        if(books.size() == 0){
            results = "No books to search for";
        }
       
        else{
            for(int i = 0; i<books.size(); i++){
                boolean titleFound = false;
                boolean authorFound = false;
                boolean genreFound = false;
                boolean yearFound = false;
                boolean ratingFound = false;

                //mark fields as found if they're blank or match database info
                if(title.equals("")){
                    titleFound = true;
                }
                else if(books.get(i).getTitle().equals(title)){
                    titleFound = true;
                }
                
                if(author.equals("")){
                    authorFound = true;
                }
                else if(books.get(i).getAuthor().equals(author)){
                    authorFound = true;
                }

                if(genre.equals("Any Genre")){
                    genreFound = true;
                }
                else if(books.get(i).getGenre().equals(genre)){
                    genreFound = true;
                }

                if(pubyear == -1){
                    yearFound = true;
                }
                else if(books.get(i).getPubyear() == pubyear){
                    yearFound = true;
                }

                if(rating == -1){
                    ratingFound = true;
                }
                else if(books.get(i).getUserRating() == rating){
                    ratingFound = true;
                }

                //if all criteria match, add book to output
                if(titleFound && authorFound && genreFound && yearFound && ratingFound){
                    System.out.println("book found");
                    results = results + books.get(i).toString() + "\n";
                }

            }
            //if no books found, output no matches
            if(results == null || results.trim().isEmpty()){
                results = "No books match these criteria";
            }         
        }
        return results;
    }
}
