package com.example.pages;

import com.example.framework.utils.ui.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnsplashSearchResultsPage {

  private SeleniumWrapper seleniumWrapper;

  @FindBy(css = "h1[data-test='page-header-title']")
  private WebElement searchResultsTitle;

  @FindBy(
      css = "div[data-test='search-photos-route'] div[data-test='masonry-grid-count-three'] figure")
  private List<WebElement> searchResults;

  public UnsplashSearchResultsPage(WebDriver driver, SeleniumWrapper seleniumWrapper) {
    this.seleniumWrapper = seleniumWrapper;
    PageFactory.initElements(driver, this);
  }

  public boolean isSearchResultsTitleDisplayed() {
    return seleniumWrapper.elements.isElementDisplayed(searchResultsTitle);
  }

  public String getSearchResultsTitle() {
    return seleniumWrapper.elements.retrieveText(searchResultsTitle);
  }

  public int getSearchResultsCount() {
    return searchResults.size();
  }

  public void clickFirstSearchResult() {
    if (!searchResults.isEmpty()) {
      seleniumWrapper.elements.clickElement(searchResults.get(0));
      seleniumWrapper.waits.waitForPageLoad();
    }
  }
}
