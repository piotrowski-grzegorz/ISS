package services;

import api.HttpClientService;
import api.iss_current_location.CurrentLocationResponse;
import api.people_in_space.PeopleInSpaceResponse;


public class IssService {
    public CurrentLocationResponse getLocationFromOpenNotify(){
        String openNotifyUrl = "http://api.open-notify.org/iss-now.json";
        HttpClientService<CurrentLocationResponse> httpClientService = new HttpClientService<CurrentLocationResponse>();
        final CurrentLocationResponse response = httpClientService.getISSCurentLocation(
                openNotifyUrl,
                CurrentLocationResponse.class);
        return response;
    }

    public PeopleInSpaceResponse getPeopleInSpace(){
        String openNotifyUrl = "http://api.open-notify.org/astros.json";
        HttpClientService<PeopleInSpaceResponse> httpClientService = new HttpClientService<PeopleInSpaceResponse>();
        final PeopleInSpaceResponse response = httpClientService.getISSCurentLocation(
                openNotifyUrl,
                PeopleInSpaceResponse.class);
        return response;
    }
}
