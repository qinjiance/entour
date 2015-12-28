/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.IEhCacheManager;
import com.qinjiance.tourist.mapper.CarDestinationMapper;
import com.qinjiance.tourist.mapper.DestinationMapper;
import com.qinjiance.tourist.model.po.CarDestination;
import com.qinjiance.tourist.model.po.Destination;
import com.qinjiance.tourist.model.vo.CarDestinationVo;
import com.qinjiance.tourist.model.vo.DestinationVo;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-8-10
 * 
 * @Time 下午12:26:24
 * 
 */
@Service
public class DestinationManager implements IDestinationManager {

	private static final Logger logger = LoggerFactory.getLogger(DestinationManager.class);

	private static final String SPLITER = ",";

	@Autowired
	private IEhCacheManager ehCacheManager;
	@Autowired
	private DestinationMapper destinationMapper;
	@Autowired
	private CarDestinationMapper carDestinationMapper;

	/**
	 * 
	 */
	public DestinationManager() {

	}

	@Override
	public List<DestinationVo> getByDes(String des) {
		if (StringUtils.isBlank(des)) {
			return null;
		}
		List<Destination> destinations = destinationMapper.getByAll(des.toLowerCase());
		if (destinations == null || destinations.isEmpty()) {
			return null;
		}
		Map<String, DestinationVo> cityMap = new HashMap<String, DestinationVo>();
		Map<String, DestinationVo> locationMap = new HashMap<String, DestinationVo>();
		StringBuilder labelSb = new StringBuilder();
		StringBuilder valueSb = new StringBuilder();
		for (Destination destination : destinations) {
			labelSb.delete(0, labelSb.length());
			valueSb.delete(0, valueSb.length());
			labelSb.append(destination.getCity());
			if (StringUtils.isNotBlank(destination.getState())) {
				labelSb.append(SPLITER).append(destination.getState());
			}
			labelSb.append(SPLITER).append(destination.getCountry());
			valueSb.append(destination.getCity()).append(SPLITER).append(destination.getCode());

			if (StringUtils.isNotBlank(destination.getLocation())) {
				labelSb.insert(0, SPLITER).insert(0, destination.getLocation());
				valueSb.insert(0, SPLITER).insert(0, destination.getLocation());
				locationMap.put(labelSb.toString(), new DestinationVo(labelSb.toString(), valueSb.toString()));
			} else {
				cityMap.put(labelSb.toString(), new DestinationVo(labelSb.toString(), valueSb.toString()));
			}
		}
		List<DestinationVo> vos = new ArrayList<DestinationVo>(cityMap.values());
		vos.addAll(locationMap.values());
		return vos;
	}

	@Override
	public List<CarDestinationVo> getByCarDes(String des) {
		if (StringUtils.isBlank(des)) {
			return null;
		}
		List<CarDestination> destinations = carDestinationMapper.getByAll(des.toLowerCase());
		if (destinations == null || destinations.isEmpty()) {
			return null;
		}
		Map<String, CarDestinationVo> airPortMap = new HashMap<String, CarDestinationVo>();
		Map<String, CarDestinationVo> desMap = new HashMap<String, CarDestinationVo>();
		StringBuilder labelSb = new StringBuilder();
		StringBuilder valueSb = new StringBuilder();
		for (CarDestination destination : destinations) {
			labelSb.delete(0, labelSb.length());
			valueSb.delete(0, valueSb.length());
			labelSb.append(destination.getStation()).append(SPLITER).append(destination.getCity());
			if (StringUtils.isNotBlank(destination.getState())) {
				labelSb.append(SPLITER).append(destination.getState());
			}
			labelSb.append(SPLITER).append(destination.getCountry());
			valueSb.append(destination.getDesId());

			if (StringUtils.isNotBlank(destination.getCode())) {
				valueSb.append(SPLITER).append(destination.getCode());
				airPortMap.put(labelSb.toString(), new CarDestinationVo(labelSb.toString(), valueSb.toString()));
			} else {
				desMap.put(labelSb.toString(), new CarDestinationVo(labelSb.toString(), valueSb.toString()));
			}
		}
		List<CarDestinationVo> vos = new ArrayList<CarDestinationVo>(airPortMap.values());
		vos.addAll(desMap.values());
		return vos;
	}

	@Override
	public List<DestinationVo> getByActDes(String des) {
		if (StringUtils.isBlank(des)) {
			return null;
		}
		List<Destination> destinations = destinationMapper.getByAll(des.toLowerCase());
		if (destinations == null || destinations.isEmpty()) {
			return null;
		}
		List<DestinationVo> vos = new ArrayList<DestinationVo>();
		StringBuilder labelSb = new StringBuilder();
		StringBuilder valueSb = new StringBuilder();
		for (Destination destination : destinations) {
			labelSb.delete(0, labelSb.length());
			valueSb.delete(0, valueSb.length());
			labelSb.append(destination.getCity());
			if (StringUtils.isNotBlank(destination.getState())) {
				labelSb.append(SPLITER).append(destination.getState());
			}
			labelSb.append(SPLITER).append(destination.getCountry());
			valueSb.append(destination.getDesId());

			if (StringUtils.isNotBlank(destination.getLocation())) {
				labelSb.insert(0, SPLITER).insert(0, destination.getLocation());
			}
			vos.add(new DestinationVo(labelSb.toString(), valueSb.toString()));
		}
		return vos;
	}

}
