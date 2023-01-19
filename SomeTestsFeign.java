import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.petStoreService.models.PetAdd;
import ru.tinkoff.qa.petStoreService.models.PetService;

import java.util.ArrayList;

public class SomeTestsFeign {
    @Test
    public void deletePetTest() {
        PetAdd pet = new PetAdd();
        pet.setName("blackHole");
        pet.setId(256);
        ArrayList<String> photoUrl = new ArrayList<>();
        photoUrl.add("photoURL");
        pet.setPhotoUrls(photoUrl);
        pet.setMessage("Pet not found");

        PetService petService = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .decode404()
                .target(PetService.class, "https://petstore.swagger.io/v2");
        petService.createPet(pet);
        petService.deletePet(pet.getId());
        PetAdd delPet = petService.getPet(pet.getId());

        Assertions.assertEquals(pet.getMessage(), delPet.getMessage(), "pet not delete");
    }
}
