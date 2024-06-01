package com.assignment.fancode;

import com.assignment.constants.StatusCodeEnum;
import com.assignment.core.TestBase;
import com.assignment.endpoints.Endpoints;
import com.assignment.model.Todo;
import com.assignment.model.User;
import com.assignment.utils.ResponseArrayUtils;
import com.assignment.utils.TestHelper;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTest{
    private static final double PASS_PERCENT_THRESHOLD = 50;
    private static final String QUERY_PARAM_USER_ID = "userId";

    @BeforeClass
  	public void setUp() {
  		RestAssured.baseURI = Config.BASE_URI;
  	}

    @AfterMethod
    public void afterEachTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println(result.getName() + " - Test Case Failed");
            System.out.println("Cause : " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println(result.getName() + " - Test Case Skipped");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println(result.getName() + " - Test Case Passed");
        } else {
            System.out.println(result.getName() + " - Test Case Status Unknown");
        }
    }
  
    @Test
    public void testTaskCompletedMoreThan50Percent() {
        Response userResponse = given().when().get(Endpoints.USERS);
        Assert.assertEquals(userResponse.getStatusCode(), CodeEnum.OK.getCode(), "Failed to fetch users");

        User[] users = ResponseUtils.getResponseAsArray(userResponse, User[].class);

        for (User user : users) {
            double latitude = Double.parseDouble(user.getAddress().getGeo().getLat());
            double longitude = Double.parseDouble(user.getAddress().getGeo().getLng());

            if (Helper.isLatLongValid(latitude, longitude)) {
                processUserTodos(user);
            }
        }
    }

    private void processUserTodos(User user) {
        String userName = user.getName();
      
        Response todoResponse = given().queryParam(QUERY_PARAM_USER_ID, user.getId()).when().get(Endpoints.TODOS);
        Assert.assertEquals(todoResponse.getStatusCode(), CodeEnum.OK.getCode(), "Failed to fetch todos");

        Todo[] todos = ResponseArrayUtils.getResponseAsArray(todoResponse, Todo[].class);
        int completedTaskCount = getCompletedTaskCount(todos);

        double completedPercentage = TestHelper.calculateTaskCompletedPercentage(completedTaskCount, todos.length);

        Assert.assertTrue(completedPercentage > PASS_PERCENT_THRESHOLD,
                "User " + user.getId() + " in the Fancode city has less than 50% tasks completed");
    }

    private int getCompletedTaskCount(Todo[] todos) {
        int count = 0;
        for (Todo todo : todos) {
            if (todo.isCompleted()) {
                count++;
            }
        }
        return count;
    }
}
