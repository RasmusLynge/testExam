package fetch;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import net.minidev.json.JSONObject;

//Perhaps this you go into a separate file
class HTTPFetcher implements Callable<String> {

    String url;

    HTTPFetcher(String url) {
        this.url = url;
    }

    public String getData() throws MalformedURLException, IOException {
        URL url = new URL(this.url);
        System.out.println("url  " + url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());

        //Kan læse normal url api
        /*String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
         */
        //Hvis json ikke kan læses på engang f.eks på en local json delpoyet server
        String jsonStr = "";
        while (scan.hasNext()) {
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr.replaceAll("\\s+", "");
    }

    @Override
    public String call() throws Exception {
        return getData();
    }
}

public class ParallelPinger {

    //Which urls it should use for thread pool
    private static List<String> urlAddress = Arrays.asList(
            "http://localhost:3333/availableCars?comp=avis&",
            "http://localhost:3333/availableCars?comp=hertz&",
            "http://localhost:3333/availableCars?comp=europcar&",
            "http://localhost:3333/availableCars?comp=budget&",
            "http://localhost:3333/availableCars?comp=alamo&");

    public static String getJsonFromAllServers(String param) throws Exception {
        //Starting thread pools needed for the job
        ExecutorService executor = Executors.newCachedThreadPool();

        //Adding furtures
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < urlAddress.size(); i++) {
            Callable<String> pingUrlCallable = new HTTPFetcher(urlAddress.get(i) + param);
            Future<String> fut = executor.submit(pingUrlCallable);
            futures.add(fut);
        }

        //Return of the promises
        List<String> results = new ArrayList();
        for (Future<String> future : futures) {
            String result = future.get(2, TimeUnit.MINUTES);
            results.add(result);
        }
        //Stopping the threadpool
        executor.shutdown();

        //Beautify json
        String jsonString = "";
        for (String r : results) {
            jsonString += r;
        }
        return jsonString;
    }

    public static void main(String[] args) throws Exception {
        /* long timeStart = System.nanoTime();
        List<String> results = getJsonFromAllServers("people");
        String jsonString = "[";
        for (String r : results) {
            jsonString += r;
        }
        jsonString += "]";

        long timeEnd = System.nanoTime();
        long total = (timeEnd - timeStart) / 1_000_000;
        System.out.println("Time to check URLS: " + total + "ms.");*/
    }
}