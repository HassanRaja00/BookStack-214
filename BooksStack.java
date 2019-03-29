import java.util.Scanner;
public class BooksStack {
    /**
     * The Book[] variable is the stack implemented as an array
     */
    private Book[] books;
    /**
     * The int top holds the index of the top of the stack
     * The int capacity holds the total space in the stack
     */
    private int top;
    private int capacity;

    /**
     * The constructor creates an empty stack with the capacity as 2
     * top is initialized as -1 because an empty stack will not have any value for the top of the array
     */
    public BooksStack(){
        this.capacity = 2;
        this.top = -1;
        books = new Book[capacity];

    }

    /**
     * This is the push method for the stack
     * @param newBook takes in a Book parameter to push into the stack of books
     * @throws BookAlreadyExistsException if the book already exists, the method won't push it
     */
    public void push(Book newBook) throws BookAlreadyExistsException{
        if(top != -1){ //checks if book already exists
            for(int i = top; i > -1; i--){
                //check if book already exists by name, author, year, genre, and ISBN
                if(newBook.getAuthor().equals(books[i].getAuthor()) && newBook.getName().equals(books[i].getName())){
                    throw new BookAlreadyExistsException("Book already exists!");
                }
            }
        }
        top++;
        books[top] = newBook;
    }

    /**
     * This is the pop method for the stack
     * @return the popped book
     * @throws EmptyStackException cannot pop an empty stack
     */
    public Book pop() throws EmptyStackException{
        if(top == -1){ //checking for empty stack
            throw new EmptyStackException("Empty stack!");
        }
        Book answer = books[top];
        books[top] = null;
        top--;
        return answer;
    }

    /**
     * This is the peek method for the stack
     * @return the book at the top of the stack
     * @throws EmptyStackException cannot see a book in an empty stack
     */
    public Book peek()throws EmptyStackException{
        if(top == -1){
            throw new EmptyStackException("Empty stack!");
        }
        return books[top];
    }

    /**
     * @return a boolean if the stack is empty or not
     */
    public boolean isEmpty(){
        return (top == -1);
    }

    /**
     * @return the total amount of books in the stack
     */
    public int size(){
        return top+1;
    }

    /**
     * @return a boolean if the stack is full or not
     */
    public boolean isFull(){
        return (top == capacity-1);
    }

    /**
     * Getter method for the stack array
     * @return the array stack
     */
    public Book[] getBooksStack(){
        return this.books;
    }

    /**
     * Creates an array stack with more space for more elements
     * @param input takes in an array stack
     * @param prevCapacity takes in the size of the array stack
     * @return
     */
    public Book[] increaseCapacity(Book[] input, int prevCapacity){
        // creates bigger stack
            Book[] biggerBooks = new Book[prevCapacity*2+1];
            this.books = biggerBooks;

        return this.books;
    }

    /**
     * Sorts the array stack using bubble sort by name
     */
    public void sortByName() {
        Book temp;
        for(int i=0; i<top; i++){
            for(int j=0; j<top-1-i; j++){
                if(books[j+1].compareTo(books[j+1].getName(), books[j].getName()) > 0){
                    temp = books[j];
                    books[j] = books[j+1];
                    books[j+1] = temp;
                }
            }
        }
        System.out.println("The stack has been sorted by name!");
    }

    /**
     * Sorts the array stack using bubble sort by author
     */
    public void sortByAuthor(){
        Book sub;
        for(int i = 0; i < top; i++){
            for(int j = 0; j < top-1-i; j++){
                if(books[j+1].compareTo(books[j+1].getAuthor(), books[j].getAuthor()) > 0){
                    sub = books[j];
                    books[j] = books[j+1];
                    books[j+1] = sub;
                }
            }
        }
        System.out.println("Sorted by author!");
    }

    /**
     * Sorts the array stack using bubble sort by genre
     */
    public void sortByGenre(){
        Book sub;
        for(int i = 0; i < top; i++){
            for(int j = 0; j < top-1-i; j++){
                if(books[j+1].compareTo(books[j+1].getGenre(), books[j].getGenre()) > 0){
                    sub = books[j];
                    books[j] = books[j+1];
                    books[j+1] = sub;
                }
            }
        }
        System.out.println("Sorted by genre!");
    }

    /**
     * Sorts the array stack using bubble sort by year
     */
    public void sortByYear(){
        Book sub;
        for(int i = 0; i < top; i++){
            for(int j = 0; j < top-1-i; j++){
                if(books[j+1].getYearPublished() > books[j].getYearPublished()){
                    sub = books[j];
                    books[j] = books[j+1];
                    books[j+1] = sub;
                }
            }
        }
        System.out.println("Sorted by year published!");
    }

    /**
     * Sorts the array stack using bubble sort by ISBN number
     */
    public void sortByISBN(){
        Book sub;
        for(int i = 0; i < top; i++){
            for(int j = 0; j < top-1-i; j++){
                if(books[j+1].getISBN() > books[j].getISBN()){
                    sub = books[j];
                    books[j] = books[j+1];
                    books[j+1] = sub;
                }
            }
        }
        System.out.println("Sorted by ISBN number!");
    }

    /**
     * Sorts the array stack using bubble sort by its condition
     */
    public void sortByCondition(){
        Book sub;
        for(int i = 0; i < top; i++){
            for(int j = 0; j < top-1-i; j++){
                if(books[j+1].compareTo(books[j+1].getCondition(), books[j].getCondition()) > 0){
                    sub = books[j];
                    books[j] = books[j+1];
                    books[j+1] = sub;
                }
            }
        }
        System.out.println("Sorted by condition!");
    }
}

/**
 * This is the BookAlreadyExistsException
 */
class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message){
        super(message);
    }
}
/**
 * This is the EmptyStackException
 */
class EmptyStackException extends Exception{
    public EmptyStackException(String message){
        super(message);
    }
}