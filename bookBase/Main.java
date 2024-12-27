package bookBase;

import java.util.ArrayList;
/**
 * Class containing main method
 */
public class Main{
    /**
     * Creates users and databases from text file, display LoginGUI
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        AllUsers userList = new AllUsers(users);

        //create users and databases from text file
        userList.readFromFile("Databases.txt");
        

        LoginGUI login = new LoginGUI(userList);
        login.setVisible(true);
    }
}