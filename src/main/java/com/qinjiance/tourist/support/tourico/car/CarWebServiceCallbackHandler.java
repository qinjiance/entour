/**
 * CarWebServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.qinjiance.tourist.support.tourico.car;


/**
 *  CarWebServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class CarWebServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public CarWebServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public CarWebServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for cancelCar method
     * override this method for handling normal response from cancelCar operation
     */
    public void receiveResultcancelCar(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CancelCarResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from cancelCar operation
     */
    public void receiveErrorcancelCar(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getRulesAndRestrictions method
     * override this method for handling normal response from getRulesAndRestrictions operation
     */
    public void receiveResultgetRulesAndRestrictions(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.GetRulesAndRestrictionsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getRulesAndRestrictions operation
     */
    public void receiveErrorgetRulesAndRestrictions(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for searchCars method
     * override this method for handling normal response from searchCars operation
     */
    public void receiveResultsearchCars(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from searchCars operation
     */
    public void receiveErrorsearchCars(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for selectStations method
     * override this method for handling normal response from selectStations operation
     */
    public void receiveResultselectStations(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SelectStationsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from selectStations operation
     */
    public void receiveErrorselectStations(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getRGInfo method
     * override this method for handling normal response from getRGInfo operation
     */
    public void receiveResultgetRGInfo(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.GetRGInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getRGInfo operation
     */
    public void receiveErrorgetRGInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for bookCar method
     * override this method for handling normal response from bookCar operation
     */
    public void receiveResultbookCar(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.BookCarReponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from bookCar operation
     */
    public void receiveErrorbookCar(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for searchCarsByAirportCode method
     * override this method for handling normal response from searchCarsByAirportCode operation
     */
    public void receiveResultsearchCarsByAirportCode(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarsByAirportCodeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from searchCarsByAirportCode operation
     */
    public void receiveErrorsearchCarsByAirportCode(java.lang.Exception e) {
    }
}
