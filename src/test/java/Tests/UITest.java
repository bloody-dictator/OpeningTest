package Tests;

import Pages.GooglePage;
import Pages.OpeningPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

public class UITest {
    private final String GOOGLE_URL = "https://www.google.com/";

    private final String SEARCHING_STRING = "Открытие";

    private final String OPENING_URL = "https://www.open.ru/";

    @Test(description = "Проверяет присутствие open.ru в результатах поиска и курсы валют на open.ru")
    public void checkExchangeRate() {
        open(GOOGLE_URL);
        GooglePage googlePage = new GooglePage();
        //ищем слово "Открытие в гугле
        googlePage.searchGoogle(SEARCHING_STRING);
        //ищем в результатах поиска урл открытия
        googlePage.searchInResultsURL(OPENING_URL);
        OpeningPage openingPage = new OpeningPage();
        //проверяем что блок с курсом валют существует
        openingPage.getMainPageExchangeElement().shouldHave();
        //две строки с евро и долларом в списке строк
        for (SelenideElement i : openingPage.getMainPageExchangeRow()){
            List<String> rates = i.findAll(By.cssSelector("span.main-page-exchange__rate")).texts();
            //запятая в строках, число не парсится без замены
            List<Double> finalRates = rates.stream().map(rate -> rate.replace(",", ".") )
                    .mapToDouble(Double:: parseDouble).boxed().collect(Collectors.toList());
            Assert.assertTrue(finalRates.size()==2);
            Assert.assertTrue(finalRates.get(0)<finalRates.get(1));
        }
    }
}

