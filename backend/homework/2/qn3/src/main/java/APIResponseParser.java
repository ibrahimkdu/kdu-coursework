import java.util.ArrayList;
import java.util.List;

public class APIResponseParser {
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";
        String[] startRulesArray = {"<Author>","<name>"};
        List<String> startRulesArraylist = List.of(startRulesArray);
        String authorname = parse(response,startRulesArraylist, endRule);
        book.setAuthor(authorname);
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);
        startRule="<name>";
        String authorName=parse(response,startRule,endRule);
        book.setAuthor(authorName);
        startRule="<original_publication_year type=\"integer\">";
        int publicationYear=Integer.parseInt(parse(response,startRule,endRule));
        book.setPublicationYear(publicationYear);
        startRule="<average_rating>";
        float averageRating=Float.parseFloat(parse(response,startRule,endRule));
        book.setAverageRating(averageRating);
        startRule="<ratings_count type=\"integer\">";
        String ratings=parse(response,startRule,endRule);
        int ratingCount=Integer.parseInt(ratings.replace(",",""));
        book.setRatingsCount(ratingCount);
        startRule="<image_url>";
        String imageUrl=parse(response,startRule,endRule);
        book.setImageUrl(imageUrl);

// Your code
        return book;
    }
    private static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule) + startRule.length();
        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }
    private static String parse(String response, List<String> startRules, String endRule) {
        int startIndex = response.indexOf(startRules.get(0));
        for (int i = 1; i < startRules.size(); i++) {
            startIndex = response.indexOf(startRules.get(i), startIndex);
        }
        startIndex += startRules.get(startRules.size() - 1).length();

        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }
    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
        Book book = APIResponseParser.parse(response);
        LogBack.slf4jLogger.debug(book.getTitle());
        LogBack.slf4jLogger.debug(book.getAuthor());
        LogBack.slf4jLogger.debug("date of publication"+book.getPublicationYear());
        LogBack.slf4jLogger.debug("average rating"+book.getAverageRating());
        LogBack.slf4jLogger.debug(book.getImageUrl());

//        System.out.println(book.getTitle());
//        System.out.println(book.getAuthor());
//        System.out.println(book.getPublicationYear());
//        System.out.println((book.getAverageRating()));
//        System.out.println(book.getRatingsCount());
//        System.out.println(book.getImageUrl());
        // Print book information
//        logger.slf4jLogger.debug("Book Information:");
//        logger.slf4jLogger.debug("Title: " + book.getTitle());
//        logger.slf4jLogger.debug("Author: " + book.getAuthor());
//        logger.slf4jLogger.debug("Publication Year: " + book.getPublicationYear());
//        logger.slf4jLogger.debug("Average Rating: " + book.getAverageRating());
//        logger.slf4jLogger.debug("Ratings Count: " + book.getRatingsCount());
//        logger.slf4jLogger.debug("Image URL: " + book.getImageUrl());


    }
}
