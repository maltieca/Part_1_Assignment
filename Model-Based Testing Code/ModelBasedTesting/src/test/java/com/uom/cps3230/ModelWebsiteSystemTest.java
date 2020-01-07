package com.uom.cps3230;


import com.uom.cps3230.enums.ModelWebsiteStates;
import nz.ac.waikato.modeljunit.FsmModel;

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
}

