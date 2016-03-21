/*******************************************************************************
 * Copyright (c) 2016 Andrea Margheri
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Andrea Margheri
 *******************************************************************************/
/**
 * 
 */
package it.unifi.facpl.system;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unifi.facpl.lib.context.AuthorisationPDP;
import it.unifi.facpl.lib.context.ContextRequest;
import it.unifi.facpl.lib.interfaces.IEvaluableAlgorithm;
import it.unifi.facpl.lib.policy.FacplPolicy;

/**
 * @author Andrea Margheri
 *
 */
public class PDP {

	private Class<? extends IEvaluableAlgorithm> algCombining;
	private List<FacplPolicy> policies;
	private Boolean extendedIndeterminate;

	public PDP(Class<? extends IEvaluableAlgorithm> algCombining, List<FacplPolicy> policies,
			Boolean extendedIndeterminate) {
		this.algCombining = algCombining;
		this.policies = policies;
		this.extendedIndeterminate = extendedIndeterminate;
	}

	/**
	 * PDP Evaluation
	 * 
	 * @param cxtReq
	 *            Request to Evaluate
	 */
	public AuthorisationPDP doAuthorisation(ContextRequest cxtReq) {

		Logger l = LoggerFactory.getLogger(PDP.class);

		l.debug("-------------------------------------");
		l.debug("PDP Evaluation of request: " + cxtReq.getRequest().getId() + " started...");

		try {

			// Invoking Combining Algorithm defining PDP
			Class<?> params[] = new Class[3];
			params[0] = List.class;
			params[1] = ContextRequest.class;
			params[2] = Boolean.class;

			Method eval = algCombining.getDeclaredMethod("evaluate", params);

			l.debug("Loading combining algorithm PDP: " + eval.getName());
			Object alg = algCombining.newInstance();

			AuthorisationPDP dr = new AuthorisationPDP();
			dr = (AuthorisationPDP) eval.invoke(alg, policies, cxtReq, extendedIndeterminate); // VIENE
																								// CHIAMATO
																								// IL
																								// COMBINING
																								// ALG
			dr.setId(cxtReq.getRequest().getId());

			l.debug("...PDP Evaluation of request " + cxtReq.getRequest().getId() + " completed. PDP decision: "
					+ dr.toString());

			l.debug("--------------------------------");

			return dr;

		} catch (Exception e) {
			l.debug("PDP catch unexpected exception");
			l.debug(e.getMessage().toString());
			return null;
		}

	}

}
