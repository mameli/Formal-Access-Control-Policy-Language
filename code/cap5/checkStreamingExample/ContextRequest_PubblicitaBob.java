package checkStreamingExample;

import java.util.ArrayList;
import java.util.HashMap;

import it.unifi.facpl.lib.context.ContextRequest_Status;
import it.unifi.facpl.lib.context.ContextStub_Default;
import it.unifi.facpl.lib.context.Request;
import it.unifi.facpl.lib.enums.FacplStatusType;
import it.unifi.facpl.lib.util.FacplDate;
import it.unifi.facpl.system.status.FacplStatus;
import it.unifi.facpl.system.status.StatusAttribute;

@SuppressWarnings("all")
public class ContextRequest_PubblicitaBob {

	private static ContextRequest_Status CxtReq;

	public static ContextRequest_Status getContextReq() {
		if (CxtReq != null) {
			return CxtReq;
		}
		// create map for each category
		HashMap<String, Object> req_category_attribute_name = new HashMap<String, Object>();
		HashMap<String, Object> req_category_attribute_action = new HashMap<String, Object>();
		// add attribute's values
		req_category_attribute_name.put("id", "Bob");
		req_category_attribute_action.put("id", "listenCommercials");
		// add attributes to request
		Request req = new Request("pubblicità bob");
		req.addAttribute("name", req_category_attribute_name);
		req.addAttribute("action", req_category_attribute_action);
		// context stub: default-one
		CxtReq = new ContextRequest_Status(req, ContextStub_Default.getInstance());
		StatusStreaming st = new StatusStreaming();
		CxtReq.setStatus(st.getStatus());
		return CxtReq;
	}
}
