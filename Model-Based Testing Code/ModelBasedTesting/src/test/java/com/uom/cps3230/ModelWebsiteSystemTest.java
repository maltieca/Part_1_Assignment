package com.uom.cps3230;


import com.uom.cps3230.enums.ModelWebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.junit.Assert;

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

    public boolean logInGuard() {
        return getState().equals(ModelWebsiteStates.LOG_OFF);
    }

    public boolean searchGuard() {
        return getState().equals(ModelWebsiteStates.LOG_ON);
    }

    public boolean selectProductGuard() {
        return getState().equals(ModelWebsiteStates.SEARCH);
    }

    public boolean findProductsAfterAddingGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }

    public boolean removeProductsGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }

    public boolean findProductsAfterRemovingGuard() {
        return getState().equals(ModelWebsiteStates.REMOVE_PRODUCT);
    }

    public boolean checkoutAfterRemoveGuard() {
        return getState().equals(ModelWebsiteStates.REMOVE_PRODUCT);
    }

    public boolean checkoutAfterAddingAProductGuard() {
        return getState().equals(ModelWebsiteStates.ADD_PRODUCT);
    }


}

