package it.unifi.facpl.lib.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ExpressionValue;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.lib.enums.StandardDecision;
import it.unifi.facpl.lib.policy.ExpressionBooleanTree;
import it.unifi.facpl.lib.policy.ExpressionFunction;
import it.unifi.facpl.system.status.FacplStatus;

public class FulfilledObligationNumCheck extends AbstractFulfilledObligationCheck implements Cloneable {

	protected ExpressionBooleanTree target;
	protected ExpressionBooleanTree status_target;
	protected int expiration;
	protected boolean hasExpired;
	protected int originalExpiration;
	/*
	 * four constructor for all combination of Expression:
	 * 1: ExpressionFunction, ExpressionFunction
	 * 2: ExpressionBooleanTree, ExpressionBooleanTree
	 * 3: ExpressionBooleanTree, ExpressionFunction
	 * 4: ExpressionFunction, ExpresisonBooleanTree
	 */
	public FulfilledObligationNumCheck(Effect evaluatedOn, ExpressionFunction target,
			ExpressionFunction status_target, int expiration) {
		super(evaluatedOn);
		this.target = new ExpressionBooleanTree(target);
		this.target = new ExpressionBooleanTree(status_target);
		this.expiration = expiration;
		this.originalExpiration = expiration;
		this.hasExpired = false;
	}

	public FulfilledObligationNumCheck(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionBooleanTree status_target, int expiration) {
		super(evaluatedOn);
		this.target = target;
		this.status_target = status_target;
		this.expiration = expiration;
		this.hasExpired = false;
		this.originalExpiration = expiration;
	}

	public FulfilledObligationNumCheck(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionFunction status_target, int expiration) {
		super(evaluatedOn);
		this.target = target;
		this.target = new ExpressionBooleanTree(status_target);
		this.expiration = expiration;
		this.hasExpired = false;
		this.originalExpiration = expiration;
	}

	public FulfilledObligationNumCheck(Effect evaluatedOn, ExpressionFunction target,
			ExpressionBooleanTree status_target, int expiration) {
		super(evaluatedOn);
		this.target = new ExpressionBooleanTree(target);
		this.status_target = status_target;
		this.expiration = expiration;
		this.hasExpired = false;
		this.originalExpiration = expiration;
	}

	@Override
	public Object getPepAction() {
		return null;
	}

	@Override
	public AbstractFulfilledObligation evaluateObl(FacplStatus status) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public StandardDecision getObligationResult(ContextRequest cxtRequest) {

		/*
		 * This method evaluate target and status target and according to result return:
		 * IF target and status target TRUE -> EVALUATED_ON [PERMIT OR DENY]
		 * IF target or status target FALSE -> NOT APPLICABLE
		 * IF target or status target BOTTOM -> NOT APPLICABLE
		 * IF target or status target ERROR -> INDETERMINATE
		 */
		Logger l = LoggerFactory.getLogger(FulfilledObligationNumCheck.class);
		l.debug("EVALUATING FULFILLEDOBLIGATION-CHECK: " + "\r\n");

		ExpressionValue result_target, result_target_status;
		result_target = null;
		result_target_status = null;
		if (this.getExpiration() > 0 || this.getExpiration()== -1) {
			//if not expired -> evaluate target
			l.debug("EVALUATING EXPRESSION OF OBLIGATION: " + "\r\n");
			result_target = target.evaluateExpressionTree(cxtRequest);
			result_target_status = status_target.evaluateExpressionTree(cxtRequest);
			this.subExpiration(1);
			l.debug("RESULT_TARGET: " + result_target + " || RESULT_TARGET_STATUS: " + result_target_status);
		} else if (this.getExpiration() == 0) {
			l.debug("OBLIGATION CHECK HAS EXPIRED");
			result_target = ExpressionValue.ERROR;
			result_target_status = ExpressionValue.ERROR;
		}

		if (result_target == ExpressionValue.TRUE && result_target_status == ExpressionValue.TRUE) {
			//true and true -> [permit or deny]
			l.debug("DECISION CHECK: TRUE");
			if (evaluatedOn.equals(Effect.PERMIT)) {
				l.debug("RETURN PERMIT");
				return StandardDecision.PERMIT;
			} else {
				l.debug("RETURN DENY");
				return StandardDecision.DENY;
			}
		} else if (result_target == ExpressionValue.BOTTOM || result_target_status == ExpressionValue.BOTTOM) {
			//bottom -> not applicable
			l.debug("DECISION CHECK: BOTTOM");
			l.debug("RETURN NOT APPLICABLE");
			return StandardDecision.NOT_APPLICABLE;
		} else if (result_target == ExpressionValue.ERROR || result_target_status == ExpressionValue.ERROR) {
			//error -> indeterminate
			l.debug("DECISION INDETERMINATE");
			return StandardDecision.INDETERMINATE;
		} else if (result_target == ExpressionValue.FALSE || result_target_status == ExpressionValue.FALSE) {
			//false -> not applicable
			l.debug("DECISION NOT APPLICABLE");
			return StandardDecision.NOT_APPLICABLE;
		}
		l.debug("RETURN INDETERMINATE");
		return StandardDecision.INDETERMINATE;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpired() {
		this.hasExpired = true;
	}

	public boolean hasExpired() {
		return this.hasExpired;
	}

	public void subExpiration(int i) {
		Logger l = LoggerFactory.getLogger(FulfilledObligationNumCheck.class);
		if (expiration == -1){
			l.debug("EXPIRATION PERSISTENT");
		}
		if (expiration > 0) {
			expiration -= i;
			l.debug("NEW EXPIRATION: " + this.toString());
			if (expiration == 0) {
				this.setExpired();
			}
		}
	}

	@Override
	public String toString() {
		return "target: " + target.toString() + "  " + "status:  " + status_target.toString() + "  EXPIRATION IN: "
				+ expiration;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		/*
		 * return the original obligation
		 */
		return new FulfilledObligationNumCheck(this.evaluatedOn, this.target, this.status_target,
				this.originalExpiration);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FulfilledObligationNumCheck) {
			FulfilledObligationNumCheck temp = (FulfilledObligationNumCheck) obj;
			return temp.target == this.target && temp.status_target == this.status_target;
		}
		return false;
	}

}
