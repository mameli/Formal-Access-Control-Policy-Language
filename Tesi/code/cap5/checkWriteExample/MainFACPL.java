package checkReadWriteExample;


import java.util.LinkedList;

import it.unifi.facpl.lib.algorithm.check.DenyOverridesCheck;
import it.unifi.facpl.lib.algorithm.check.DenyOverridesGreedyCheck;
import it.unifi.facpl.lib.context.AuthorisationPDP;
import it.unifi.facpl.lib.context.AuthorisationPEP;
import it.unifi.facpl.lib.context.ContextRequest_Status;
import it.unifi.facpl.lib.enums.EnforcementAlgorithm;
import it.unifi.facpl.lib.policy.FacplPolicy;
import it.unifi.facpl.lib.util.exception.MissingAttributeException;
import it.unifi.facpl.system.PDP;
import it.unifi.facpl.system.PEP;
import it.unifi.facpl.system.PEPCheck;
import it.unifi.facpl.system.status.FacplStatus;
import  java.io.*;


@SuppressWarnings("all")
public class MainFACPL {

	private PDP pdp;
	private PEPCheck pep;
	private static FacplStatus status;

	public MainFACPL(boolean Check) throws MissingAttributeException {
		if (Check==true){
		// defined list of policies included in the PDP
		LinkedList<FacplPolicy> policies = new LinkedList<FacplPolicy>();
		policies.add(new PolicySet_ReadWriteCheck());
		this.pdp = new PDP(it.unifi.facpl.lib.algorithm.PermitUnlessDenyGreedy.class, policies, false);

		this.pep = new PEPCheck(EnforcementAlgorithm.DENY_BIASED, new DenyOverridesCheck(), this.pdp);

		this.pep.addPEPActions(PEPAction.getPepActions());
		}else{
			// defined list of policies included in the PDP
			LinkedList<FacplPolicy> policies = new LinkedList<FacplPolicy>();
			policies.add(new PolicySet_ReadWrite());
			this.pdp = new PDP(it.unifi.facpl.lib.algorithm.PermitUnlessDenyGreedy.class, policies, false);

			this.pep = new PEPCheck(EnforcementAlgorithm.DENY_BIASED, new DenyOverridesCheck(), this.pdp);

			this.pep.addPEPActions(PEPAction.getPepActions());
		}
	}
	public static void main(String[] args) throws MissingAttributeException {
		// log
		StringBuffer result = new StringBuffer();
		
		LinkedList<ContextRequest_Status> requests = new LinkedList<ContextRequest_Status>();
		LinkedList<ContextRequest_Status> requests2 = new LinkedList<ContextRequest_Status>();

		MainFACPL system = new MainFACPL(true);
		MainFACPL systemNoCheck = new MainFACPL(false);
		long start;
		long end;
		try {
			PrintWriter writer = new PrintWriter("/Users/mameli/Desktop/testNoCheck.txt", "UTF-8");
			for (int k=1;k<=100;k++){
				start=System.currentTimeMillis();
				for (ContextRequest_Status rcxt : requests) {
					result.append(systemNoCheck.pep.doAuthorisation(rcxt));
				}
				end=System.currentTimeMillis();
				writer.println(end-start);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter writer = new PrintWriter("/Users/mameli/Desktop/testCheck.txt", "UTF-8");
			for (int k=1;k<=100;k++){
				start=System.currentTimeMillis();
				for (ContextRequest_Status rcxt : requests) {
					result.append(system.pep.doAuthorisation(rcxt));
				}
				end=System.currentTimeMillis();
				writer.println(end-start);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter writer = new PrintWriter("/Users/mameli/Desktop/testCheck2.txt", "UTF-8");
			for (int k=1;k<=100;k++){
				start=System.currentTimeMillis();
				for (ContextRequest_Status rcxt : requests2) {
					startR=System.currentTimeMillis();
					result.append(system.pep.doAuthorisation(rcxt));
					endR=System.currentTimeMillis();
				}
				end=System.currentTimeMillis();
				writer.println(end-start);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter writer = new PrintWriter("/Users/mameli/Desktop/testNoCheck2.txt", "UTF-8");
			for (int k=1;k<=100;k++){
				start=System.currentTimeMillis();
				for (ContextRequest_Status rcxt : requests2) {
					startR=System.currentTimeMillis();
					result.append(systemNoCheck.pep.doAuthorisation(rcxt));
					endR=System.currentTimeMillis();
				}
				end=System.currentTimeMillis();
				writer.println(end-start);
				//result.append("\ntime for all requests "+(end-start));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println(result.toString());
	}

	public PDP getPdp() {
		return pdp;
	}

	public PEP getPep() {
		return pep;
	}

}
