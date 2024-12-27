package bookBase;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Class for LoginGUI
 */
public class LoginGUI extends JFrame{
    /**
     * WIDTH is width of the window
     */
    public static final int WIDTH = 900;
    /**
     * HEIGHT is height of the window
     */
    public static final int HEIGHT = 600;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JButton loginButton;
    private JButton createUser;
    private JTextArea errorMessageArea;
    private AllUsers userList;



    /**
     * Attempts to login when button is pressed
     */
    private class loginListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET USERNAME & PASSWORD
            errorMessageArea.setText("");
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String error = "";

            //make sure username exists
            if(!userList.usernameExists(username)){
                error = "User doesn't exist. Create new user instead.";
            }
            
            //check if password is correct
            else if(userList.correctPassword(username, password) == -1){
                error = "Incorrect password";
            }
        
            //if username and password match, go to the user's data
            else{
                Database personsBooks = userList.login(username, password);
                User person = userList.getUser(username);
                setVisible(false);
                SearchGUI searchDisplay = new SearchGUI(personsBooks, person, userList);
                searchDisplay.setVisible(true);
            }
            errorMessageArea.setText(error);
        }
    }

    /**
     * Displays create user GUI when button is pressed
     */
    private class createUserListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            NewUserGUI createUserDisplay = new NewUserGUI(userList);
            createUserDisplay.setVisible(true);
        }
    }

    /**
     * Constructor for login GUI
     * @param users is the list of all users
     */
    public LoginGUI(AllUsers users){
        super("LOGIN");
        userList = users;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));

        //top row
        JPanel row1 = new JPanel();
        row1.setLayout(new GridLayout(2,2));

        JLabel usernLabel = new JLabel("Username:");
        row1.add(usernLabel);
        usernameInput = new JTextField(25);
        row1.add(usernameInput);

        JLabel pwordLabel = new JLabel("Password:");
        row1.add(pwordLabel);
        passwordInput = new JTextField(25);
        row1.add(passwordInput);

        add(row1);



        //other rows
        loginButton = new JButton("Login");
        loginButton.addActionListener(new loginListener());
        add(loginButton);

        JLabel or = new JLabel("OR");
        add(or);

        createUser = new JButton("Create New User");
        createUser.addActionListener(new createUserListener());
        add(createUser);

        errorMessageArea = new JTextArea();
        errorMessageArea.setEditable(false);
        add(errorMessageArea);

    }
}
