package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }


    public void createNewList(String listName) {
    }

    public void addCardToFirstList(String cardName) {
        actions.waitForElementVisible("trello.addNewCard.Button");
        actions.clickElement("trello.addNewCard.Button");
        actions.waitForElementVisible("trello.createCard.titleInput");
        actions.typeValueInField(cardName, "trello.createCard.titleInput");
        actions.clickElement("trello.addCard.submitButton");
    }

    public void moveCardToList(String cardName, String listName) {
        actions.waitForElementVisible("trello.boardPage.cardByName", cardName);
        WebElement card = driver.findElement(By.xpath(String.format("//span[@class='list-card-title js-card-name' and text()='%s']", cardName)));
        WebElement destination = driver.findElement(By.xpath(String.format("//div[@class='list js-list-content' and (descendant::h2[text()='%s'])]", listName)));
        Actions actions1 = new Actions(actions.getDriver());
        actions1.clickAndHold(card).moveToElement(destination).release().perform();

    }


    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");
    }

    public void assertAddCardExists() {
        actions.waitForElementPresent("trello.boardPage.cardLocator");
    }

    public void assertCardInList(String cardName, String listName) {
        actions.waitForElementPresent("trello.CheckIfACard.IsInAList", cardName,listName);
    }

    public void assertCardNotInList(String listName, String cardName) {
        actions.waitForElementPresent("trello.CheckIfACard.NotInAList", listName, cardName);
    }

    public void deleteBoard(String boardName) {
        actions.waitForElementPresent("trello.boardPage.threeDotMenuButton");
        actions.clickElement("trello.boardPage.threeDotMenuButton");

        actions.waitForElementPresent("trello.threeDotMenu.closeBoard.button");
        actions.clickElement("trello.threeDotMenu.closeBoard.button");

        actions.waitForElementPresent("trello.threeDotsMenu.confirm.closeBoardButton");
        actions.clickElement("trello.threeDotsMenu.confirm.closeBoardButton");

        actions.waitForElementPresent("trello.threeDotsMenu.permanentlyDeleteBoard");
        actions.clickElement("trello.threeDotsMenu.permanentlyDeleteBoard");

        actions.waitForElementPresent("trello.threeDotsMenu.closeBoardDeleteBoard.ConfirmButton");
        actions.clickElement("trello.threeDotsMenu.closeBoardDeleteBoard.ConfirmButton");
    }

    public void assertBoardDeleted(String boardName) {
        actions.waitForElementPresent("trello.pressTrelloWorkspace.button");
        actions.clickElement("trello.pressTrelloWorkspace.button");

        actions.waitForElementPresent("trello.workspace.boards.button");
        actions.clickElement("trello.workspace.boards.button");

        actions.waitForElementPresent("trello.workspace.createYourFirstBoard.button");

    }
}

