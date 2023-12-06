package services;

import api.HttpClientService;
import api.iss_current_location.CurrentLocationResponse;

public class IssService {
    public CurrentLocationResponse getLocationFromOpenNotify(){
        String openNotifyUrl = "http://api.open-notify.org/iss-now.json";
        HttpClientService<CurrentLocationResponse> httpClientService = new HttpClientService<CurrentLocationResponse>();
        final CurrentLocationResponse response = httpClientService.getISSCurentLocation(openNotifyUrl, CurrentLocationResponse.class);
        return response;

    }
}
