import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataCrawling {

    @Test
    public void dataCrawling () throws IOException {
        String URL = "https://www.op.gg/summoners/kr/50S-1st\n";
        Document doc = Jsoup.connect(URL)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
                .header("authority", "www.op.gg")
                .header("method", "GET")
                .header("path", "/summoners/kr/50S-1st")
                .header("scheme", "https")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("accept-encoding", "gzip, deflate, br, zstd")
                .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("cache-control", "no-cache")
                .header("pragma", "no-cache")
                .header("upgrade-insecure-requests", "1")
                .get();

        System.out.println(doc);

    }
}
