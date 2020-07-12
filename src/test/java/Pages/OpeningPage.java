package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OpeningPage {
    public SelenideElement getMainPageExchangeElement(){
        return $(By.className("main-page-exchange"));
    }

    public ElementsCollection getMainPageExchangeRow(){
        return $$(By.className("main-page-exchange__row"));
    }
}
