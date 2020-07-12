package Tests;

import Pojos.PostPojo;
import Pojos.ResponsePojo;
import Pojos.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {
    private final String API_URL = "https://reqres.in/api/users";

    @Test(description = "Проверяет, что в ответе Get-запроса поля списка пользователей notNull")
    public void checkGetAPI(){
        Response response = given().queryParam("page", "2").when().get(API_URL);
       ResponsePojo responsePojo =response.getBody().as(ResponsePojo.class);
       UserPojo[] data = responsePojo.getData();
       for (UserPojo i: data){
           Assert.assertNotNull(i.getFirstName());
           Assert.assertNotNull(i.getLastName());
           Assert.assertNotNull(i.getEmail());
           Assert.assertNotNull(i.getAvatar());
       }
    }

    @Test(description = "Проверяет, что в ответе Post-запроса те же самые значения, что в запросе")
    public void checkPostAPI(){
        PostPojo postPojo = new PostPojo("morfeus", "leader");
        Response response = given().contentType(ContentType.JSON).body(postPojo)
                .when().post(API_URL);
        PostPojo postPojoResponse = response.getBody().as(PostPojo.class);
        Assert.assertEquals(postPojo.getName(), postPojoResponse.getName());
        Assert.assertEquals(postPojo.getJob(), postPojoResponse.getJob());

    }
}
