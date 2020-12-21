package pack;

import java.io.*;
import java.util.*;

/**
 *
 * @author Haruaki Tamada
 */
public class LibraryUtil{
    
    private UserManager manager = new UserManager();

    public List<Book> readFromFile(String fileName){
        List<Book> books = new ArrayList<Book>();
        try(BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "utf-8"))){
            String line;
            while((line = in.readLine()) != null){
                line = line.trim();
                if(Objects.equals(line, "") || line.startsWith("#")){
                    continue;
                }
                String[] data = line.split(",");
                Book book = this.createBook(data[0], data[1], data[2], data[3]);
                books.add(book);
            }
        } catch(IOException e){
            e.printStackTrace();
            // ignore
        }
        return Collections.unmodifiableList(books);
    }

    public List<User> readFromUsersfile(String fileName){
        List<User> users = new ArrayList<User>();
        try(BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "utf-8"))){
            String line;
            while((line = in.readLine()) != null){
                line = line.trim();
                if(Objects.equals(line, "") || line.startsWith("#")){
                    continue;
                }
                String[] data = line.split(",");
                User user = manager.create(data[0], data[1], Integer.parseInt(data[2]));
                users.add(user);
            }
        } catch(IOException e){
            e.printStackTrace();
            // ignore
        }
        return Collections.unmodifiableList(users);
    }

    public Book createBook(String title, String authors, String publisher, String publishYear){
        return this.createBook(title, authors, publisher, Integer.valueOf(publishYear));
    }

    public Book createBook(String title, String authors, String publisher, Integer publishYear){
        Book book = new Book();
        book.title = title;
        book.authors = authors;
        book.publisher = publisher;
        book.publishYear = publishYear;

        return book;
    }
}