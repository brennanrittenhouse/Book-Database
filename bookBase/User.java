package bookBase;
/**
 * Object class for User
 */
public class User {
    private String fullname;
    private String username;
    private String password;
    private Database books;

    /**
     * Creates new User object
     * @param fullname is the user's full name
     * @param username is the user's username
     * @param password is the passsword
     * @param books is the user's database
     * @throws Exception if any fields are blank or invalid
     */
    public User(String fullname, String username, String password, Database books) throws Exception{
        if(nonBlankString(fullname) && nonBlankString(username) && nonBlankString(password)){
            this.fullname = fullname;
            this.username = username;
            this.password = password;
        }
        else{
            throw new Exception("Error: Couldn't create User.\n Blank input.");
        }
        this.books = books;
    }

    /**
     * @return the user's full name
     */
    public String getFullname(){
        return this.fullname;
    }

    /**
     * @return the user's username
     */
    public String getUsername(){
        return this.username;
    }
    
    /**
     * @return the user's password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * @return the user's database
     */
    public Database getUserBooks(){
        return books;
    }

    /**
     * Set full name
     * @param fullname is the user's full name
     * @throws Exception if full name is blank
     */
    public void setFullname(String fullname) throws Exception{
        if(nonBlankString(fullname)){
            this.fullname = fullname;
        }
        else{
            throw new Exception("Error: Blank full name\n");
        }
        
    }

    /**
     * Set the user's username
     * @param username is the user's username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Set the user's password
     * @param password is the user's password
     * @throws Exception if password is blank
     */
    public void setPassword(String password) throws Exception{
        if(nonBlankString(password)){
            this.password = password;
        }
        else{
            throw new Exception("Error: Blank Password\n");
        }
        
    }

    /**
     * Set database
     * @param books is the user's database
     */
    public void setBooks(Database books){
        this.books = books;
    }

    /**
     * @param input is a string
     * @return true if input is not blank, false if blank
     */
    public static boolean nonBlankString(String input){
        if(input == null || input.trim().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * @return String representation of a User
     */
    public String toString(){
        return "Username: " + username + "\nFull Name: " + fullname + "\nPassword: " + password + "\n" + books.toString();
    }



}
