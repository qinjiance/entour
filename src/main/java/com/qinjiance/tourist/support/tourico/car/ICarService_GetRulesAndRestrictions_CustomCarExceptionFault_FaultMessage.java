/**
 * ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.qinjiance.tourist.support.tourico.car;

public class ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage
    extends java.lang.Exception {
    private static final long serialVersionUID = 1449409321292L;
    private com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CarWebServiceExceptionE faultMessage;

    public ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage() {
        super(
            "ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage");
    }

    public ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage(
        java.lang.String s) {
        super(s);
    }

    public ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage(
        java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CarWebServiceExceptionE msg) {
        faultMessage = msg;
    }

    public com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CarWebServiceExceptionE getFaultMessage() {
        return faultMessage;
    }
}
