package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.*;


public class FitnessBlenderTest extends BaseTest {

    public static final String SEARCH_TERM_VALUE = "lower body workout";
    public static final String SEARCH_TERM_NOT_CONTAINS_MESSAGE = "Results do not contain searched term";
    public static final String SEARCH_TERM_LIST_NOT_EMPTY_MESSAGE = "List is not empty";
    public static final String PLAY_BUTTON_NOT_ENABLED_MESSAGE = "The video can not be played";
    public static final String CHOSEN_FILTERS_DOES_NOT_MATCH_MESSAGE = "Filters does not match";
    public static final String LISTS_ARE_NOT_EQUAL_MESSAGE = "Lists are not equal";

    @Test
    public void searchTest_positive() throws InterruptedException {
        CheckSearchFunctionality searchPage = new CheckSearchFunctionality(driver);
        boolean termExists = searchPage.checkSearchFunctionality(SEARCH_TERM_VALUE);
        Assert.assertTrue(SEARCH_TERM_NOT_CONTAINS_MESSAGE, termExists);

        Thread.sleep(3000); // for visual confirmation
    }

    @Test
    public void isResultListNotEmptyTest_positive() throws InterruptedException {
        CheckSearchFunctionality searchPage = new CheckSearchFunctionality(driver);
        boolean isEmptyList = searchPage.checkResultList(SEARCH_TERM_VALUE);
        Assert.assertFalse(SEARCH_TERM_LIST_NOT_EMPTY_MESSAGE, isEmptyList);

        Thread.sleep(3000); // for visual confirmation
    }

    @Test
    public void isVideoPlayButtonEnabledTest_positive() throws InterruptedException {
        ChooseFromWorkoutsAndProgramsMenu workout = new ChooseFromWorkoutsAndProgramsMenu(driver);
        workout.chooseFromWorkoutDropdownMenu(0);
        ChooseFilters video = new ChooseFilters(driver);
        video.checkFiltersAndChooseVideo();
        boolean playButtonEnabled = video.isPlayButtonEnabled();
        Assert.assertTrue(PLAY_BUTTON_NOT_ENABLED_MESSAGE, playButtonEnabled);

        Thread.sleep(3000); // for visual confirmation
    }

    @Test
    public void isVideoMatchingToChosenFiltersTest_positive() throws InterruptedException {
        ChooseFromWorkoutsAndProgramsMenu workout = new ChooseFromWorkoutsAndProgramsMenu(driver);
        workout.chooseFromWorkoutDropdownMenu(0);
        ChooseFilters video = new ChooseFilters(driver);
        video.checkFiltersAndChooseVideo();
        GetWorkoutDetails details = new GetWorkoutDetails(driver);
        details.makeListOfWorkoutDetails();
        boolean timeMatches = details.isTimeBetweenChosenValues();
        boolean allFiltersMatch = details.checkChosenFilters(video.getListOfChosenFilters());

        Assert.assertTrue(CHOSEN_FILTERS_DOES_NOT_MATCH_MESSAGE, timeMatches && allFiltersMatch);
        Thread.sleep(3000); // for visual confirmation
    }


    @Test
    public void areAddedItemsInCartTest_positive() throws InterruptedException {
        ChooseFromWorkoutsAndProgramsMenu workout = new ChooseFromWorkoutsAndProgramsMenu(driver);
        workout.chooseFromWorkoutDropdownMenu(1);
        AddWorkoutProgramsToCart cart = new AddWorkoutProgramsToCart(driver);
        cart.addingToCart();
        Assert.assertEquals(LISTS_ARE_NOT_EQUAL_MESSAGE, cart.getListOfNamesOrderedPrograms(), cart.getListOfNamesOrderedProgramsCart());

        Thread.sleep(3000); // for visual confirmation
    }
}
