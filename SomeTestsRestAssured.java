import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.petStoreService.models.PetAdd;
import ru.tinkoff.qa.petStoreService.models.PetAddResponse;
import ru.tinkoff.qa.petStoreService.models.PetGetResponse;

import java.util.ArrayList;

public class SomeTestsRestAssured {

    public static final String URL = "https://petstore.swagger.io/v2/pet";
    private PetAddResponse postSpec(PetAdd pet) {
        return RestAssured.given().body(pet)
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .post(URL)
                .then().statusCode(200).extract()
                .as(PetAddResponse.class);
    }
    private PetAddResponse putSpec(PetAdd newPet) {
        return RestAssured.given().body(newPet)
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .put(URL)
                .as(PetAddResponse.class);
    }
    private RequestSpecification getSpec() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
    @Test
    public void addPetTest() {
        PetAdd myPet = new PetAdd();
        myPet.setName("katya");
        ArrayList<String> photoUrl = new ArrayList<>();
        photoUrl.add("https://petstore.swagger.io/v2/pet");
        myPet.setPhotoUrls(photoUrl);
        //addPet
        PetAddResponse myPetResponse = postSpec(myPet);
        //getPet
        PetAddResponse getMyPet = getSpec()
                .get(URL + "/" + myPetResponse.getId())
                .then().statusCode(200).extract()
                .as(PetAddResponse.class);
        Assertions.assertEquals(myPet.getName(), getMyPet.getName(), "add check pet name");
    }

    @Test
    public void putPetTest() {
        PetAdd myPet = new PetAdd();
        myPet.setId(100);
        myPet.setName("rat");
        ArrayList<String> photoUrl = new ArrayList<>();
        photoUrl.add("String");
        myPet.setPhotoUrls(photoUrl);

        PetAdd myNewPet = new PetAdd();
        myNewPet.setName("mouse");
        myNewPet.setId(myPet.getId());
        //addPet
        postSpec(myPet);
        //putPet
        putSpec(myNewPet);
        //getPutPet
        PetAddResponse getMyPet = getSpec()
                .get(URL + "/" + myPet.getId())
                .as(PetAddResponse.class);
        Assertions.assertEquals(myNewPet.getName(), getMyPet.getName(), "pet name not changed");
    }
    @Test
    public void getNotFoundPetTest() {
        PetAdd myPet = new PetAdd();
        myPet.setId(0);
        myPet.setMessage("Pet not found");
        //getPet
        PetGetResponse getMyPet = getSpec()
                .get(URL + "/" + myPet.getId())
                .then().statusCode(404).extract()
                .as(PetGetResponse.class);
        Assertions.assertEquals(getMyPet.getMessage(), myPet.getMessage());
    }
}
