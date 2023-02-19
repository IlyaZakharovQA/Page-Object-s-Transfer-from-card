package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private SelenideElement transferButton = $("[data-test-id= 'action-transfer']");
    private SelenideElement amountInput = $("[data-test-id= 'amount'] input");
    private SelenideElement fromInput = $("[data-test-id= 'from'] input");
    private SelenideElement transferHead = $x("//h1[text()= 'Пополнение карты']");
    private SelenideElement error = $("[data-test-id= 'error']");


    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.sendKeys(amountToTransfer);
        fromInput.sendKeys(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findError(String expectedTest) {
        error.shouldHave(exactText(expectedTest), Duration.ofSeconds(8)).shouldBe(visible);
    }
}