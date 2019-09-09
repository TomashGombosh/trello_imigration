package controllers.trello;

import config.Config;
import dataModels.ListDataModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class ListController  extends ApiHelper {
    private String url = "lists";

    public void createList(@NotNull ListDataModel listDataModel) {
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", listDataModel.getName()));
        parameters.add(new BasicNameValuePair("idBoard", listDataModel.getIdBoard()));
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
    }

}
