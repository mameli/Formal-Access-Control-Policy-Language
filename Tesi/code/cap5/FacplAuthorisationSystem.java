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
package it.unifi.facpl.system;

import java.util.HashMap;

import it.unifi.facpl.lib.context.AuthorisationPDP;
import it.unifi.facpl.lib.context.AuthorisationPEP;
import it.unifi.facpl.lib.context.ContextRequest;
import it.unifi.facpl.lib.context.ContextRequest_Status;
import it.unifi.facpl.lib.interfaces.IPepAction;

/**
 * 
 * @author Andrea Margheri Standard PDP supporting top-level combining
 *         algorithm. (Standard authorisation decision is returned) Standard PEP
 *         supporting enforcement of PDP authorisation
 * 
 */
public class FacplAuthorisationSystem {

	private PDP pdp;
	private PEP pep;

	public FacplAuthorisationSystem(PDP pdp, PEP pep) {
		this.pdp = pdp;
		this.pep = pep;
	}

	/**
	 * PDP Enforcement
	 * 
	 * @param cxtReq
	 *            Request to authorise
	 * @return
	 */
	public AuthorisationPDP doAuthorisation(ContextRequest cxtReq) {

		return this.pdp.doAuthorisation(cxtReq);

	}

	/**
	 * PEP Enforcement
	 * 
	 * @param authPDP
	 *            Authorisation to enforce
	 * @return
	 */
	public AuthorisationPEP doEnforcement(AuthorisationPDP authPDP, ContextRequest_Status cxtReq) {

		return this.pep.doEnforcement(authPDP, cxtReq);

	}

	/**
	 * Initialise PEP Actions
	 * 
	 * @param classPepActions
	 */
	public void inizializePepActions(HashMap<String, Class<? extends IPepAction>> pepActions) {

		this.pep.addPEPActions(pepActions);

	}

}