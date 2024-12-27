package bookBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class of all bookbase users
 */
public class AllUsers {
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Creates a new AllUsers object
     * @param users is an arraylist of Users
     */
    public AllUsers(ArrayList<User> users){
        this.users = users;
    }

    /**
     * Get all the users
     * @return the arraylist of users
     */
    public ArrayList<User> getUsers(){
        return this.users;
    }

    /**
     * Set allusers
     * @param users is an arraylist of Users
     */
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    /**
     * Add a user to AllUsers
     * @param person is a User
     */
    public void addUser(User person){
        users.add(person);
    }

    /**
     * Check if username is in AllUsers
     * @param username is the username to search for
     * @return true if username if found, false otherwise
     */
    public boolean usernameExists(String username){
        for(int i = 0; i<users.size(); i++){
            System.out.println("Looking for user");
            if(users.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }


    /**
     * Check if password is correct for given username
     * @param username is a String representing the user's username
     * @param password is a String representing the user's password
     * @return the user's position in list of users or -1 if not found
     */
    public int correctPassword(String username, String password){
        for(int i = 0; i<users.size(); i++){
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Find and return User associated with given username
     * @param username is a Strinb
     * @return the associated User object
     */
    public User getUser(String username){
        for(int i = 0; i<users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * Get a user's database if login info is correct
     * @param username is a String
     * @param password is a String
     * @return the user's database
     */
    public Database login(String username, String password){
        int result = correctPassword(username, password);
        User person = users.get(result);
        return person.getUserBooks();
    }

    /**
     * Save all users and their databases to a text file
     * @param filename is the file where the data will be saved
     */
    public void saveUsersAndDatabases(String filename){
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(new FileOutputStream(filename));
        }catch(FileNotFoundException e){
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }

        for(int i = 0; i<users.size(); i++){
            outputStream.print("full name = \"");
            outputStream.println(users.get(i).getFullname() + "\"");
            outputStream.print("username = \"");
            outputStream.println(users.get(i).getUsername() + "\"");
            outputStream.print("password = \"");
            outputStream.println(users.get(i).getPassword() + "\"");
            outputStream.print("numbooks = \"");
            outputStream.println(users.get(i).getUserBooks().getNumBooks() + "\"" + "\n");
            String userDatabase = users.get(i).getUserBooks().fileFormatData();
            outputStream.println(userDatabase);
        }
        outputStream.close();
    }

    /**
     * Create users and databases from data in textfile
     * @param filename is the file being read
     */
    public void readFromFile(String filename){
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new FileInputStream(filename));
            while(inputStream.hasNextLine()){
                String fullname = "";
                String username = "";
                String password = "";
                int numBooks = -1;
                int type = -1;
                String title = "";
                String author = "";
                int pubYear = -1;
                String genre = "";
                int rating = -1;
                int length = -1;
                String narrator = "";
                String month = "";
                int day = -1;
                int year = -1;
                String month2 = "";
                int day2 = -1;
                int year2 = -1;
                int start = -1;
                int end = -1;
                String line = "";
                String junk = "";
                ArrayList<Book> books = new ArrayList<Book>();
                Database newBooks = new Database(books);
                //get fullname and keep only stuff in quotes
                line = inputStream.nextLine();
                start = line.indexOf('"') + 1;
                end = line.lastIndexOf('"');
                fullname = line.substring(start, end);
                //get username
                line = inputStream.nextLine();
                start = line.indexOf('"') + 1;
                end = line.lastIndexOf('"');
                username = line.substring(start, end);
                //password
                line = inputStream.nextLine();
                start = line.indexOf('"') + 1;
                end = line.lastIndexOf('"');
                password = line.substring(start, end);
                //num books
                line = inputStream.nextLine();
                start = line.indexOf('"') + 1;
                end = line.lastIndexOf('"');
                numBooks = Integer.parseInt(line.substring(start, end));
                junk = inputStream.nextLine();

                //create database for user
                for(int i = 0; i<numBooks; i ++){
                    //get type and keep only stuff in quotes
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    type = Integer.parseInt(line.substring(start, end));
                    //get title
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    title = line.substring(start, end);
                    //get author
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    author = line.substring(start, end);
                    //get pubyear
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    pubYear = Integer.parseInt(line.substring(start, end));
                    //genre
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    genre = line.substring(start, end);
                    //get rating
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    rating = Integer.parseInt(line.substring(start, end));
                    //get length
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    length = Integer.parseInt(line.substring(start, end));
                    //get narrator
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    narrator = line.substring(start, end);
                    //month
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    month = line.substring(start, end);
                    //day
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    day = Integer.parseInt(line.substring(start, end));
                    //year
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    year = Integer.parseInt(line.substring(start, end));
                    //month2
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    month2 = line.substring(start, end);
                    //day2
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    day2 = Integer.parseInt(line.substring(start, end));
                    //year2
                    line = inputStream.nextLine();
                    start = line.indexOf('"') + 1;
                    end = line.lastIndexOf('"');
                    year2 = Integer.parseInt(line.substring(start, end));
                    try{
                        Date startDate = new Date(month,day,year);
                        Date endDate = new Date(month2,day2,year2);

                        //if it's a typed book...
                        if(type == 1){
                            TypedBook newTyped = new TypedBook(title, author, genre, pubYear, startDate, endDate, rating, length);
                            newBooks.addBook(newTyped);
                        }
                        //if it's an audiobook
                        else if (type == 2){
                            Audiobook newAudio = new Audiobook(title, author, genre, pubYear, startDate, endDate, rating, narrator, length);
                            newBooks.addBook(newAudio);
                        }
                        else{
                            System.out.println("couldn't get book type");
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    junk = inputStream.nextLine();
                }
                junk = inputStream.nextLine();
                try{
                    User newUser = new User(fullname, username, password, newBooks);
                    addUser(newUser);
                }
                catch(Exception ex){
                    System.out.println("couldn't create user");
                }
                
            }    
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }  
    }
}
