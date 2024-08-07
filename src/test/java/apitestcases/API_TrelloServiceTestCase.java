package apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class API_TrelloServiceTestCase {


    public String BaseUrl = "https://api.trello.com/1";

    public String name = "BOARD_TEST";

    public String key = "324c0c34d20d05497425b2941ee4667e";

    public String token = "ATTA47b6f298ad7fc60153ea04a01d6a7fe218e08b13cbc8d48112b10ebc0ef79c9e8065E52A";

    public String boardId = "";

    public String idList = "";

    public String cardID = "";

    public String cardID2 = "";


    @org.testng.annotations.Test(priority = 1)
    public void createBoard() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("name", name)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .post(BaseUrl + "/boards/")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        boardId = response.jsonPath().getString("id");

        System.out.println(boardId);

    }

    @org.testng.annotations.Test(priority = 2)
    public void createList() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .when()
                .post(BaseUrl + "/lists")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        idList = response.jsonPath().getString("id");

        System.out.println(idList);
    }

    @org.testng.annotations.Test(priority = 3)
    public void createCard() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("idList", idList)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name)
                .when()
                .post(BaseUrl + "/cards")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        cardID = response.jsonPath().getString("id");

    }

    @org.testng.annotations.Test(priority = 3)
    public void createCardTwo() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("idList", idList)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "CARD_2")
                .when()
                .post(BaseUrl + "/cards")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        cardID2 = response.jsonPath().getString("id");
    }

    @org.testng.annotations.Test(priority = 4)
    public void updateCart() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "CARD_2_UPDATE")
                .when()
                .put(BaseUrl + "/cards/" + cardID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

    }

    @org.testng.annotations.Test(priority = 5)
    public void deleteCard() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete(BaseUrl + "/cards/" + cardID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

    }

    @org.testng.annotations.Test(priority = 6)
    public void deleteCard2() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete(BaseUrl + "/cards/" + cardID2)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

    }

    @org.testng.annotations.Test(priority = 7)
    public void deleteBoard() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete(BaseUrl + "/boards/" + boardId)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

    }
}