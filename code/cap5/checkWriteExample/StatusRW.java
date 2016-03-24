package checkReadWriteExample;

import java.util.HashMap;

import it.unifi.facpl.lib.enums.FacplStatusType;
import it.unifi.facpl.system.status.FacplStatus;
import it.unifi.facpl.system.status.StatusAttribute;

public class StatusRW {
private static FacplStatus status;
	
	public StatusRW() {
	}
	
	public FacplStatus getStatus() {
		if (status==null){
			HashMap<StatusAttribute, Object> attributes = new HashMap<StatusAttribute, Object>();
			attributes.put(new StatusAttribute("accessCounter", FacplStatusType.INT), 0);
			attributes.put(new StatusAttribute("isWritingThesis", FacplStatusType.BOOLEAN),Boolean.FALSE);
			status = new FacplStatus(attributes, this.getClass().getName());
			return status;
		}
		return status;
	}
}
