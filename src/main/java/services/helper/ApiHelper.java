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
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
        private static final String METHOD_NAME = "DELETE";

        public String getMethod() {
            return METHOD_NAME;
        }

        private HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }
    }

    protected String sendGetRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        HttpGet get = new HttpGet(baseUrl + requestUrl);
        setHeaders(get, headers);
        get.setURI(buildURl(get, requestParameters));
        response = execute(get);
        return convertResponseToString(response);
    }

    protected String sendPostRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        HttpPost post = new HttpPost(baseUrl + requestUrl);
        setHeaders(post, headers);
        setEntity(post, requestParameters);
        response = execute(post);
        return convertResponseToString(response);
    }

    protected String sendPutRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        HttpPut put = new HttpPut(baseUrl + requestUrl);
        setHeaders(put, headers);
        setEntity(put, requestParameters);
        response = execute(put);
        return convertResponseToString(response);
    }

    protected String sendDeleteRequest(String baseUrl, List<NameValuePair> headers, String requestUrl, List<NameValuePair> requestParameters) {
        HttpDelete get = new HttpDelete(baseUrl + requestUrl);
        setHeaders(get, headers);
        get.setURI(buildURl(get, requestParameters));
        response = execute(get);
        return convertResponseToString(response);
    }

    private void setEntity(HttpPost requestBase, List<NameValuePair> requestParameters) {
        try {
            requestBase.setEntity(new UrlEncodedFormEntity(requestParameters, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.info("Exception Log" + e);
        }
    }

    private void setEntity(HttpPut requestBase, List<NameValuePair> requestParameters) {
        try {
            requestBase.setEntity(new UrlEncodedFormEntity(requestParameters, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.info("Exception Log" + e);
        }
    }

    private HttpResponse execute(HttpRequestBase requestBase) {
        try {
            return client.execute(requestBase);
        } catch (IOException e) {
            log.info("Exception Log" + e);
            return null;
        }
    }

    private void setHeaders(HttpRequestBase requestBase, List<NameValuePair> headers) {
        for (NameValuePair header : headers) {
            requestBase.addHeader(header.getName(), header.getValue());
        }
    }

    private URI buildURl(HttpRequestBase requestBase, List<NameValuePair> requestParam) {
        URI uri = null;
        try {
            uri = new URIBuilder(requestBase.getURI()).addParameters(requestParam).build();
        } catch (URISyntaxException e) {
            log.info("Exception Log" + e);
        }
        return uri;
    }

    private String convertResponseToString(@NotNull HttpResponse response) {
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);

            }
            return result.toString();
        } catch (Exception e) {
            log.info("Exception Log" + e);
            return null;
        }
    }
}