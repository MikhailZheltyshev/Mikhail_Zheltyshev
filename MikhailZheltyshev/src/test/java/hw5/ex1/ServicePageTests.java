package hw5.ex1;

import base.SelenideTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DifferentELementsPageSelenide;
import pageObjects.HomePageSelenide;

import static com.codeborne.selenide.Selenide.page;
import static enums.Urls.HOME_PAGE;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("UI Tests")
@Story("Service Page Testing")
public class ServicePageTests extends SelenideTestBase {

    private HomePageSelenide homePage;
    private DifferentELementsPageSelenide differentELementsPage;

    @BeforeClass
    public void beforeClass() {
        homePage = page(HomePageSelenide.class);
        differentELementsPage = page(DifferentELementsPageSelenide.class);
    }

    @Test
    public void servicePageInterfaceCheck() {

        //1 Open test site by URL
        homePage.open(HOME_PAGE.url);

        //2 Assert Browser title
        homePage.checkTitle();

        //3 Perform login
        homePage.login(PITER_CHAILOVSKII.login, PITER_CHAILOVSKII.password);

        //4 Assert User name in the left-top side of screen that user is loggined
        homePage.checkLoggedUserName(PITER_CHAILOVSKII.displayName);

        //5 Click on "Service" subcategory in the header and check that drop down contains options
        //5.1 Click
        homePage.clickOnUpperSelect();

        //5.2 Check for content
        homePage.checkUpperServiceMenuContent();

        //6 Click on Service subcategory in the left section and check that drop down contains options
        //6.1 Click
        homePage.clickOnLeftSelect();

        //6.2 Check for content
        homePage.checkLeftServiceMenuContent();

        //7 Open through the header menu Service -> Different Elements Page
        homePage.openDifferentElementsPageThroughTheHeaderMenu();

        //8 Check interface on Different elements page, it contains all needed elements
        differentELementsPage.checkDifferentElementsPageContent();

        //9 Assert that there is Right Section
        differentELementsPage.checkRightSectionExists();

        //10 Assert that there is Left Section
        differentELementsPage.checkLeftSectionExists();

        //11 Select checkboxes
        differentELementsPage.selectWaterAndWindCheckBoxes();

        //12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        differentELementsPage.checkLoggingOfCheckBoxesChecked();

        //13 Select radio
        differentELementsPage.selectSelenRadioButton();

        //14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        differentELementsPage.checkLoggingOfRadioButtons();

        //15 Select in dropdown
        differentELementsPage.selectYellowFromDropDownMenu();

        //16 Assert that for dropdown there is a log row and value is corresponded to the selected value 
        differentELementsPage.checkLoggingOfDropDownMenu();

        //17 Unselect and assert checkboxes
        differentELementsPage.unselectWaterAndWindCheckBoxes();

        //18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        differentELementsPage.checkLoggingOfCheckBoxesUnchecked();
    }
}
