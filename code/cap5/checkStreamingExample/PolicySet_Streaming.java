package checkStreamingExample;


import it.unifi.facpl.lib.enums.Effect;
import it.unifi.facpl.lib.enums.ExprBooleanConnector;
import it.unifi.facpl.lib.enums.FacplStatusType;
import it.unifi.facpl.lib.enums.ObligationType;
import it.unifi.facpl.lib.policy.ExpressionBooleanTree;
import it.unifi.facpl.lib.policy.ExpressionFunction;
import it.unifi.facpl.lib.policy.ObligationCheck;
import it.unifi.facpl.lib.policy.ObligationStatus;
import it.unifi.facpl.lib.policy.PolicySet;
import it.unifi.facpl.lib.policy.Rule;
import it.unifi.facpl.lib.util.AttributeName;
import it.unifi.facpl.lib.util.FacplDate;
import it.unifi.facpl.system.status.StatusAttribute;
import it.unifi.facpl.system.status.functions.bool.FlagStatus;
import it.unifi.facpl.system.status.functions.strings.SetString;

public class PolicySet_Streaming extends PolicySet{

	public PolicySet_Streaming() {
		addId("Streaming_Policy");
		// Algorithm Combining
		addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
		// Target

		// Policy
		addPolicyElement(new PolicySet_LoginAlice());
		addPolicyElement(new PolicySet_LoginBob());
		addPolicyElement(new PolicySet_ListenAlice());
		addPolicyElement(new PolicySet_ListenBob());
		addPolicyElement(new PolicySet_pubBob());
	}

	private class PolicySet_LoginAlice extends PolicySet {


		public PolicySet_LoginAlice() {
			addId("LoginAlice_Policy");
			// Algorithm Combining
			addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
			// Target
			ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Alice",
					new AttributeName("name", "id"));
			ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "login",
					new AttributeName("action", "id"));
			ExpressionBooleanTree ebt= new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
			addTarget(ebt);
			// PolElements
			addPolicyElement(new Rule_Login());
			// Obligation
			addObligation(
					new ObligationStatus(
							new SetString(),
							Effect.PERMIT,
							ObligationType.M,
							new StatusAttribute("loginAlice", FacplStatusType.STRING), "PREMIUM")
						);
			addObligation(
					new ObligationStatus(
							new FlagStatus(),
							Effect.PERMIT,
							ObligationType.M,
							new StatusAttribute("streamingAlice", FacplStatusType.BOOLEAN), true)
						);
		}

		private class Rule_Login extends Rule {

			Rule_Login() {
				addId("LoginAlice_Rule");
				// Effect
				addEffect(Effect.PERMIT);
				addTarget(new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("passwordAlice", FacplStatusType.STRING),
						new AttributeName("password", "id")));
			}
		}
	}

	private class PolicySet_LoginBob extends PolicySet {


		public PolicySet_LoginBob() {
			addId("LoginBob_Policy");
			// Algorithm Combining
			addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
			// Target
			ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Bob",
					new AttributeName("name", "id"));
			ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "login",
					new AttributeName("action", "id"));
			ExpressionBooleanTree ebt= new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
			addTarget(ebt);
			// PolElements
			addPolicyElement(new Rule_LoginBob());
			// Obligation
			addObligation(
					new ObligationStatus(
							new SetString(),
							Effect.PERMIT,
							ObligationType.M,
							new StatusAttribute("loginBob", FacplStatusType.STRING), "STANDARD")
						);
			addObligation(
					new ObligationStatus(
							new FlagStatus(),
							Effect.PERMIT,
							ObligationType.M,
							new StatusAttribute("streamingBob", FacplStatusType.BOOLEAN), true)
						);
		}

		private class Rule_LoginBob extends Rule {

			Rule_LoginBob() {
				addId("LoginBob_Rule");
				// Effect
				addEffect(Effect.PERMIT);
				addTarget(new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("passwordBob", FacplStatusType.STRING),
						new AttributeName("password", "id")));
			}
		}
	}

	private class PolicySet_ListenAlice extends PolicySet {


		public PolicySet_ListenAlice() {
			addId("ListenAlice_Policy");
			// Algorithm Combining
			addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
			// Target
			ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "listen",
					new AttributeName("action", "id"));
			ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Alice",
					new AttributeName("name", "id"));
			ExpressionBooleanTree ebt = new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
			addTarget(ebt);
			// PolElements
			addPolicyElement(new Rule_Login());
			// Obligation
			addObligation(
					new ObligationCheck(Effect.PERMIT,
							new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Alice",
									new AttributeName("name", "id")),
							new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
											new StatusAttribute("streamingAlice", FacplStatusType.BOOLEAN),
									true)
							));
		}

		private class Rule_Login extends Rule {

			Rule_Login() {
				addId("ListenAlice_Rule");
				// Effect
				addEffect(Effect.PERMIT);

				addTarget(new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("loginAlice", FacplStatusType.STRING),
						"PREMIUM"));
			}
		}
	}

	private class PolicySet_ListenBob extends PolicySet {
		public PolicySet_ListenBob()  {
			addId("ListenBob_Policy");
			// Algorithm Combining
			addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
			// Target
			ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "listen",
					new AttributeName("action", "id"));
			ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Bob",
					new AttributeName("name", "id"));
			ExpressionBooleanTree ebt = new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
			addTarget(ebt);
			// PolElements
			addPolicyElement(new Rule_ListenBob());
			// Obligation
			addObligation(
					new ObligationCheck(Effect.PERMIT,
							new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Bob",
									new AttributeName("name", "id")),
							new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
											new StatusAttribute("streamingBob", FacplStatusType.BOOLEAN),
									true),
							new FacplDate("00:15:00")));
		}

		private class Rule_ListenBob extends Rule {

			Rule_ListenBob() {
				addId("ListenBob_Rule");
				// Effect
				addEffect(Effect.PERMIT);
				ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("loginBob", FacplStatusType.STRING),
						"STANDARD");
				ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("commercialsBob", FacplStatusType.BOOLEAN),
						false);
				ExpressionBooleanTree ebt = new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
				addTarget(ebt);
				addObligation(
						new ObligationStatus(
								new FlagStatus(),
								Effect.PERMIT,
								ObligationType.M,
								new StatusAttribute("commercialsBob", FacplStatusType.BOOLEAN), true)
							);
			}
		}
	}

	private class PolicySet_pubBob extends PolicySet {


		public PolicySet_pubBob()  {
			addId("PubblitàBob_Policy");
			// Algorithm Combining
			addCombiningAlg(it.unifi.facpl.lib.algorithm.DenyUnlessPermitGreedy.class);
			// Target
			ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "listenCommercials",
					new AttributeName("action", "id"));
			ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class, "Bob",
					new AttributeName("name", "id"));
			ExpressionBooleanTree ebt = new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
			addTarget(ebt);
			// PolElements
			addPolicyElement(new Rule_pubBob());
		}

		private class Rule_pubBob extends Rule {

			Rule_pubBob() {
				addId("pubBob_Rule");
				// Effect
				addEffect(Effect.PERMIT);
				ExpressionFunction e1=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("loginBob", FacplStatusType.STRING),
						"STANDARD");
				ExpressionFunction e2=new ExpressionFunction(it.unifi.facpl.lib.function.comparison.Equal.class,
						new StatusAttribute("commercialsBob", FacplStatusType.BOOLEAN),
						true);
				ExpressionBooleanTree ebt = new ExpressionBooleanTree(ExprBooleanConnector.AND, e1, e2);
				addTarget(ebt);
				addObligation(
						new ObligationStatus(
								new FlagStatus(),
								Effect.PERMIT,
								ObligationType.M,
								new StatusAttribute("commercialsBob", FacplStatusType.BOOLEAN), false)
							);
			}
		}
	}
}
