/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Rasmu
 */
public class GetJson {

    public static String getJson(String week, String address) throws ProtocolException, MalformedURLException, IOException {
        URL url = new URL("http://localhost:3333/availableCars?week=" + week + "&comp=hertz&addr=" + address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEERRR");
        System.out.println(url);
        System.out.println(jsonStr);
        return (jsonStr);
    }

    public static String getJsonTwo(String comp, String week, String address) throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:3333/availableCars?week=" + week + "&comp="+comp+"&addr=" + address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        System.out.println("HENTER FRA: "+ comp);

        String inline = "";

        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            System.out.println(inline);
            sc.close();
        }
        return inline;
    }
}
