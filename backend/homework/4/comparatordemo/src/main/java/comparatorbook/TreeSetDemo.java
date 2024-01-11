package comparatorbook;
import LogBack.LogBack;

import java.util.*;
public class TreeSetDemo {


    public static Set<Book> treeSetDemo(Comparator comparator) {
        Book book1 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("The Last Lecture", "Randy Pausch", 2008);


        Set<Book> books = new TreeSet<>(comparator);


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        for (Book book : books) {
            LogBack.slf4jLogger.info("book {}",book);
        }
        return books;
    }

    public static void main(String[] args) {
        // Create an instance of TreeSetDemo
        TreeSetDemo.treeSetDemo(null); // Use default sorting
        LogBack.slf4jLogger.debug("Sorted Books using neutral comparator");

        TreeSetDemo.treeSetDemo(new PubDateDescComparator());
        LogBack.slf4jLogger.debug("Sorted Books by Publication date in descending order:");

        TreeSetDemo.treeSetDemo(new PubDateAscComparator());
        LogBack.slf4jLogger.debug("Sorted Books by Publication Date (Ascending):");
    }
}
class Book implements Comparable<Object> {
private int year;
private String title;
private String author;

    public Book(String title, String author,int year) {
        this.year = year;
        this.title = title;
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "year=" + year +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title, author);
    }
    public int compareTo(Object book) {
        return getTitle().compareTo(((Book)book).getTitle()); // utilizing String's compareTo
    }
}
class TitleComparator implements Comparator<Object>  {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
    }
}
class PubDateAscComparator implements Comparator<Object> {

    public int compare(Object o1, Object o2) {
        if (((Book)o1).getYear() == ((Book)o2).getYear()) {
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else if (((Book)o1).getYear() < ((Book)o2).getYear()) {
            return -1;
        } else {
            return 1;
        }
    }
}
class PubDateDescComparator implements Comparator<Object>  {

    public int compare(Object o1, Object o2) {
        if (((Book)o1).getYear() == ((Book)o2).getYear()) {
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else if (((Book)o1).getYear() < ((Book)o2).getYear()) {
            return 1;
        } else {
            return -1;
        }
    }
}
