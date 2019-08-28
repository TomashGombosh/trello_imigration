package controllers;

import config.Config;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardController extends ApiHelper {
    private String url = "cards";
    public String getCardInformation(String id) {

        return null;
    }

    public void createCard(@NotNull HashMap<String, String> map) {
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", map.get("name")));
        parameters.add(new BasicNameValuePair("desc", map.get("desc")));
        parameters.add(new BasicNameValuePair("idList", map.get("idList")));
        if(map.get("idLabels")!= null)
            parameters.add(new BasicNameValuePair("idLabels", map.get("idLabels")));
        if(map.get("idMembers")!= null)
            parameters.add(new BasicNameValuePair("idMembers", map.get("idMembers")));
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
    }

}
