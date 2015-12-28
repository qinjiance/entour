/**
 * IActivityBookFlow_GetRGInfo_WSFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.qinjiance.tourist.support.tourico.activity;

public class IActivityBookFlow_GetRGInfo_WSFault_FaultMessage extends java.lang.Exception {
    private static final long serialVersionUID = 1450024186353L;
    private com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.WSFaultE faultMessage;

    public IActivityBookFlow_GetRGInfo_WSFault_FaultMessage() {
        super("IActivityBookFlow_GetRGInfo_WSFault_FaultMessage");
    }

    public IActivityBookFlow_GetRGInfo_WSFault_FaultMessage(java.lang.String s) {
        super(s);
    }

    public IActivityBookFlow_GetRGInfo_WSFault_FaultMessage(
        java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public IActivityBookFlow_GetRGInfo_WSFault_FaultMessage(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.WSFaultE msg) {
        faultMessage = msg;
    }

    public com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.WSFaultE getFaultMessage() {
        return faultMessage;
    }
}
