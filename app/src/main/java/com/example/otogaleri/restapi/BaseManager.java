package com.example.otogaleri.restapi;

public class BaseManager {
    protected RestApi getRestApi() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.Url);
        return restApiClient.getRestApi();
        /*return new RestApiClient(BaseUrl.Url).getRestApi();/*buda iş görür*/
    }
}
