package pageObjects;

import com.codeborne.selenide.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.ServiceMenuCategories;
import enums.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.ServiceMenuButtons.DATES;
import static enums.ServiceMenuButtons.DIFFERENT_ELEMENTS;
import static enums.ServiceMenuButtons.USER_TABLE;
import static enums.ServiceMenuCategories.*;
import static enums.Urls.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageSelenideCucumber {

    public HomePageSelenideCucumber() {
        page(this);
    }

    //==================================================WEB-ELEMENTS====================================================
    @FindBy(css = ".profile-photo")
    private SelenideElement profileButton;

    @FindBy(css = "[id = 'Name']")
    private SelenideElement login;

    @FindBy(css = "[id = 'Password']")
    private SelenideElement password;

    @FindBy(css = ".login [type = 'submit']")
    private SelenideElement submit;

    @FindBy(css = ".profile-photo span")
    private SelenideElement loggedUserNameElement;

    @FindBy(css = "[class = 'dropdown']")
    private SelenideElement upperServiceButton;

    @FindBy(css = "[class = 'dropdown-menu'] > li")
    private ElementsCollection upperServiceMenuElements;

    @FindBy(css = "[class = 'sidebar-menu'] > [index='3']")
    private SelenideElement leftServiceButton;

    @FindBy(css = "[class = 'sub'] > li")
    private ElementsCollection leftServiceMenuElements;

    @FindBy(css = ".benefit-icon")
    private ElementsCollection benefitIcons;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection textsUnderIconsElements;

    @FindBy(css = "h3.main-title.text-center")
    private SelenideElement mainHeaderElement;

    @FindBy(css = ".main-txt.text-center")
    private SelenideElement mainSubHeader;

    private final String EXPECTED_HOME_PAGE_TITLE = "Home Page";

    //==================================================METHODS=========================================================
    @Step("Open test site by URL")
    @Given("I am on \"Home Page\"")
    public void open() {
        Selenide.open(HOME_PAGE.url);
    }

    @Step("Assert Browser title")
    @Then("The browser title is Home Page")
    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), EXPECTED_HOME_PAGE_TITLE);
    }

    @Step("Perform login")
    @When("I login as user (.+) with password (.+)")
    public void login(String name, String pwd) {
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(pwd);
        submit.click();
    }

    @When("I login as user \"(.+)\"")
    public void login(String user) {
        profileButton.click();
        login.sendKeys(Users.getUserByName(user.toUpperCase()).login);
        password.sendKeys(Users.getUserByName(user.toUpperCase()).password);
        submit.click();
    }

    @Step("Assert User name in the left-top side of screen that user is loggined")
    @Then("The user icon (.+) is displayed on the header")
    public void checkLoggedUserName(String expectedName) {
        loggedUserNameElement.shouldBe(visible);
        loggedUserNameElement.shouldHave(text(expectedName));
    }

    @Step("Click on \"Service\" subcategory in the header")
    @When("I click on \"Service\" button in Header")
    public void clickOnUpperSelect() {
        upperServiceButton.click();
    }

    @Step("Check that upper \"Service\" drop down contains correct options")
    @Then("Upper Service drop down opens with all needed options")
    public void checkUpperServiceMenuContent() {
        for (String category : getExpectedCategoriesList()) {
            assertTrue(upperServiceMenuElements.texts().contains(category.toUpperCase()));
        }
    }

    @Step("Click on \"Service\" subcategory in the left section")
    @When("I click on the left Service subcategory")
    public void clickOnLeftSelect() {
        leftServiceButton.click();
    }

    @Step("Check that left \"Service\" drop down contains correct options")
    @Then("Left Service drop down opens with all needed options")
    public void checkLeftServiceMenuContent() {
        for (String category : getExpectedCategoriesList()) {
            assertTrue(leftServiceMenuElements.texts().contains(category));
        }
    }

    @Step("Open through the header menu Service -> Different Elements Page")
    @Then("I navigate to the Different Elements page through the upper Service menu")
    public void openDifferentElementsPageThroughTheHeaderMenu() {
        clickOnUpperSelect();
        upperServiceMenuElements.find(Condition.text(DIFFERENT_ELEMENTS.name)).click();
        assertEquals(WebDriverRunner.url(), DIFFERENT_ELEMENTS_PAGE.url);
    }

    @Step("Open through the header menu Service -> Dates Page")
    public void openDatesPageThroughTheHeaderMenu() {
        clickOnUpperSelect();
        upperServiceMenuElements.find(Condition.text(DATES.name)).click();
        assertEquals(WebDriverRunner.url(), DATES_PAGE.url);
    }

    @Step("Open through the header menu Service -> User Table")
    @And("I click on \"User Table\" button in Service dropdown")
    public void openUserTablePageThroughTheHeaderMenu() {
        clickOnUpperSelect();
        upperServiceMenuElements.find(Condition.text(USER_TABLE.name)).click();
        assertEquals(WebDriverRunner.url(), USER_TABLE_PAGE.url);
    }

    @Step("Assert that interface on Home page contains all needed elements.")
    @Then("Interface on Home Page contains all needed elements")
    public void checkInterfaceOfHomePage() {
        benefitIcons.shouldHave(size(4));
        for (SelenideElement icon : benefitIcons) {
            icon.shouldBe(visible);
        }
        textsUnderIconsElements.shouldHave(size(4));
        for (SelenideElement text : textsUnderIconsElements) {
            text.shouldBe(visible);
        }
        mainHeaderElement.shouldBe(visible);
        mainSubHeader.shouldBe(visible);
    }
}
