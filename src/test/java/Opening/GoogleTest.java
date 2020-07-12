package Opening;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    public SelenideElement getMainPageExchangeElement(){
        return $(By.className("main-page-exchange"));
    }

    public ElementsCollection getMainPageExchangeRow(){
        return $$(By.className("main-page-exchange__row"));
    }

    public String getGoogleURL(){ return "https://www.google.com/"; }

    public String getStringForSearch(){ return "Открытие"; }

    public String getOpeningURL(){ return "https://www.open.ru/"; }

    @Test
    public void checkExchangeRate() {
        open(getGoogleURL());
        GooglePage googlePage = new GooglePage();
        googlePage.searchGoogle(getStringForSearch());
        googlePage.getGoogleSearchResults()
                .findBy(Condition.attribute("href", getOpeningURL())).shouldHave().pressEnter();
        getMainPageExchangeElement().shouldHave();
        for (SelenideElement i : getMainPageExchangeRow()){
            List<String> rates = i.findAll(By.cssSelector("span.main-page-exchange__rate")).texts();
            List<Double> finalRates = rates.stream().map(rate -> rate.replace(",", ".") )
                    .mapToDouble(Double:: parseDouble).boxed().collect(Collectors.toList());
            Assert.assertTrue(finalRates.size()==2);
            Assert.assertTrue(finalRates.get(0)<finalRates.get(1));
        }
    }
}

