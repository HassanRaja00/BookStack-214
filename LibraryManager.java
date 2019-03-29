import java.util.InputMismatchException;
import java.util.Scanner;
public class LibraryManager {

    public static void main(String[] args){
        BooksStack stackingBooks = new BooksStack(); //creates a new empty stack
        Scanner input = new Scanner(System.in);
        String initialStatement = "(A) - Add book\n(R) - Remove book\n(G) - See book\n(P) - Print books\n(S) - Sort books\n(Q) - Quit";
        boolean keepGoing = true;
        while(keepGoing){ //this keeps the program running after a task is completed to do more tasks
            System.out.println("\nMenu:\n" + initialStatement);
            String choice = input.nextLine();
            switch(choice) { //the different choices do different tasks
                case "A":
                    //add
                    try {
                        System.out.print("Please enter the name: ");
                        String name = input.nextLine();
                        System.out.print("Please enter the author: ");
                        String author = input.nextLine();
                        System.out.print("Please enter the genre: ");
                        String genre = input.nextLine();
                        System.out.print("Please enter the year published: ");
                        int year = Integer.parseInt(input.nextLine());
                        System.out.print("Please enter ISBN number: ");
                        long ISBN = Long.parseLong(input.nextLine());
                        System.out.print("Please enter the condition: ");
                        String bookCondition = input.nextLine();
                        try {
                            //creates a bigger stack if full before adding
                            if (stackingBooks.isFull()) {
                                BooksStack biggerStack = new BooksStack();
                                biggerStack.increaseCapacity(stackingBooks.getBooksStack(), stackingBooks.size());
                                while (!stackingBooks.isEmpty()) {
                                    biggerStack.push(stackingBooks.pop());
                                }
                                stackingBooks = biggerStack;
                            }
                            stackingBooks.push(new Book(ISBN, year, name, author, genre, bookCondition));
                            System.out.println(name + " has been added!");
                        } catch (BookAlreadyExistsException ex) {
                            System.out.println(ex.getMessage() + "\n");
                        } catch (EmptyStackException ex2) {
                            System.out.println(ex2.getMessage());
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("The input you entered is incorrect. Try again\n");
                    } catch (IllegalArgumentException ex2) {
                        System.out.println("Error! Try again!\n");
                    }

                    break;
                case "R":
                    //remove
                    try {
                        System.out.print("Please enter the name of book: ");
                        String removeBook = input.nextLine();
                        BooksStack removeStack = new BooksStack();
                        removeStack.increaseCapacity(stackingBooks.getBooksStack(), stackingBooks.size());

                        while(!(stackingBooks.isEmpty())){ //pops into temp stack
                            Book current_book = stackingBooks.peek();
                            if(current_book.getName().equals(removeBook)){ //if the book is found, pop it
                                stackingBooks.pop();
                                System.out.println("Book removed!");
                                while(!removeStack.isEmpty()){ //pops back into original stack
                                    stackingBooks.push(removeStack.pop());
                                }
                                break;
                            } else{
                                removeStack.push(stackingBooks.pop());
                            }
                        }
                        if(stackingBooks.isEmpty()){ //if books is not found when stack is empty, pop back into original
                            System.out.println("Book not in stack!");
                            while(!removeStack.isEmpty()){
                                stackingBooks.push(removeStack.pop());
                            }
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("The input you entered is incorrect. Try again\n");
                    } catch (IllegalArgumentException ex2) {
                        System.out.println("Error! Try again!\n");
                    } catch(EmptyStackException ex3){
                        System.out.println(ex3.getMessage());
                    } catch(BookAlreadyExistsException ex4){
                        System.out.println(ex4.getMessage());
                    }
                    break;
                case "G":
                    //get
                    try {
                        System.out.println("Please enter the name of the book: ");
                        String getBook = input.nextLine();
                        BooksStack holdStack = new BooksStack();
                        holdStack.increaseCapacity(stackingBooks.getBooksStack(), stackingBooks.size());

                        while (!(stackingBooks.isEmpty())) { //pops into temp stack
                            Book current_book = stackingBooks.peek();
                            if (current_book.getName().equals(getBook)){ //if the book is found, print the toString
                                System.out.println(stackingBooks.peek().toString());
                                while(!holdStack.isEmpty()){ //pops back into original stack
                                    stackingBooks.push(holdStack.pop());
                                }
                                break;
                            }else{
                                    holdStack.push(stackingBooks.pop());
                            }
                        }
                        if(stackingBooks.isEmpty()){ //if the book isn't found when the stack is empty, pop back into original stack
                            System.out.println("Book not in stack!");
                            while(!holdStack.isEmpty()){
                                stackingBooks.push(holdStack.pop());
                            }
                        }

                    }catch(EmptyStackException ex){
                        System.out.println(ex.getMessage());
                    } catch(BookAlreadyExistsException ex2){
                        System.out.println(ex2.getMessage());
                    }

                    break;
                case "P":
                    //print
                    if (stackingBooks.isEmpty()) {
                        System.out.println("Cannot print empty stack!\n");
                    } else {
                        try {
                            System.out.print("          Name             |   Author          |   Genre       |   Year   |        ISBN        | Condition\n");
                            System.out.print("==========================================================================================================\n");
                            BooksStack printer = new BooksStack();
                            printer.increaseCapacity(stackingBooks.getBooksStack(), stackingBooks.size());
                            while (!stackingBooks.isEmpty()) {
                                System.out.print("   " + stackingBooks.peek().getName() + "       |");
                                System.out.print("   " + stackingBooks.peek().getAuthor() + "      |");
                                System.out.print("   " + stackingBooks.peek().getGenre() + "       |");
                                System.out.print("   " + stackingBooks.peek().getYearPublished() + "   |");
                                System.out.print("   " + stackingBooks.peek().getISBN() + "    |");
                                System.out.print(" " + stackingBooks.peek().getCondition() + "\n");
                                //pop into temp stack
                                printer.push(stackingBooks.pop());
                            }
                            while (!printer.isEmpty()) {
                                //pop back to original stack
                                stackingBooks.push(printer.pop());
                            }
                        } catch (EmptyStackException ex) {
                            System.out.println(ex.getMessage() + "\n");
                        } catch (BookAlreadyExistsException ex2) {
                            System.out.println(ex2.getMessage() + "\n");
                        }
                    }
                    break;
                case "S":
                    //sort
                    if(stackingBooks.isEmpty()){
                        System.out.println("cannot sort empty stack!");
                    } else {
                        boolean correct = false;
                        System.out.print("Please select by what: \n");
                        System.out.print("(N) - Name\n(A) - Author\n(G) - Genre\n(Y) - Year\n(C) - Condition\n(I) - ISBN Number\n");
                        String option = input.nextLine();
                        while (!correct) {
                            switch (option) {
                                case "N":
                                    stackingBooks.sortByName();
                                    correct = true;
                                    break;
                                case "A":
                                    stackingBooks.sortByAuthor();
                                    correct = true;
                                    break;
                                case "G":
                                    stackingBooks.sortByGenre();
                                    correct = true;
                                    break;
                                case "Y":
                                    stackingBooks.sortByYear();
                                    correct = true;

                                    break;
                                case "C":
                                    stackingBooks.sortByCondition();
                                    correct = true;
                                    break;
                                case "I":
                                    stackingBooks.sortByISBN();
                                    correct = true;
                                    break;
                                default:
                                    System.out.println("Incorrect input! Try again!\n");
                            }
                        }
                    }
                        break;
                        case "Q":
                            System.out.print("Sorry to see you go!");
                            input.close();
                            System.exit(0);
                        default:
                            System.out.println("Wrong input. Try again!");


            }
        }
    }
}
