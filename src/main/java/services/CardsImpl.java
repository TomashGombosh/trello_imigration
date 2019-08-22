package services;

import config.Config;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import services.helper.ApiHelper;
import services.helper.JsonHelper;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class CardsImpl extends ApiHelper implements MainService{
    @Override
    public String add(String name) {
        return null;
    }

    @Override
    public String edit(String name) {
        return null;
    }

    @Override
    public String delete(String name) {
        return null;
    }

    @Override
    public boolean validate(String name) {
        String url = "members/" + Config.MEMBER + "/boards?key=" + Config.API_KEY + "&token=" + Config.TOKEN;
        JsonObject jsonObject = sendGetRequest(Config.TRELLO_API_URL, null, url);
        System.out.print(jsonObject);
        return new JsonHelper().isDataPresentInJson(jsonObject,name);
    }
}
