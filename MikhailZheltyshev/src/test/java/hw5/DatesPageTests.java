package hw5;

import base.SelenideTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DatesPageSelenide;
import pageObjects.HomePageSelenide;

import static com.codeborne.selenide.Selenide.page;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("UI Tests")
@Story("Dates Page Testing")
@Listeners(AllureAttachmentListener.class)
public class DatesPageTests extends SelenideTestBase {

    private HomePageSelenide homePage;
    private DatesPageSelenide datesPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePageSelenide.class);
        datesPage = page(DatesPageSelenide.class);
    }

    @Issue("Setting to (30,70) bug")
    @Test
    public void datesPageInterfaceCheck() {

        //1 Open test site by URL
        homePage.open();

        //2 Assert Browser title
        homePage.checkTitle();

        //3 Perform login
        homePage.login(PITER_CHAILOVSKII);

        //4 Assert User name in the left-top side of screen that user is logged
        homePage.checkLoggedUserName(PITER_CHAILOVSKII);

        //5 Open through the header menu Service -> Dates Page
        homePage.openDatesPageThroughTheHeaderMenu();

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        datesPage.setSlidersPosition(0, 100);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkSlidersSettingLog(0, 100);

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datesPage.setSlidersPosition(0, 0);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkSlidersSettingLog(0, 0);

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datesPage.setSlidersPosition(100, 100);

        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkSlidersSettingLog(100, 100);

        //12 Using drag-and-drop set Range sliders: from 30 to 70.
        datesPage.setSlidersPosition(30, 70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkSlidersSettingLog(30, 70);
    }
}
