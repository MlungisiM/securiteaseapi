import common.base;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import utils.TestListener;
import java.io.File;
import java.util.Map;



@Listeners(TestListener.class)
public class securiteaseapi extends base implements ITestListener {

    String endpoint = getProp().getProperty("endpoint");


    @Test(priority = 1)
    public void ValidateApiSchema() {

        Response response = RestAssured.get(endpoint);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/main/resources/all.json")));
    }

    @Test(priority = 2)
    public void ConfirmTotalCountries() {
        Response response = RestAssured.get(endpoint);
        int countryCount = response.jsonPath().getList("name.common").size();
        Assert.assertEquals(countryCount, 195, "Country count does not match expected value of 195");
    }

    @Test(priority = 3)
    public void ValidateSouthAfricanSignLanguage() {
            Response response = RestAssured.get(endpoint);

        // Extract South Africa's language data directly
        Map<String, String> languages = response.jsonPath().getMap("find { it.cca2 == 'ZA' }.languages");

        // Ensure languages exist before checking
        Assert.assertNotNull(languages, "Languages field is missing for South Africa");

        //Print available languages
        System.out.println("Languages found for South Africa: " + languages);

        // Check if SASL exists directly in the map
        boolean saslExists = languages.containsKey("sasl");
        Assert.assertTrue(saslExists, "South African Sign Language (SASL) is not listed as an official language of South Africa");
    }
}



