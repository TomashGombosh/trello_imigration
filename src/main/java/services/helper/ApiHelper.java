package services.helper;

import net.jcip.annotations.NotThreadSafe;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public abstract class ApiHelper {
    private static Logger log;
    private HttpResponse response;
    private HttpClient client;

    protected ApiHelper() {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
        log = Logger.getLogger(this.getClass().getCanonicalName());
        client = HttpClientBuilder.create().build();

    }

    @NotThreadSafe
    class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public String getMethod() {
            return METHOD_NAME;
        }

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public HttpDeleteWithBody(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpDeleteWithBody() {
            super();
        }
    }

    protected String sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(baseUrl + requestUrl);

            for (NameValuePair header : headers) {
                request.addHeader(header.getName(), header.getValue());
            }

            response = client.execute(request);

            response.getEntity().getContent().toString();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            return null;
        }
    }

    protected String sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        try {

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
        } catch (IOException e) {
            return null;
        }
    }

    protected StringBuffer sendGetRequest(String baseUrl, String requestUrl) {
        try {
            HttpGet request = new HttpGet(baseUrl + requestUrl);

            client.execute(request);

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

    protected String sendPostRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        try {

            HttpPost post = new HttpPost(baseUrl + requestUrl);

            // add header
            for (NameValuePair header : headers) {
                post.addHeader(header.getName(), header.getValue());
            }
            post.setEntity(new UrlEncodedFormEntity(requestParameters, "utf-8"));

            client.execute(post);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            log.info(e);
        }
        return null;
    }

    protected String sendPutRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        try {

            HttpPut put = new HttpPut(baseUrl + requestUrl);

            // add header
            for (NameValuePair header : headers) {
                put.addHeader(header.getName(), header.getValue());
            }
            put.setEntity(new UrlEncodedFormEntity(requestParameters, "utf-8"));

            HttpResponse response = client.execute(put);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            log.info(e);
        }
        return null;
    }

    protected String[] sendDeleteRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, String requestParameters) {
        try {
            String URL = baseUrl + requestUrl;
            String[] restResponse = new String[2];
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(URL);
            StringEntity input = new StringEntity(requestParameters, ContentType.APPLICATION_JSON);

            for (NameValuePair header : headers) {
                httpDelete.addHeader(header.getName(), header.getValue());
            }
            httpDelete.setEntity(input);

            Header requestHeaders[] = httpDelete.getAllHeaders();
            CloseableHttpResponse response = httpclient.execute(httpDelete);
            restResponse[0] = Integer.toString((response.getStatusLine().getStatusCode()));
            restResponse[1] = EntityUtils.toString(response.getEntity());
            return restResponse;
        } catch (IOException e) {
            log.info(e);
        }
        return null;
    }

    private void requestBuilder() {

    }
}