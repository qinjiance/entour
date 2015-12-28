/**
 * IHotelFlow_SearchHotels_WSFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package com.qinjiance.tourist.support.tourico.hotel;

public class IHotelFlow_SearchHotels_WSFault_FaultMessage extends java.lang.Exception {
    private static final long serialVersionUID = 1448296970604L;
    private com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.WSFaultE faultMessage;

    public IHotelFlow_SearchHotels_WSFault_FaultMessage() {
        super("IHotelFlow_SearchHotels_WSFault_FaultMessage");
    }

    public IHotelFlow_SearchHotels_WSFault_FaultMessage(java.lang.String s) {
        super(s);
    }

    public IHotelFlow_SearchHotels_WSFault_FaultMessage(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public IHotelFlow_SearchHotels_WSFault_FaultMessage(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.WSFaultE msg) {
        faultMessage = msg;
    }

    public com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.WSFaultE getFaultMessage() {
        return faultMessage;
    }
}
