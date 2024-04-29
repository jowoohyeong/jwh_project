import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataCrawling {

    @Test
    public void dataCrawling () throws IOException {
        try {
            URL url = new URL("https://lol-web-api.op.gg/api/v1.0/internal/bypass/games/kr/summoners/Quk1gs9-EAdv6S51XuSbtKJ3xV4hXd2SH6MgaXOo0BPu5A?&limit=20&hl=ko_KR&game_type=total");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
