package services.helper;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public abstract class ApiHelper {
    private static Logger log;

    protected ApiHelper(){
        log = Logger.getLogger(this.getClass().getCanonicalName());
    }

    protected static String sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl){
        try {
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

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e){
            return null;
        }
    }

    protected static String sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl,  List<NameValuePair> requestParameters){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(baseUrl + requestUrl);

            for (NameValuePair header : headers) {
                request.addHeader(header.getName(), header.getValue());
            }

            URI uri = null;
            try {
                uri = new URIBuilder(request.getURI()).addParameters(requestParameters).build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            request.setURI(uri);

            HttpResponse response = client.execute(request);

            response.getEntity().getContent().toString();
            log.info("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e){
            return null;
        }
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
            log.info("Exception Log");
            return null;
        }
    }

    protected static String sendPostRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        try {


            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(baseUrl + requestUrl);

            // add header
            for (NameValuePair header : headers) {
                post.addHeader(header.getName(), header.getValue());
            }
            post.setEntity(new UrlEncodedFormEntity(requestParameters,  "utf-8"));

            HttpResponse response = client.execute(post);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e){
            log.info(e);
        }
        return null;
    }
}