package checkStreamingExample;

import java.util.HashMap;

import it.unifi.facpl.lib.interfaces.IPepAction;

@SuppressWarnings("all")
public class PEPAction{

    public static HashMap<String, Class<? extends IPepAction>> getPepActions() {
		/*
		 * Set your own pep action e.g. HashMap<String,Class<? extends
		 * IPepAction>> pepAction = new HashMap<String,Class<? extends
		 * IPepAction>>(); pepAction.put("action", Action.class); return
		 * pepAction;
		 */
		return null;
	}
	
}
