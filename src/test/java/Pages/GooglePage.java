package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GooglePage {
    public SelenideElement getGoogleSearchString(){
        return $(By.name("q"));
    }

    public ElementsCollection getGoogleSearchResults(){
        return  $$("#res .g .rc .r a");
    }

    public void searchGoogle(String searchingString){
        getGoogleSearchString().setValue(searchingString).pressEnter();
    }
}
