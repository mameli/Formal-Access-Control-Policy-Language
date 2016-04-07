package it.unifi.facpl.lib.context;

import java.util.LinkedList;

import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.system.status.FacplStatus;

public class AbstractFulfilledObligationCheck extends AbstractFulfilledObligation {

	public AbstractFulfilledObligationCheck(Effect effect) {
		this.type = ObligationType.M;
		this.evaluatedOn = effect;
		this.arguments = new LinkedList<Object>();
	}
	@Override
	public Object getPepAction() {
		
		return null;
	}

	@Override
	public AbstractFulfilledObligation evaluateObl(FacplStatus status) throws Throwable {
		
		return null;
	}

}
