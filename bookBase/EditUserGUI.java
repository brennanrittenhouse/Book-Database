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
 * Class for EditUserGUI
 */
public class EditUserGUI extends JFrame{
     /**
     * WIDTH is width of the window
     */
    public static final int WIDTH = 900;
    /**
     * HEIGHT is height of the window
     */
    public static final int HEIGHT = 600;
    private Database bookList;
    private User person;
    private JTextField fullnameInput;
    private JTextField passwordInput;
    private JButton update;
    private JTextArea messageArea;
    private AllUsers userlist;

    /**
     * Displays stat GUI
     */
    private class statListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            StatsGUI statGUI = new StatsGUI(bookList, person, userlist);
            statGUI.setVisible(true);
        }
    }

    /**
     * Displays search GUI
     */
    private class searchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            SearchGUI searchingGUI = new SearchGUI(bookList, person, userlist);
            searchingGUI.setVisible(true);
        }
    }

    /**
     * Displays add book GUI
     */
    private class addBookListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            EditUserGUI editGUI = new EditUserGUI(bookList, person, userlist);
            editGUI.setVisible(true);
        }
    }

    /**
     * Displays update ratings GUI
     */
    private class updateRatingsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            UpdateRatingsGUI updateGUI = new UpdateRatingsGUI(bookList, person, userlist);
            updateGUI.setVisible(true);
        }
    }

    /**
     * Updates user info when button is pressed
     */
    private class updateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String fullname = "";
            String password = "";
            String output = "";
            fullname = fullnameInput.getText();
            password = passwordInput.getText();
            try{
                person.setFullname(fullname);
            }catch(Exception ex){
                output = ex.getMessage();
            }
            
            try{
                person.setPassword(password);
            }catch(Exception ex){
                output = ex.getMessage();
            }

            if(output == null || output.trim().isEmpty()){
                output = "Updated User:\n" + person.toString();
            }
            messageArea.setText(output);
            userlist.saveUsersAndDatabases("Databases.txt");
        }
    }

    /**
     * Constructor for EditUserGUI
     * @param books is the database associated with the user
     * @param user is the user being edited
     * @param allUsers is the collection of all users
     */
    public EditUserGUI(Database books, User user, AllUsers allUsers){
        super("Database");
        bookList = books;
        person = user;
        userlist = allUsers;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));

        //menu
        JMenu menu = new JMenu("Menu");

        JMenuItem statChoice = new JMenuItem("Stats");
        statChoice.addActionListener(new statListener());
        menu.add(statChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new searchListener());
        menu.add(searchChoice);

        JMenuItem addBookChoice = new JMenuItem("Add Book");
        addBookChoice.addActionListener(new addBookListener());
        menu.add(addBookChoice);

        JMenuItem updateRateChoice = new JMenuItem("Update Ratings");
        updateRateChoice.addActionListener(new updateRatingsListener());
        menu.add(updateRateChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);


        //set window title
        JLabel title = new JLabel("Editing User Info");
        add(title);

        //row 2 (two columns)
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(2,2));
        JLabel nameLabel = new JLabel("Full Name:");
        row2.add(nameLabel);
        fullnameInput = new JTextField(user.getFullname());
        row2.add(fullnameInput);
        JLabel passLabel = new JLabel("Password:");
        row2.add(passLabel);
        passwordInput = new JTextField(user.getPassword());
        row2.add(passwordInput);

        add(row2);

        update = new JButton("Update");
        update.addActionListener(new updateListener());
        add(update);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(messageArea);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrolledText);

        
    }
}
