package Tests;

import Pojos.PostPojo;
import Pojos.ResponsePojo;
import Pojos.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {
    private final String API_URL = "https://reqres.in/api/users";

    @Test(description = "Проверяет, что в ответе Get-запроса поля списка пользователей notNull")
    public void checkGetAPI(){
        Response response = given().queryParam("page", "2").when().get(API_URL);
       ResponsePojo responsePojo =response.getBody().as(ResponsePojo.class);
       List<UserPojo> data = Arrays.asList(responsePojo.getData());
       for (UserPojo i: data){
           Assert.assertTrue(!(i.getFirstName().equals(null)));
           Assert.assertTrue(!(i.getLastName().equals(null)));
           Assert.assertTrue(i.getId()!=0);
           Assert.assertTrue(!(i.getEmail().equals(null)));
           Assert.assertTrue(!(i.getAvatar().equals(null)));
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
