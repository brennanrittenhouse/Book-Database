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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/**
 * Class for NewUserGUI
 */
public class NewUserGUI extends JFrame{
    /**
     * WIDTH is width of the window
     */
    public static final int WIDTH = 900;
    /**
     * HEIGHT is height of the window
     */
    public static final int HEIGHT = 600;
    private AllUsers userList;
    private JTextField fullNameInput;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JButton createUserButton;
    private JTextArea messageArea;


    /**
     * Displays loginGUI when button is pressed
     */
    private class loginListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            LoginGUI loggingGUI = new LoginGUI(userList);
            loggingGUI.setVisible(true);
        }
    }

    /**
     * Attempts to create a user when button is pressed
     */
    private class createUserListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String fullname = fullNameInput.getText();
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            ArrayList<Book> books = new ArrayList<Book>();
            Database personsBooks = new Database(books);
            String output = "";
            if(userList.usernameExists(username)){
                output = "This User Already Exists. Please Login.";
                messageArea.setText(output);
            }
            else{
                try{
                    User newPerson = new User(fullname, username, password, personsBooks);
                    userList.addUser(newPerson);
                    userList.saveUsersAndDatabases("Databases.txt");
                    setVisible(false);
                    SearchGUI searchDisplay = new SearchGUI(personsBooks, newPerson, userList);
                    searchDisplay.setVisible(true);
                }catch (Exception ex){
                    output = ex.getMessage();
                    messageArea.setText(output);
                }
            }
        }
    }

    /**
     * Constructor for NewUserGUI
     * @param users is the list of all users
     */
    public NewUserGUI(AllUsers users){
        super("Create New User");
        userList = users;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));

        //login menu
        JMenu menu = new JMenu("Menu");
        JMenuItem loginChoice = new JMenuItem("Login");
        loginChoice.addActionListener(new loginListener());
        menu.add(loginChoice);
        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);

        //row 1
        JPanel row1 = new JPanel();
        row1.setLayout(new GridLayout(1,2));
        JLabel fullNameLabel = new JLabel("Full Name:");
        row1.add(fullNameLabel);
        fullNameInput = new JTextField(20);
        row1.add(fullNameInput);
        add(row1);


        //row 2
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1,2));
        JLabel usernameLabel = new JLabel("Username:");
        row2.add(usernameLabel);
        usernameInput = new JTextField(20);
        row2.add(usernameInput);
        add(row2);

        //row 3
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(1,2));
        JLabel passwordLabel = new JLabel("Password:");
        row3.add(passwordLabel);
        passwordInput = new JTextField(20);
        row3.add(passwordInput);
        add(row3);

        //create user button
        createUserButton = new JButton("Create User");
        createUserButton.addActionListener(new createUserListener());
        add(createUserButton);

        //message output
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(messageArea);
    }
}
