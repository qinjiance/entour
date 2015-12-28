/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.List;

import com.qinjiance.tourist.model.vo.CarDestinationVo;
import com.qinjiance.tourist.model.vo.DestinationVo;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-8-10
 * 
 * @Time 下午12:16:48
 * 
 */
public interface IDestinationManager {

	public List<DestinationVo> getByActDes(String des);

	public List<DestinationVo> getByDes(String des);

	public List<CarDestinationVo> getByCarDes(String des);
}
