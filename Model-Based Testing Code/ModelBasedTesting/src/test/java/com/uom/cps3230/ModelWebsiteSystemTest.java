package com.uom.cps3230;

import com.uom.cps3230.enums.ModelWebsiteStates;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GraphListener;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;

public class ModelWebsiteSystemTest implements FsmModel {
    private ModelWebsiteStates modelState;
    private boolean logOn, logOff, search, addProduct, removeProduct, checkout;
    private ModelWebsiteSystem sut;


    public ModelWebsiteStates getState() {
        return modelState;
    }

    public void reset(final boolean b) {
        modelState = ModelWebsiteStates.LOG_ON;
        logOn = true;
        logOff = false;
        search = false;
        addProduct = false;
        removeProduct = false;
        checkout = false;
        if (b) {
            sut = new ModelWebsiteSystem();
        }
    }

    public boolean logOutGuard() {
        return getState().equals(ModelWebsiteStates.LOG_ON);
    }
    public @Action void logOut() {
        sut.userLogsOff();

        logOn = false;
        logOff = true;
        modelState = ModelWebsiteStates.LOG_OFF;

        Assert.assertEquals("The model's logoff state doesn't match the SUT's state.", logOff, sut.isLogOff());
    }

    public boolean logInGuard() {
        return getState().equals(ModelWebsiteStates.LOG_OFF);
    }
    public @Action void logIn() {
        sut.userLogsOn();

        logOn = true;
        logOff = false;
        modelState = ModelWebsiteStates.LOG_ON;

        Assert.assertEquals("The model's logOn state doesn't match the SUT's state.", logOn, sut.isLogOn());
        Assert.assertEquals("The model's logOff state doesn't match the SUT's state.", logOff, sut.isLogOff());
    }

    public boolean searchGuard() {
        return getState().equals(ModelWebsiteStates.LOG_ON);
    }
    public @Action void search() {
        sut.userSearches();

        search = true;
        modelState = ModelWebsiteStates.SEARCH;

        Assert.assertEquals("The model's search state doesn't match the SUT's state.", search, sut.isSearch());
    }


    public boolean selectProductGuard() {
        return getState().equals(ModelWebsiteStates.SEARCH);
    }
    public @Action void selectProduct() {
        sut.userSelectsProduct();

        addProduct = true;
        modelState = ModelWebsiteStates.ADD_PRODUCT;


    }


    public boolean findProductsAfterAddingGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }
    public @Action void findProductsAfterAdding() {
        sut.userSearchingAfterAddingAProduct();

        addProduct = false;
        search = true;
        modelState = ModelWebsiteStates.SEARCH;


        Assert.assertEquals("The model's search state doesn't match the SUT's state.", search, sut.isSearch());
    }

    public boolean removeProductsGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }
    public @Action void removeProducts() {
        sut.userRemovesProducts();

        addProduct = false;
        removeProduct = true;
        modelState = ModelWebsiteStates.REMOVE_PRODUCT;

        Assert.assertEquals("The model's add product  state doesn't match the SUT's state.", addProduct, sut.isAddProduct());


    }

    public boolean findProductsAfterRemovingGuard() {
        return getState().equals(ModelWebsiteStates.REMOVE_PRODUCT);
    }
    public @Action
    void findProductsAfterRemoving() {
        sut.userSearchesAfterRemovingAProduct();

        search = true;
        removeProduct = false;
        modelState = ModelWebsiteStates.SEARCH;

        Assert.assertEquals("The model's search state doesn't match the SUT's state.", addProduct, sut.isAddProduct());


    }

    public boolean checkoutAfterRemoveGuard() {
        return getState().equals(ModelWebsiteStates.REMOVE_PRODUCT);
    }
    public @Action void checkoutAfterRemove() {
        sut.userChecksOut();

        removeProduct = false;
        checkout = true;
        modelState = ModelWebsiteStates.CHECKOUT;

        Assert.assertEquals("The model's add product  state doesn't match the SUT's state.", removeProduct, sut.isRemoveProduct());


    }

    public boolean checkoutAfterAddingAProductGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }
    public @Action void checkoutAfterAddingProducts() {
        sut.userChecksOutAfterAddingAProduct();

        addProduct = false;
        checkout = true;
        modelState = ModelWebsiteStates.CHECKOUT;

        Assert.assertEquals("The model's add product  state doesn't match the SUT's state.", addProduct, sut.isAddProduct());


    }

    @Test
    public void ModelBasedSystemRunner() throws FileNotFoundException {
        final Tester tester = new GreedyTester(new ModelWebsiteSystemTest());
        tester.setRandom(new Random());
        final GraphListener graphListener = tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(500);
        tester.printCoverage();
    }
}

