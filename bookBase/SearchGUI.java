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
 * Class for SearchGUI
 */
public class SearchGUI extends JFrame{
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
    private JTextField authorInput;
    private JTextField pubYearInput;
    private JTextArea resultsArea;
    private JButton search;
    private String genre = "Any Genre";
    private String ratingStr = "";
    private int rating = -1;
    private User person;
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
     * Displays add book GUI
     */
    private class addListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            AddBookGUI addGUI = new AddBookGUI(bookList, person, userlist);
            addGUI.setVisible(true);
        }
    }

    /**
     * Displays edit user GUI
     */
    private class editUserListener implements ActionListener{
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
     * Gets genre from combo box
     */
    private class genreListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            genre = (String)cb.getSelectedItem();
        }
    }

    /**
     * Gets rating from combo box
     */
    private class ratingListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            ratingStr = (String)cb.getSelectedItem();

            
        }
    }

    /**
     * Searches for book when button is pressed
     */
    private class searchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resultsArea.setText("");
            String title = "";
            String author = "";
            String pubYearS = "";
            int pubYear = -1;
            String output = "";
            
            if(ratingStr.equals("1 Star")){
                rating = 1;
            }
            else if(ratingStr.equals("2 Stars")){
                rating = 2;
            }
            else if(ratingStr.equals("3 Stars")){
                rating = 3;
            }
            else if(ratingStr.equals("4 Stars")){
                rating = 4;
            }
            else if(ratingStr.equals("5 Stars")){
                rating = 5;
            }
            else{
                rating = -1;
            }
            
            title = titleInput.getText();
            author = authorInput.getText();
            pubYearS = pubYearInput.getText();
            if(pubYearS == null || pubYearS.trim().isEmpty()){
                output = bookList.search(title, author, genre, -1, rating);
            }
            else{
                try{
                    pubYear = Integer.parseInt(pubYearS);
                    output = bookList.search(title, author, genre, pubYear, rating);
                }catch(NumberFormatException ex){
                    output = "Please enter an int or leave publication year blank";
                    System.out.println(output);
                }
            }
            resultsArea.setText(output);

            

        }
    }

  
    /**
     * Constructor for SearchGUI
     * @param books is the database to be searched
     * @param user is the user associated with the database
     * @param allusers is the list of all users
     */
    public SearchGUI(Database books, User user, AllUsers allusers){
        super("Database");
        bookList = books;
        person = user;
        userlist = allusers;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));

        //menu
        JMenu menu = new JMenu("Menu");

        JMenuItem statChoice = new JMenuItem("Stats");
        statChoice.addActionListener(new statListener());
        menu.add(statChoice);

        JMenuItem addChoice = new JMenuItem("Add Book");
        addChoice.addActionListener(new addListener());
        menu.add(addChoice);

        JMenuItem editUserChoice = new JMenuItem("Edit Profile");
        editUserChoice.addActionListener(new editUserListener());
        menu.add(editUserChoice);

        JMenuItem updateRateChoice = new JMenuItem("Update Ratings");
        updateRateChoice.addActionListener(new updateRatingsListener());
        menu.add(updateRateChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);


        //set window title
        JLabel title = new JLabel("Searching Your Book DataBase");
        add(title);

        //row 2 (two columns)
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1,2));


        //row2col1 - labels and inputs
        JPanel row2col1 = new JPanel();
        row2col1.setLayout(new GridLayout(5,2));

        JLabel titleLabel = new JLabel("Title:");
        row2col1.add(titleLabel);
        titleInput = new JTextField(20);
        row2col1.add(titleInput);

        JLabel authorLabel = new JLabel("Author:");
        row2col1.add(authorLabel);
        authorInput = new JTextField(20);
        row2col1.add(authorInput);

        JLabel genreLabel = new JLabel("Genre:");
        row2col1.add(genreLabel);
        String[] genreStrings = {"Any Genre", "Fiction", "Non-Fiction", "Fantasy", "Horror", "Romance", "Classics"};
        JComboBox genreList = new JComboBox(genreStrings);
        genreList.setSelectedItem(0);
        genreList.addActionListener(new genreListener());
        row2col1.add(genreList);
        
        JLabel pubLabel = new JLabel("Publication Year:");
        row2col1.add(pubLabel);
        pubYearInput = new JTextField(20);
        row2col1.add(pubYearInput);

        JLabel ratingLabel = new JLabel("Your Rating:");
        row2col1.add(ratingLabel);
        String[] ratingStrings = {"Any Rating", "1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars"};
        JComboBox ratingList = new JComboBox(ratingStrings);
        ratingList.setSelectedItem(0);
        ratingList.addActionListener(new ratingListener());
        row2col1.add(ratingList);

        row2.add(row2col1);

        search = new JButton("Search");
        search.addActionListener(new searchListener());
        row2.add(search);

        add(row2);

        JLabel resultLabel = new JLabel("Results");
        add(resultLabel);
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(resultsArea);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrolledText);

    }
}
