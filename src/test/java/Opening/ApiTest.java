package Opening;

import Pojos.PostPojo;
import Pojos.ResponsePojo;
import Pojos.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ApiTest {
    @Test
    public void checkURLAPI(){
        Response response = get("https://reqres.in/api/users?page=2");
       ResponsePojo responsePojo =response.getBody().as(ResponsePojo.class);
       List<UserPojo> data = Arrays.asList(responsePojo.getData());
       for (UserPojo i: data){
           Assert.assertTrue(!(i.getFirst_name().equals(null)));
           Assert.assertTrue(!(i.getLast_name().equals(null)));
           Assert.assertTrue(i.getId()!=0);
           Assert.assertTrue(!(i.getEmail().equals(null)));
           Assert.assertTrue(!(i.getAvatar().equals(null)));
       }
    }

    @Test
    public void checkPostAPI(){
        PostPojo postPojo = new PostPojo("morfeus", "leader");
        Response response = given().contentType(ContentType.JSON).body(postPojo)
                .when().post("https://reqres.in/api/users");
        PostPojo postPojoResponse = response.getBody().as(PostPojo.class);
        Assert.assertEquals(postPojo.getName(), postPojoResponse.getName());
        Assert.assertEquals(postPojo.getJob(), postPojoResponse.getJob());

    }
}
