import java.util.InputMismatchException;

public class Book {
    /**
     * A long variable that holds the book's ISBN number
     */
    private long ISBN;
    /**
     * int variable hold the book's year of publication
     */
    private int yearPublished;
    /**
     * The String name holds the book's name
     * String author holds the book's author
     * String genre holds the book's genre
     */
    private String name;
    private String author;
    private String genre;
    /**
     * The variable condition of type Condition holds the enum of two constants for the condition of the book
     */
    private Condition condition;
    private enum Condition {
        OLD, NEW;
        }

    public Book(){

    }

    /**
     * This constructor creates a new Book object
     * @param newISBN user inputs the book's ISBN number
     * @param newYearPub user inputs the book's year of publication
     * @param newName user inputs the book's name
     * @param newAuthor user inputs the book's author
     * @param newGenre user inputs the book's genre
     * @param input user inputs a string for the condition that will tell the constructor to set the enum
     */
    public Book(long newISBN, int newYearPub, String newName, String newAuthor, String newGenre, String input){
        this.ISBN = newISBN;
        this.yearPublished = newYearPub;
        this.name = newName;
        this.author = newAuthor;
        this.genre = newGenre;
        if(input.equalsIgnoreCase("New")){
            condition = condition.NEW;
        } else if(input.equalsIgnoreCase("Old")){
            condition = condition.OLD;
        }else{
            throw new InputMismatchException("For condition enter old or new!");
        }
    }

    /**
     * @return method returns the string holding the information of the book
     */
    public String toString(){
        if(condition == condition.OLD){
            return this.name + " was written by " + this.author + " in the year " +
                    this.yearPublished + ". It is of the " + this.genre + " genre. \nThe ISBN number is " +
                    this.ISBN + " and it is old.";
        } else{
            return this.name + " was written by " + this.author + " in the year " +
                    this.yearPublished + ". It is of the " + this.genre + " genre. The ISBN number is " +
                    this.ISBN + " and it is new.";
        }

    }

    /**
     * Mutator method for the enum Condition
     * @param input the method takes in a string and if it is "new" it sets the enum to NEW, and the same for "old"
     */
    public void setCondition(String input){
        if(input.equalsIgnoreCase("New")){
            condition = condition.NEW;
        } else{
            condition = condition.OLD;
        }
    }

    /**
     * @return this getter returns the value of the enum as a String
     */
    public String getCondition(){
        if(condition == condition.OLD){
            return "Old";
        } else{
            return "New";
        }
    }

    /**
     * @return this getter returns the long value of the book's ISBN number
     */
    public long getISBN(){
        return this.ISBN;
    }

    /**
     * @param newISBN the mutator method takes in a long value to set the book's ISBN number
     */
    public void setISBN(long newISBN){
        ISBN = newISBN;
    }

    /**
     * @return this getter method returns the int value of the book's year of publication
     */
    public int getYearPublished(){
        return this.yearPublished;
    }

    /**
     * @param newYear the mutator method takes in an int value to set the book's year of publication
     */
    public void setYearPublished(int newYear){
        yearPublished = newYear;
    }

    /**
     * @return this getter method returns the book's name as a String
     */
    public String getName(){
        return this.name;
    }

    /**
     * @param newName this mutator method takes in a String to set the book's name
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     * @return this getter method returns the book's author as a String
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * @param newAuthor this mutator method takes in a String to set the book's author
     */
    public void setAuthor(String newAuthor){
        author = newAuthor;
    }

    /**
     * @return this getter method returns the book's genre as a String
     */
    public String getGenre(){
        return this.genre;
    }

    /**
     * @param newGenre this mutator method takes in a String to set the book's genre
     */
    public void setGenre(String newGenre){
        genre = newGenre;
    }

    /**
     *
     * @param d first string to compare
     * @param s second string to compare
     * @return an int that tell us if the strings are equal or not
     */
    public int compareTo(String d, String s) {
        String a = d.toLowerCase(); //make sure all letters have the same capitalization
        String b = s.toLowerCase();
        int a1 = a.length();
        int b2 = b.length();
        int min = Math.min(a1, b2);
        for(int i=0; i < min; i++){
            int ch1 = (int)a.charAt(i);
            int ch2 = (int)b.charAt(i);
            if(ch1 != ch2){
                return ch1 - ch2;
            }
        }
        if(a1 != b2){
            return a1-b2;
        }
        else{
            return 0;
        }
    }
}
