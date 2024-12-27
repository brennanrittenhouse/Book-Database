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
 * Class for UpdateRatingsGUI
 */
public class UpdateRatingsGUI extends JFrame{
     /**
     * WIDTH is width of the window
     */
    public static final int WIDTH = 900;
    /**
     * HEIGHT is height of the window
     */
    public static final int HEIGHT = 600;
    private Database bookList;
    private JTextField titleInput;
    private User person;
    private JTextArea resultsArea;
    private JButton updateButton;
    private String ratingStr = "1 Star";
    private AllUsers userlist;

    /**
     * Display stat GUI
     */
    private class statListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            StatsGUI statGUI = new StatsGUI(bookList, person, userlist);
            statGUI.setVisible(true);
        }
    }

    /**
     * Display search GUI
     */
    private class searchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            SearchGUI searchingGUI = new SearchGUI(bookList, person, userlist);
            searchingGUI.setVisible(true);
        }
    }

    /**
     * Display edit user GUI
     */
    private class editUserListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            EditUserGUI editGUI = new EditUserGUI(bookList, person, userlist);
            editGUI.setVisible(true);
        }
    }

    /**
     * Display add book GUI
     */
    private class addBookListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            AddBookGUI addingGUI = new AddBookGUI(bookList, person, userlist);
            addingGUI.setVisible(true);
        }
    }

    /**
     * Get book rating from combo box
     */
    private class ratingListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            ratingStr = (String)cb.getSelectedItem();
        }
    }

    /**
     * Update rating when button is pressed
     */
    public class updateButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int newRating;
            if(ratingStr.equals("1 Star")){
                newRating = 1;
            }
            else if(ratingStr.equals("2 Stars")){
                newRating = 2;
            }
            else if(ratingStr.equals("3 Stars")){
                newRating = 3;
            }
            else if(ratingStr.equals("4 Stars")){
                newRating = 4;
            }
            else if(ratingStr.equals("5 Stars")){
                newRating = 5;
            }
            else{
                newRating = -1;
            }
            String title = "";
            title = titleInput.getText();
            String output = "";
            if(title == null || title.trim().isEmpty()){
                output = "Title cannot be blank";
            }
            //if they own they book, update the reating and output the new book info
            else if(bookList.titleExists(title)){
                bookList.getBook(title).rate(newRating);
                output = bookList.getBook(title).toString();
            }
            else{
                output = "This book isn't in your database";
            }
            System.out.println("output: " + output);
            resultsArea.setText(output);
            userlist.saveUsersAndDatabases("Databases.txt");
        }
    }

    /**
     * Constructor for UpdateRatingsGUI
     * @param books is the database 
     * @param user is the user associated with the database
     * @param allUsers is the list of all users
     */
    public UpdateRatingsGUI(Database books, User user, AllUsers allUsers){
        super("Database");
        bookList = books;
        person = user;
        userlist = allUsers;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1));

        //menu
        JMenu menu = new JMenu("Menu");

        JMenuItem statChoice = new JMenuItem("Stats");
        statChoice.addActionListener(new statListener());
        menu.add(statChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new searchListener());
        menu.add(searchChoice);

        JMenuItem editUserChoice = new JMenuItem("Edit Profile");
        editUserChoice.addActionListener(new editUserListener());
        menu.add(editUserChoice);

        JMenuItem addBookChoice = new JMenuItem("Add Book");
        addBookChoice.addActionListener(new addBookListener());
        menu.add(addBookChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);


        //set window title
        JLabel title = new JLabel("Adding New Book To Your Collection");
        add(title);

        //row 2
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1,2));
        JLabel titleLabel = new JLabel("Title:");
        row2.add(titleLabel);
        titleInput = new JTextField(20);
        row2.add(titleInput);
        add(row2);

        

        //row 5
        JPanel row5 = new JPanel();
        row5.setLayout(new GridLayout(1,2));
        JLabel rateLabel = new JLabel("Updated Rating:");
        row5.add(rateLabel);
        String[] ratingStrings = {"1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars"};
        JComboBox ratingList = new JComboBox(ratingStrings);
        ratingList.setSelectedItem(0);
        ratingList.addActionListener(new ratingListener());
        row5.add(ratingList);

        add(row5);

        //row 6
        updateButton = new JButton("Update");
        updateButton.addActionListener(new updateButtonListener());
        add(updateButton);

        //row 3
        JLabel outputLabel = new JLabel("Results:");
        add(outputLabel);

        //row 4
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(resultsArea);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(resultsArea);
    }
}
