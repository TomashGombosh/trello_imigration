package controllers.trello;

import com.google.gson.JsonParser;
import config.Config;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class LabelController extends ApiHelper {
    private JsonParser parser;
    private List<NameValuePair> parameters = new ArrayList<>();
    private List<NameValuePair> headers = new ArrayList<>();

    public LabelController() {
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        parser = new JsonParser();
    }
    //TODO add Label Add, Edit Delete to the controller
/*
    private HashMap<Integer, CardDataModel> getCardsInfo() {
        HashMap<Integer, CardDataModel> map = new HashMap<>();
        String url = "lists/" + primaryList.getId() + "/cards";
        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            CardDataModel getCardDataModel = new CardDataModel();
            getCardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getCardDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            getCardDataModel.setLabels(getLabels(jsonArray.get(counter).getAsJsonObject().get("labels").getAsJsonArray()));
            map.put(counter, getCardDataModel);
        }
        return map;
    }

    private boolean validateCardInfo(CardDataModel cardDataModel) {
        for (int counter = 0; counter < getCardsInList.size(); counter++) {
            if (getCardsInList.get(counter).isDataTheSame(cardDataModel))
                return true;
        }
        return false;
    }

    public String getCardId(CardDataModel cardDataModel) {
        HashMap<Integer, CardDataModel> map = getCardsInList;
        for (int counter = 0; counter < map.size(); counter++) {
            CardDataModel cardDataModel1 = map.get(counter);
            if (cardDataModel1.getName().equals(cardDataModel.getName()) || cardDataModel1.getName().equals(cardDataModel.getId())) {
                return cardDataModel1.getId();
            }
        }
        return null;
    }

    public CardDataModel getCardInfo(String idOrName) {
        CardDataModel cardDataModel = new CardDataModel(idOrName);
        if (getCardId(cardDataModel) == null) {
            String cardUrl = url + "/" + idOrName;
            return getCard(cardUrl);
        }
        if (getCardId(cardDataModel) != null) {
            CardDataModel cardDataModel1 = new CardDataModel();
            cardDataModel1.setName(idOrName);
            String cardUrl = url + "/" + getCardId(cardDataModel1);
            return getCard(cardUrl);
        } else {
            return new CardDataModel("No Such Card");
        }
    }

    public CardDataModel createCard(@NotNull CardDataModel cardDataModel) {
        CardDataModel getCardDataModel = new CardDataModel();
        parameters.add(new BasicNameValuePair("name", cardDataModel.getName()));
        parameters.add(new BasicNameValuePair("desc", cardDataModel.getDescription()));
        parameters.add(new BasicNameValuePair("idList", cardDataModel.getListId()));
        if (cardDataModel.getLabels() != null)
            for (int counter = 0; counter < cardDataModel.getLabels().size(); counter++)
                parameters.add(new BasicNameValuePair("idLabels", cardDataModel.getLabels().get(counter).getId()));
        if (cardDataModel.getMembersId() != null)
            parameters.add(new BasicNameValuePair("idMembers", cardDataModel.getMembersId()));
        JsonObject jsonObject = (JsonObject) parser.parse(sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters));
        getCardDataModel.setId(jsonObject.getAsJsonObject().get("id").getAsString());
        getCardDataModel.setName(jsonObject.getAsJsonObject().get("name").getAsString());
        getCardDataModel.setDescription(jsonObject.getAsJsonObject().get("desc").getAsString());
        getCardDataModel.setLabels(getLabels(jsonObject.get("idLabels").getAsJsonArray()));
        return getCardDataModel;
    }

    public String deleteCard(@NotNull CardDataModel cardDataModel) {
        if (validateCardInfo(cardDataModel)) {
            String durl = url + "/" + cardDataModel.getId();
            return sendDeleteRequest(Config.TRELLO_API_URL, headers, durl, parameters);
        } else {
            return "Board with that data is not present";
        }
    }*/
}
