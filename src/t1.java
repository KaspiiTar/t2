import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class t1 {

    private static String getChapterText(String url) throws Exception{
        url = "https://ficbook.net" + url;
        Document document = Jsoup.connect(url).get();
        return document.select("div#content.jsPartText.part_text.urlize.clearfix.public_beta").text();
    }

    public static void main(String[] args) throws Exception {
        //Document document = new Document("afdf");
        Document document = Jsoup.connect("https://ficbook.net/readfic/9736193").get();

        String fanFicName = document.select("div.fanfic-main-info > h1").text();
        // FileWriter writer = null;
        FileWriter writer = new FileWriter(fanFicName + ".txt");

        List<String> chapterUrls = document.select("div.part-info > a").eachAttr("href");
        for (String chapterUrl : chapterUrls) {
            writer.write(getChapterText(chapterUrl));
        }
        writer.close();
    }
}

