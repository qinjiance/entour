/**
 * ActivityBookFlowCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.qinjiance.tourist.support.tourico.activity;


/**
 *  ActivityBookFlowCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ActivityBookFlowCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ActivityBookFlowCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ActivityBookFlowCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for searchActivityByActivityIds method
     * override this method for handling normal response from searchActivityByActivityIds operation
     */
    public void receiveResultsearchActivityByActivityIds(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByActivityIdsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from searchActivityByActivityIds operation
     */
    public void receiveErrorsearchActivityByActivityIds(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for activityPreBook method
     * override this method for handling normal response from activityPreBook operation
     */
    public void receiveResultactivityPreBook(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityPreBookResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from activityPreBook operation
     */
    public void receiveErroractivityPreBook(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for bookActivity method
     * override this method for handling normal response from bookActivity operation
     */
    public void receiveResultbookActivity(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.BookActivityResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from bookActivity operation
     */
    public void receiveErrorbookActivity(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getRGInfo method
     * override this method for handling normal response from getRGInfo operation
     */
    public void receiveResultgetRGInfo(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.GetRGInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getRGInfo operation
     */
    public void receiveErrorgetRGInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for searchActivityByDestinationIds method
     * override this method for handling normal response from searchActivityByDestinationIds operation
     */
    public void receiveResultsearchActivityByDestinationIds(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByDestinationIdsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from searchActivityByDestinationIds operation
     */
    public void receiveErrorsearchActivityByDestinationIds(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getActivityDetails method
     * override this method for handling normal response from getActivityDetails operation
     */
    public void receiveResultgetActivityDetails(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.GetActivityDetailsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getActivityDetails operation
     */
    public void receiveErrorgetActivityDetails(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for searchActivityByAirPortCode method
     * override this method for handling normal response from searchActivityByAirPortCode operation
     */
    public void receiveResultsearchActivityByAirPortCode(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByAirPortCodeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from searchActivityByAirPortCode operation
     */
    public void receiveErrorsearchActivityByAirPortCode(java.lang.Exception e) {
    }
}
