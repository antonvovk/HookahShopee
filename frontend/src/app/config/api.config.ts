export class ApiConfig {

  public static readonly API_URL = 'http://localhost:8080/api';
  //http://hookah-api.eu-west-3.elasticbeanstalk.com/

  public static readonly POST_API_URL = ApiConfig.API_URL + '/post';
  public static readonly MANUFACTURER_API_URL = ApiConfig.API_URL + '/manufacturer';
  public static readonly PRODUCT_API_URL = ApiConfig.API_URL + '/product';
  public static readonly CITY_API_URL = ApiConfig.API_URL + '/city';
}
