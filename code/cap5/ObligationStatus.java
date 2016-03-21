package it.unifi.facpl.lib.policy;

import it.unifi.facpl.lib.context.AbstractFulfilledObligation;
import it.unifi.facpl.lib.context.FulfilledObligationStatus;
import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.lib.util.FacplDate;
import it.unifi.facpl.system.status.StatusAttribute;
import it.unifi.facpl.system.status.function.arithmetic.evaluator.IExpressionFunctionStatus;

public class ObligationStatus extends AbstractObligation {

	public ObligationStatus(IExpressionFunctionStatus pepAction, Effect evaluatedOn, ObligationType type,
			Object... args) {
		super(evaluatedOn, type, args);
		this.pepAction = pepAction;
		/*
		 * adding status argument to this obligation
		 */
		if (args != null) {
			for (Object ob : args) {
				if (ob instanceof StatusAttribute || ob instanceof Integer || ob instanceof Double
						|| ob instanceof Boolean || ob instanceof FacplDate || ob instanceof String) {
					argsStatus.add(ob);
				}
				argsFunction.add(ob);
			}
		}
	}

	@Override
	protected AbstractFulfilledObligation createObligation() {
		/*
		 * this method create a fulfilled obligation status
		 */
		AbstractFulfilledObligation obl = new FulfilledObligationStatus(this.evaluatedOn, this.typeObl,
				(IExpressionFunctionStatus) this.pepAction);
		if (!argsStatus.isEmpty()) {
			obl.addArgStatus(argsStatus);
		}
		return obl;
	}

}
