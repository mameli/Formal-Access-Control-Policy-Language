package it.unifi.facpl.lib.context;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ExpressionValue;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.lib.enums.StandardDecision;
import it.unifi.facpl.lib.policy.ExpressionBooleanTree;
import it.unifi.facpl.lib.policy.ExpressionFunction;
import it.unifi.facpl.lib.util.FacplDate;
import it.unifi.facpl.system.status.FacplStatus;

public class FulfilledObligationTimeCheck extends AbstractFulfilledObligationCheck implements Cloneable {

	protected ExpressionBooleanTree target;
	protected ExpressionBooleanTree status_target;
	protected FacplDate expirationTime;
	protected boolean hasExpired;
	protected FacplDate originalExpiration;
	/*
	 * four constructor for all combination of Expression:
	 * 1: ExpressionFunction, ExpressionFunction
	 * 2: ExpressionBooleanTree, ExpressionBooleanTree
	 * 3: ExpressionBooleanTree, ExpressionFunction
	 * 4: ExpressionFunction, ExpresisonBooleanTree
	 */
	public FulfilledObligationTimeCheck(Effect evaluatedOn, ExpressionFunction target,
			ExpressionFunction status_target, FacplDate expiration) {
		super(evaluatedOn);
		this.target = new ExpressionBooleanTree(target);
		this.target = new ExpressionBooleanTree(status_target);

		this.expirationTime = new FacplDate();
		System.err.println("EXPIRATION TIME SECOND "+expiration.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+expiration.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+expiration.getDate().get(Calendar.HOUR));
		System.err.println("START TIME "+this.expirationTime.toString());
		this.expirationTime.getDate().add(Calendar.SECOND, expiration.getDate().get(Calendar.SECOND));
		this.expirationTime.getDate().add(Calendar.MINUTE, expiration.getDate().get(Calendar.MINUTE));
		this.expirationTime.getDate().add(Calendar.HOUR, expiration.getDate().get(Calendar.HOUR));
		System.err.println("EXPIRATION TIME SECOND "+this.expirationTime.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+this.expirationTime.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+this.expirationTime.getDate().get(Calendar.HOUR));

		this.originalExpiration = expiration;
		this.hasExpired = false;
	}

	public FulfilledObligationTimeCheck(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionBooleanTree status_target, FacplDate expiration) {
		super(evaluatedOn);
		this.target = target;
		this.status_target = status_target;
		this.expirationTime = new FacplDate();
		System.err.println("EXPIRATION TIME SECOND "+expiration.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+expiration.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+expiration.getDate().get(Calendar.HOUR));
		System.err.println("START TIME "+this.expirationTime.toString());
		this.expirationTime.getDate().add(Calendar.SECOND, expiration.getDate().get(Calendar.SECOND));
		this.expirationTime.getDate().add(Calendar.MINUTE, expiration.getDate().get(Calendar.MINUTE));
		this.expirationTime.getDate().add(Calendar.HOUR, expiration.getDate().get(Calendar.HOUR));
		System.err.println("EXPIRATION TIME SECOND "+this.expirationTime.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+this.expirationTime.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+this.expirationTime.getDate().get(Calendar.HOUR));
		this.originalExpiration = expiration;
		this.hasExpired = false;
	}

	public FulfilledObligationTimeCheck(Effect evaluatedOn, ExpressionBooleanTree target,
			ExpressionFunction status_target, FacplDate expiration) {
		super(evaluatedOn);
		this.target = target;
		this.target = new ExpressionBooleanTree(status_target);
		this.expirationTime = new FacplDate();
		System.err.println("EXPIRATION TIME SECOND "+expiration.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+expiration.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+expiration.getDate().get(Calendar.HOUR));
		System.err.println("START TIME "+this.expirationTime.toString());
		this.expirationTime.getDate().add(Calendar.SECOND, expiration.getDate().get(Calendar.SECOND));
		this.expirationTime.getDate().add(Calendar.MINUTE, expiration.getDate().get(Calendar.MINUTE));
		this.expirationTime.getDate().add(Calendar.HOUR, expiration.getDate().get(Calendar.HOUR));
		System.err.println("EXPIRATION TIME SECOND "+this.expirationTime.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+this.expirationTime.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+this.expirationTime.getDate().get(Calendar.HOUR));
		this.originalExpiration = expiration;
		this.hasExpired = false;
	}

	public FulfilledObligationTimeCheck(Effect evaluatedOn, ExpressionFunction target,
			ExpressionBooleanTree status_target, FacplDate expiration) {
		super(evaluatedOn);
		this.target = new ExpressionBooleanTree(target);
		this.status_target = status_target;
		this.expirationTime = new FacplDate();
		System.err.println("EXPIRATION TIME SECOND "+expiration.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+expiration.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+expiration.getDate().get(Calendar.HOUR));
		System.err.println("START TIME "+this.expirationTime.toString());
		this.expirationTime.getDate().add(Calendar.SECOND, expiration.getDate().get(Calendar.SECOND));
		this.expirationTime.getDate().add(Calendar.MINUTE, expiration.getDate().get(Calendar.MINUTE));
		this.expirationTime.getDate().add(Calendar.HOUR, expiration.getDate().get(Calendar.HOUR));
		System.err.println("EXPIRATION TIME SECOND "+this.expirationTime.getDate().get(Calendar.SECOND));
		System.err.println("EXPIRATION TIME MINUTE "+this.expirationTime.getDate().get(Calendar.MINUTE));
		System.err.println("EXPIRATION TIME HOUR "+this.expirationTime.getDate().get(Calendar.HOUR));
		this.originalExpiration = expiration;
		this.hasExpired = false;
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
		Logger l = LoggerFactory.getLogger(FulfilledObligationTimeCheck.class);
		l.debug("EVALUATING FULFILLEDOBLIGATION-CHECK: " + "\r\n");

		ExpressionValue result_target, result_target_status;
		result_target = null;
		result_target_status = null;
		FacplDate TIME = new FacplDate();
		if (TIME.before(this.getExpiration())) {
			l.debug("TIME "+TIME.toString());
			l.debug("NOT EXPIRED");
			//if not expired -> evaluate target
			l.debug("EVALUATING EXPRESSION OF OBLIGATION: " + "\r\n");
			result_target = target.evaluateExpressionTree(cxtRequest);
			result_target_status = status_target.evaluateExpressionTree(cxtRequest);
			this.checkExpiration();
			l.debug("RESULT_TARGET: " + result_target + " || RESULT_TARGET_STATUS: " + result_target_status);
		} else if (TIME.after(this.getExpiration()) || TIME.equals(this.getExpiration()) ) {
			this.checkExpiration();
			l.debug("TIME "+TIME.toString());
			l.debug("OBLIGATION CHECK HAS EXPIRED");
			this.checkExpiration();
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

	public FacplDate getExpiration() {
		return expirationTime;
	}

	public void setExpired() {
		this.hasExpired = true;
	}

	public boolean hasExpired() {
		return this.hasExpired;
	}


	public void checkExpiration() {
		FacplDate TIME = new FacplDate();
		Logger l = LoggerFactory.getLogger(FulfilledObligationTimeCheck.class);
		timeDiff=this.expirationTime.getDate().get(Calendar.SECOND)
											-TIME.getDate().get(Calendar.SECOND);
		if (TIME.before(this.expirationTime)) {
			l.debug("TIME TO EXPIRATION: " + timeDiff);
		}
		else if (this.expirationTime.after(TIME)) {
			l.debug("TIME TO EXPIRATION: " + timeDiff);
				this.setExpired();
		}
		else if (this.expirationTime.equals(TIME)) {
			l.debug("TIME TO EXPIRATION: " + timeDiff);
				this.setExpired();
		}
	}

	@Override
	public String toString() {
		FacplDate TIME = new FacplDate();
		int timeDifference = this.expirationTime.getDate().get(Calendar.SECOND)-TIME.getDate().get(Calendar.SECOND);
		return "target: " + target.toString() + "\r\n" + "status: " + status_target.toString() + "\r\n TIME TO EXPIRATION: "
				+ timeDifference;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		/*
		 * return the original obligation
		 */
		return new FulfilledObligationTimeCheck(this.evaluatedOn, this.target, this.status_target,
				this.originalExpiration);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FulfilledObligationTimeCheck) {
			FulfilledObligationTimeCheck temp = (FulfilledObligationTimeCheck) obj;
			return temp.target == this.target && temp.status_target == this.status_target;
		}
		return false;
	}

}
