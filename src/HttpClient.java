
import org.json.JSONObject;
import sun.applet.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {
    public static void main(String[] args) {
        try {
            HttpClient.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me() throws Exception {
        //GetBookById method 
        String url = "http://localhost:8092/restApi/book-shop/1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print

        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        JSONObject resultData = myResponse.getJSONObject("result");
        System.out.println("id- "+resultData.getString("id"));
        System.out.println("name- "+resultData.getString("name"));
        System.out.println("type- "+resultData.getString("type"));
        System.out.println("page- "+resultData.getInt("page"));
        JSONObject authorData = resultData.getJSONObject("authorResponse");
        //System.out.println("authorResponse- "+myResponse.getString("authorResponse"));
        System.out.println("id- "+authorData.getString("id"));
        System.out.println("author- "+authorData.getString("author"));
        JSONObject statusData = myResponse.getJSONObject("status");
        System.out.println("message- "+statusData.getString("message"));
        System.out.println("code- "+statusData.getInt("code"));
    }
}

