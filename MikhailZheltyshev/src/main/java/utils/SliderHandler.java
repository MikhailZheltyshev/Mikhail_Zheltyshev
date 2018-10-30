package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SliderHandler {

    private SelenideElement sliderRange = $(".range");

    private Double getStep() {
        return (double) sliderRange.getSize().width / 100;
    }

    public void setPosition(SelenideElement handle, int targetPosition) {
        Actions actions = new Actions(getWebDriver());
        Double current = getCurrentPosition(handle);
        int xOffset = (int) ((targetPosition - current - 1) * getStep());
        actions.dragAndDropBy(handle, xOffset, 0).perform();

    }

    public Double getCurrentPosition(SelenideElement handle){
        return Double.parseDouble(handle.getCssValue("left").replaceAll("px", "")) / getStep();
    }

    //This method works more stable on Mac platform for some reason.
    public void setPositionExperimental(SelenideElement handle, int targetPosition) {
        Actions actions = new Actions(getWebDriver());
        double current = Double.parseDouble(handle.getCssValue("left").replaceAll("px", ""));
        int xOffset;
        if (targetPosition == 0) {
            xOffset = (int) -Math.ceil(current);
        } else if (targetPosition == 100) {
            xOffset = (int) Math.ceil(sliderRange.getSize().width - current);
        } else {
            xOffset = (int) ((targetPosition - (current / getStep()) - 1) * getStep());
        }
        actions.dragAndDropBy(handle, xOffset, 0).perform();
    }
}