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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Class for StatsGUI
 */
public class StatsGUI extends JFrame{
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
    private JTextField ratingOutput;
    private JTextField authorOutput;
    private JTextField lengthOutput;
    private JTextField genreOutput;
    private JTextArea fiveOutput;
    private AllUsers userlist;


    /**
     * Displays add book GUI
     */
    private class addBookListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            AddBookGUI addingGUI = new AddBookGUI(bookList, person, userlist);
            addingGUI.setVisible(true);
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
     * Constructor for StatsGUI
     * @param books is the database the stats are based on
     * @param user is the user associated with the database
     * @param allUsers is the list of all users
     */
    public StatsGUI(Database books, User user, AllUsers allUsers){
        super("Database");
        bookList = books;
        person = user;
        userlist = allUsers;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7,1));

        //menu
        JMenu menu = new JMenu("Menu");

        JMenuItem addBookChoice = new JMenuItem("Add Book");
        addBookChoice.addActionListener(new addBookListener());
        menu.add(addBookChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new searchListener());
        menu.add(searchChoice);

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
        JLabel title = new JLabel("YOUR BOOK STATISTICS");
        add(title);

        //row 2 - average rating
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1,2));
        JLabel rateLabel = new JLabel("Avg Rating:");
        row2.add(rateLabel);
        double rating = bookList.avgRating();
        String ratingS = "";
        if(rating == -1){
            ratingS = "No books in database";
        }
        else{
            ratingS = String.format("%.2f", rating) + " Stars";
        }
        ratingOutput = new JTextField(ratingS);
        ratingOutput.setEditable(false);
        row2.add(ratingOutput);
        add(row2);

        //row 3 - top author
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(1,2));
        JLabel authorLabel = new JLabel("Top Author:");
        row3.add(authorLabel);
        String topAuthor = bookList.topAuthor();
        authorOutput = new JTextField(topAuthor);
        authorOutput.setEditable(false);
        row3.add(authorOutput);
        add(row3);

        //row 4 - average page length
        JPanel row4 = new JPanel();
        row4.setLayout(new GridLayout(1,2));
        JLabel lengthLabel = new JLabel("Average Book Length:");
        row4.add(lengthLabel);
        double length = bookList.avgPageLength();
        String lengthS = "";
        if(length == -1){
            lengthS = "No Typed Books";
        }
        else{
            lengthS = String.format("%.2f", length) + " pages";
        }
        lengthOutput = new JTextField(lengthS);
        lengthOutput.setEditable(false);
        row4.add(lengthOutput);
        add(row4);

        //row 5 - top genre
        JPanel row5 = new JPanel();
        row5.setLayout(new GridLayout(1,2));
        JLabel genreLabel = new JLabel("Top Genre:");
        row5.add(genreLabel);
        String topGenre = bookList.topGenre();
        genreOutput = new JTextField(topGenre);
        genreOutput.setEditable(false);
        row5.add(genreOutput);
        add(row5);

        //row 6
        JLabel fiveLabel = new JLabel("5 Star Reads:");
        add(fiveLabel);

        //row 7
        String bestBooks = bookList.getFiveStars();
        fiveOutput = new JTextArea(15,30);
        fiveOutput.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(fiveOutput);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        fiveOutput.setText(bestBooks);
        add(scrolledText);


    }
}
