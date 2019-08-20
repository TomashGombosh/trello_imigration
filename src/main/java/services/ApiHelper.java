package services;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ApiHelper {

    private static Logger log = Logger.getLogger("log4j.properties");

    protected static JsonObject sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(baseUrl + requestUrl);

        for (NameValuePair header : headers) {
            request.addHeader(header.getName(), header.getValue());
        }

        HttpResponse response = client.execute(request);

        response.getEntity().getContent().toString();
        log.info("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        JsonReader reader = Json.createReader(rd);
        log.info(reader.readObject());
        return reader.readObject();
    }

    protected static StringBuffer sendGetRequest(String baseUrl, String requestUrl) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(baseUrl + requestUrl);

            HttpResponse response = client.execute(request);

            response.getEntity().getContent().toString();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result;
        } catch (IOException e) {
            log.info("Expection Log");
            return null;
        }
    }

    protected static String sendPostRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseUrl + requestUrl);

        // add header
        for (NameValuePair header : headers) {
            post.addHeader(header.getName(), header.getValue());
        }
        post.setEntity(new UrlEncodedFormEntity(requestParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}