package test.cases.trello;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest extends BaseTest {

    private BoardPage boardPage;
    private BoardsPage boardsPage;


    @Before
    public void pagesSetup() {
        boardPage = new BoardPage(actions.getDriver());
        boardsPage = new BoardsPage(actions.getDriver());
    }

    @Test
    public void test1_createBoardWhenCreateBoardClicked() {

        login();
        boardsPage.createBoard();
        boardPage.assertAddListExists();

    }

    @Test
    public void test2_createNewCardInExistingBoardWhenCreateCardClicked() {

        boardsPage.clickOnAsideBoard(getUIMappingByKey("trello.boardName"));
        String cardName = getUIMappingByKey("trello.cardName");

        boardPage.addCardToFirstList(cardName);
        boardPage.assertAddCardExists();

        String firstListName = getUIMappingByKey("trello.firstListName");
        boardPage.assertCardInList(cardName, firstListName);
    }

    @Test
    public void test3_moveCardBetweenStatesWhenDragAndDropIsUsed() {

        String cardName = getUIMappingByKey("trello.cardName");
        String targetListName = getUIMappingByKey("trello.targetListName");
        String firstListName = getUIMappingByKey("trello.firstListName");

        boardPage.moveCardToList(cardName, targetListName);

        boardPage.assertCardInList(cardName, targetListName);
        boardPage.assertCardNotInList(firstListName, cardName);
    }

    @Test
    public void test4_deleteBoardWhenDeleteButtonIsClicked() {

        String workspaceName = boardPage.getWorkspaceName();
        System.out.println(workspaceName);

        String boardName = getUIMappingByKey("trello.boardName");

        boardPage.deleteBoard(boardName);

        boardPage.assertBoardDeleted(workspaceName);
    }
}
