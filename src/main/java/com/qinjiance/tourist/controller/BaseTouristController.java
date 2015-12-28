package com.qinjiance.tourist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.IUserManager;
import com.qinjiance.tourist.mapper.DestinationMapper;
import com.qinjiance.tourist.model.po.Destination;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-7-23
 * 
 * @Time 上午12:35:09
 * 
 */
public class BaseTouristController extends DateTimeBinderController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

}
