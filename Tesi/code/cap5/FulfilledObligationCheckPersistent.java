package it.unifi.facpl.lib.context;

import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.lib.policy.ExpressionBooleanTree;
import it.unifi.facpl.lib.policy.ExpressionFunction;

public class FulfilledObligationCheckPersistent extends FulfilledObligationNumCheck {
	/*
	 * same constructor of FulfilledObligationCheck,
	 * but expiration is initialized with -1
	 */
	public FulfilledObligationCheckPersistent(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionBooleanTree status_target) {
		super(evaluatedOn, target, status_target, -1);
	}

	public FulfilledObligationCheckPersistent(Effect evaluatedOn, ExpressionFunction target,
			ExpressionBooleanTree status_target) {
		super(evaluatedOn, target, status_target, -1);
	}

	public FulfilledObligationCheckPersistent(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionFunction status_target) {
		super(evaluatedOn, target, status_target, -1);
	}

	public FulfilledObligationCheckPersistent(Effect evaluatedOn, ExpressionFunction target,
			ExpressionFunction status_target) {
		super(evaluatedOn, target, status_target, -1);
	}

	public void setExpired() {
		this.hasExpired = false;
	}

	public boolean hasExpired() {
		return false;
	}

	public void subExpiration(int i) {
		// do nothing
	}

	@Override
	public String toString() {
		return "target:  " + target.toString() + "  " + "status: " + status_target.toString() + "  PERSISTENT";
	}

}
