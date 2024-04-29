import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataCrawling2 {

    @Test
    public void dataCrawling () throws IOException {
        int cnt = 0;
        String URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=20190612&page=1";
        Document doc = Jsoup.connect(URL).get();
        Elements elem = doc.select("ul[class=\"sa_list\"]");

        System.out.println(elem);

        for(Element e: elem.select("dt")) {
//            if (e.className().equals("photo")) {
//                continue;
//            }
            System.out.println(e.text());
            cnt++;
        }

        //제목만
        for(Element e: elem.select("Strong")) {
            System.out.println(e.text());
            cnt++;
        }
        System.out.println(cnt);
    }
}
