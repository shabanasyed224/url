import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private final Map<String, String> urlMap;
    private final String baseUrl;
    private final String characters;
    private final Random random;
    private final int shortUrlLength;

    public LinkShortener(String baseUrl, int shortUrlLength) {
        this.urlMap = new HashMap<>();
        this.baseUrl = baseUrl;
        this.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        this.random = new Random();
        this.shortUrlLength = shortUrlLength;
    }

    public String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        urlMap.put(shortUrl, originalUrl);
        return baseUrl + shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return urlMap.get(shortUrl.replace(baseUrl, ""));
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < shortUrlLength; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    public static void main(String[] args) {
        LinkShortener shortener = new LinkShortener("http://short.url/", 6);
        
        String originalUrl = "https://www.example.com/some/very/long/url";
        String shortUrl = shortener.shortenUrl(originalUrl);
        
        System.out.println("Original URL: " + originalUrl);
        System.out.println("Shortened URL: " + shortUrl);
        
        String retrievedUrl = shortener.getOriginalUrl(shortUrl);
        System.out.println("Retrieved URL: " + retrievedUrl);
    }
}