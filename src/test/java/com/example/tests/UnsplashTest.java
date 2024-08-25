package com.example.tests;

import com.example.pages.UnsplashHomePage;
import com.example.pages.UnsplashLoginPage;
import com.example.pages.UnsplashSearchResultsPage;
import com.example.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnsplashTest extends BaseTest {

  @Test(description = "Verify that the Unsplash home page loads successfully")
  public void testHomePageLoads() {
    getSeleniumWrapper().navigation.goToUrl(getBaseUrl());
    UnsplashHomePage homePage = new UnsplashHomePage(getDriver(), getSeleniumWrapper());
    homePage.waitForPageLoad();
    Assert.assertTrue(homePage.isHomePageLoaded(), "Home page is not loaded correctly");
  }

  @Test(description = "Verify that the search functionality works correctly")
  public void testSearchFunctionality() {
    getSeleniumWrapper().navigation.goToUrl(getBaseUrl());
    UnsplashHomePage homePage = new UnsplashHomePage(getDriver(), getSeleniumWrapper());
    homePage.waitForPageLoad();
    homePage.searchForImage("nature");

    UnsplashSearchResultsPage searchResultsPage =
        new UnsplashSearchResultsPage(getDriver(), getSeleniumWrapper());
    Assert.assertTrue(
        searchResultsPage.isSearchResultsTitleDisplayed(), "Search results title is not displayed");
    Assert.assertTrue(
        searchResultsPage.getSearchResultsTitle().toLowerCase().contains("nature"),
        "Search results title does not contain the search query");
    Assert.assertTrue(searchResultsPage.getSearchResultsCount() > 0, "No search results found");
  }

  @Test(description = "Verify that the Explore page can be accessed from the home page")
  public void testExplorePageNavigation() {
    getSeleniumWrapper().navigation.goToUrl(getBaseUrl());
    UnsplashHomePage homePage = new UnsplashHomePage(getDriver(), getSeleniumWrapper());
    homePage.waitForPageLoad();
    homePage.navigateToExplorePage();
    Assert.assertTrue(
        getSeleniumWrapper().navigation.getCurrentUrl().contains("/explore"),
        "Navigation to Explore page failed");
  }

  @Test(description = "Verify that an error message is displayed for invalid login credentials")
  public void testInvalidLogin() {
    getSeleniumWrapper().navigation.goToUrl(getBaseUrl());
    UnsplashHomePage homePage = new UnsplashHomePage(getDriver(), getSeleniumWrapper());
    homePage.waitForPageLoad();
    homePage.navigateToLoginPage();

    UnsplashLoginPage loginPage = new UnsplashLoginPage(getDriver(), getSeleniumWrapper());
    loginPage.login("invalid@example.com", "invalidpassword");

    Assert.assertTrue(
        loginPage.isErrorMessageDisplayed(), "Error message is not displayed for invalid login");
    Assert.assertTrue(
        loginPage.getErrorMessage().contains("Invalid email or password"),
        "Incorrect error message displayed");
  }

  @Test(description = "Verify that the first search result can be clicked")
  public void testClickFirstSearchResult() {
    getSeleniumWrapper().navigation.goToUrl(getBaseUrl());
    UnsplashHomePage homePage = new UnsplashHomePage(getDriver(), getSeleniumWrapper());
    homePage.waitForPageLoad();
    homePage.searchForImage("landscape");

    UnsplashSearchResultsPage searchResultsPage =
        new UnsplashSearchResultsPage(getDriver(), getSeleniumWrapper());
    Assert.assertTrue(searchResultsPage.getSearchResultsCount() > 0, "No search results found");
    searchResultsPage.clickFirstSearchResult();

    // Add assertions to verify that the image details page is loaded
    // This might require creating a new UnsplashImageDetailsPage class
  }
}
