package checkStreamingExample;

import java.util.HashMap;

import it.unifi.facpl.lib.enums.FacplStatusType;
import it.unifi.facpl.system.status.FacplStatus;
import it.unifi.facpl.system.status.StatusAttribute;

public class StatusStreaming {
private static FacplStatus status;

	public StatusStreaming() {
	}

	public FacplStatus getStatus() {
		if (status==null){
			HashMap<StatusAttribute, Object> attributes = new HashMap<StatusAttribute, Object>();
			attributes.put(new StatusAttribute("loginBob", FacplStatusType.STRING),"noLogin");
			attributes.put(new StatusAttribute("loginAlice", FacplStatusType.STRING),"noLogin");
			attributes.put(new StatusAttribute("passwordAlice", FacplStatusType.STRING), "123456");
			attributes.put(new StatusAttribute("passwordBob", FacplStatusType.STRING), "abcdef");
			attributes.put(new StatusAttribute("streamingAlice", FacplStatusType.BOOLEAN), false);
			attributes.put(new StatusAttribute("streamingBob", FacplStatusType.BOOLEAN), false);
			attributes.put(new StatusAttribute("passwordAlice", FacplStatusType.STRING), "123456");
			attributes.put(new StatusAttribute("passwordAlice", FacplStatusType.STRING), "123456");
			attributes.put(new StatusAttribute("commercialsBob", FacplStatusType.BOOLEAN), false);
			status = new FacplStatus(attributes, this.getClass().getName());
			return status;
		}
		return status;
	}
}
