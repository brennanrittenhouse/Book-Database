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
 * Class for adding book GUI
 */
public class AddBookGUI extends JFrame{
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
    private JButton addButton;
    private String genre = "Fiction";
    private String ratingStr = "1 Star";
    private int rating = -1;
    private JTextField startDayInput;
    private JTextField endDayInput;
    private JTextField startYearInput;
    private JTextField endYearInput;
    private String startMonth = "Jan";
    private String endMonth = "Jan";
    private String type = "Printed Book";
    private JTextField lengthInput;
    private JTextField narratorInput;
    private User person;
    private AllUsers userlist;

    /**
     * Displays stats GUI
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
     * Displays update rating GUI
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
     * Gets start month from combo box
     */
    private class startMonthListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            startMonth = (String)cb.getSelectedItem();
        }
    }

    /**
     * Gets end month from combo box
     */
    private class endMonthListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            endMonth = (String)cb.getSelectedItem();
        }
    }

    /**
     * Get book type from combo box
     */
    private class typeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //GET TYPE FROM COMBO BOX HERE
            JComboBox cb = (JComboBox)e.getSource();
            type = (String)cb.getSelectedItem();
        }
    }

    /**
     * Adds a book to database when button is pressed
     */
    private class addBookListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resultsArea.setText("");
            String title = "";
            String author = "";
            String pubYearS = "";
            int pubYear = -1;
            String startDayS = "";
            String startYearS = "";
            String endDayS = "";
            String endYearS = "";
            String output = "";
            String narrator = "";
            String lengthS = "";

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
            startDayS = startDayInput.getText();
            startYearS = startYearInput.getText();
            endDayS = endDayInput.getText();
            endYearS = endYearInput.getText();
            lengthS = lengthInput.getText();
            narrator = narratorInput.getText();

            try{
                pubYear = Integer.parseInt(pubYearS);
                int startDay = Integer.parseInt(startDayS);
                int startYear = Integer.parseInt(startYearS);
                int endDay = Integer.parseInt(endDayS);
                int endYear = Integer.parseInt(endYearS);
                int length = Integer.parseInt(lengthS);
                try{
                    Date startDate = new Date(startMonth, startDay, startYear);
                    Date endDate = new Date(endMonth, endDay, endYear);
                    // {"Printed Book", "Audiobook"};
                    if(type.equals("Audiobook")){
                        Audiobook newAudio = new Audiobook(title, author, genre, pubYear, startDate, endDate, rating, narrator, length);
                        //test prints
                        System.out.println("BEFORE:");
                        bookList.printAll();
                        bookList.addBook(newAudio);
                        output = "Added Successfully";
                        System.out.println("AFTER:");
                        bookList.printAll();

                    }
                    else{
                        TypedBook newTyped = new TypedBook(title, author, genre, pubYear, startDate, endDate, rating, length);
                        //test prints
                        System.out.println("BEFORE:");
                        bookList.printAll();
                        bookList.addBook(newTyped);
                        output = "Added Successfully";
                        System.out.println("AFTER:");
                        bookList.printAll();
                    }
                    resultsArea.setText(output);
                    userlist.saveUsersAndDatabases("Databases.txt");

                }catch (Exception ex){
                    output = ex.getMessage();
                    resultsArea.setText(output);
                }
                
                
                
            }catch(NumberFormatException ex){
                output = "Dates and lengths must be integers";
                resultsArea.setText(output);
            }

        }
    }

    /**
     * Constructor for AddBookGUI
     * @param books is the database to be added to
     * @param user is the user whose data is being edited
     * @param allUsers is a collection of all users
     */
    public AddBookGUI(Database books, User user, AllUsers allUsers){
        super("Database");
        bookList = books;
        person = user;
        userlist = allUsers;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));

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

        JMenuItem updateRateChoice = new JMenuItem("Update Ratings");
        updateRateChoice.addActionListener(new updateRatingsListener());
        menu.add(updateRateChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);


        //set window title
        JLabel title = new JLabel("Adding New Book To Your Collection");
        add(title);

        //row 2 (two columns)
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1,2));

        //row2col1 - labels and inputs
        JPanel row2col1 = new JPanel();
        row2col1.setLayout(new GridLayout(8,2));

        JLabel typeLabel = new JLabel("Type:");
        row2col1.add(typeLabel);
        String[] typeStrings = {"Printed Book", "Audiobook"};
        JComboBox typeList = new JComboBox(typeStrings);
        typeList.setSelectedItem(0);
        typeList.addActionListener(new typeListener());
        row2col1.add(typeList);

        JLabel titleLabel = new JLabel("Title:");
        row2col1.add(titleLabel);
        titleInput = new JTextField(20);
        row2col1.add(titleInput);

        JLabel authorLabel = new JLabel("Author:");
        row2col1.add(authorLabel);
        authorInput = new JTextField(20);
        row2col1.add(authorInput);

        JLabel narLabel = new JLabel("Narrator:");
        row2col1.add(narLabel);
        narratorInput = new JTextField(20);
        row2col1.add(narratorInput);


        JLabel genreLabel = new JLabel("Genre:");
        row2col1.add(genreLabel);
        String[] genreStrings = {"Fiction", "Non-Fiction", "Fantasy", "Horror", "Romance", "Classics"};
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
        String[] ratingStrings = {"1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars"};
        JComboBox ratingList = new JComboBox(ratingStrings);
        ratingList.setSelectedItem(0);
        ratingList.addActionListener(new ratingListener());
        row2col1.add(ratingList);

        JLabel lengthLabel = new JLabel("Length:");
        row2col1.add(lengthLabel);
        lengthInput = new JTextField(20);
        row2col1.add(lengthInput);

        row2.add(row2col1);

        //add button
        addButton = new JButton("Add Book");
        addButton.addActionListener(new addBookListener());
        row2.add(addButton);

        add(row2);

        //row 3
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(2,7));

        JLabel startLabel = new JLabel("Start Date:");
        row3.add(startLabel);

        JLabel monthLabel = new JLabel("Month");
        row3.add(monthLabel);
        String[] monthStrings = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        JComboBox startMonthList = new JComboBox(monthStrings);
        startMonthList.setSelectedItem(0);
        startMonthList.addActionListener(new startMonthListener());
        row3.add(startMonthList);

        JLabel dayLabel = new JLabel("Day");
        row3.add(dayLabel);
        startDayInput = new JTextField(20);
        row3.add(startDayInput);

        JLabel yearLabel = new JLabel("Year");
        row3.add(yearLabel);
        startYearInput = new JTextField(20);
        row3.add(startYearInput);

        JLabel endLabel = new JLabel("End Date:");
        row3.add(endLabel);

        JLabel monthLabel2 = new JLabel("Month");
        row3.add(monthLabel2);
        JComboBox endMonthList = new JComboBox(monthStrings);
        endMonthList.setSelectedItem(0);
        endMonthList.addActionListener(new endMonthListener());
        row3.add(endMonthList);

        JLabel dayLabel2 = new JLabel("Day");
        row3.add(dayLabel2);
        endDayInput = new JTextField(20);
        row3.add(endDayInput);

        JLabel yearLabel2 = new JLabel("Year");
        row3.add(yearLabel2);
        endYearInput = new JTextField(20);
        row3.add(endYearInput);

        add(row3);


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
