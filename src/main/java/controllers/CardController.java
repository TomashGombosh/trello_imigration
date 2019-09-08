package controllers;

import config.Config;
import dataModels.CardDataModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class CardController extends ApiHelper {
    private String url = "cards";
    public String getCardInformation(String id) {

        return null;
    }

    public void createCard(@NotNull CardDataModel cardDataModel) {
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", cardDataModel.getTitle()));
        parameters.add(new BasicNameValuePair("desc", cardDataModel.getDescription()));
        parameters.add(new BasicNameValuePair("idList",cardDataModel.getListId()));
        if(cardDataModel.getLabelsId()!= null)
            parameters.add(new BasicNameValuePair("idLabels", cardDataModel.getLabelsId()));
        if(cardDataModel.getMembersId()!= null)
            parameters.add(new BasicNameValuePair("idMembers", cardDataModel.getMembersId()));
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
    }

}
