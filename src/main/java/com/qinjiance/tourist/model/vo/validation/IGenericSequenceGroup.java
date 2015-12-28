/**
 * 
 */
package com.qinjiance.tourist.model.vo.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 验证顺序指示接口.
 * 
 * @author "Jiance Qin"
 * 
 * @date 2014年1月14日
 * 
 * @time 下午5:45:29
 * 
 */
@GroupSequence(value = { INotNullGroup.class, IConstraintGroup.class, Default.class })
public interface IGenericSequenceGroup {

}
