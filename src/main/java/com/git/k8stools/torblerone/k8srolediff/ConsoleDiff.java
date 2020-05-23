package com.git.k8stools.torblerone.k8srolediff;

import java.util.ArrayList;

/**
 * Used for formatted printing of the diff-results on the console.
 */
public class ConsoleDiff {

	private Role[] roles;

	private ArrayList<String> apiGroups;
	private ArrayList<String> resources;

	private ArrayList<Rule> rules;

	private ArrayList<Line> lines;

	public ConsoleDiff(Role[] roles) {
		this.roles = roles;
		this.apiGroups = new ArrayList<String>();
		this.resources = new ArrayList<String>();
		this.lines = new ArrayList<Line>();
		this.rules = roles[0].getRules();
		this.rules.addAll(roles[1].getRules());
	}

	/**
	 * Used for calculating apiGroup-resource combinations and matching them with the extracted Rules.
	 * Then 
	 */
	public void getDiffs() {
		fillApiGroups();
		fillResources();

		System.out.printf("|%1$-25s|%2$-30s|%3$-10s|%4$-10s|%5$-50s|", "apiGroup", "resource", "verbs1", "verbs2",
				"diff");
		System.out.println();
		// System.out.println("apiGroup\tresource\tverb1\t\t\tverb2\t\t\tdiff");

		// ist in einer Rule die apiGroup und die Resource vorhanden? Dann füge der Line
		// die verbs hinzu

		ArrayList<Integer> ruleNumbersChecked = new ArrayList<Integer>();

		for(String apiGroup : this.apiGroups){
			for(String resource : this.resources){
				Line l = new Line(apiGroup, resource);
				
				for(Role role : this.roles){
					int roleNumber = role.roleNumber;
					for(Rule rule : role.getRules()){
						
						if(!(ruleNumbersChecked.contains(rule.ruleNumber)) && rule.getApiGroups().contains(apiGroup) && rule.getResources().contains(resource)){
							ruleNumbersChecked.add(rule.ruleNumber);
							for(String verb : rule.getVerbs()){
								
								if(roleNumber == 1){
									if(l.verb1.contains(verb)){
										//System.out.println("Verb1 hat bereits " + verb);
									} else {
										l.verb1.add(verb);
									}
								}
								if(roleNumber == 2){
									if(l.verb2.contains(verb)){
										//System.out.println("Verb2 hat bereits " + verb);
									} else {
										l.verb2.add(verb);
									}
								}
								
							}


						} else {
							//System.out.println(rule.toString() + " enthält nicht apiGroup " + apiGroup + " und resource " + resource);
						}

					}
					ruleNumbersChecked.clear();
				}
				lines.add(l);
			}
		}

		lineDiffs();
		for (Line l : lines) {
			// if (l.getVerb1().size() > 0 || l.getVerb2().size() > 0) {
				System.out.format("|%1$-25s|%2$-30s|%3$-10s|%4$-10s|%5$-50s|", l.getApiGroup(), l.getResource(),
						l.getVerb1().size(), l.getVerb2().size(), l.getDiff());
				System.out.println();
			// }
		}

	}






	/**
	 * heart of the class, if a verb for a specific apiGroup-resource combination is given in oldRole but not in newRole, we lose
	 * some power, therefore we output the verb combined with a "-".
	 * Vice-versa tactic is used for "+".
	 */
	private void lineDiffs() {
		for (Line l : this.lines) {
			for (String verb1 : l.getVerb1()) {
				boolean foundVerb = false;
				for(String verb2 : l.getVerb2()){
					if(verb1.contentEquals(verb2)){
						foundVerb = true;
						break;
					}
				}
				if(!foundVerb){
					l.diff.add("-" +verb1);
				}
			}
			for (String verb2 : l.getVerb2()) {
				boolean foundVerb = false;
				for(String verb1 : l.getVerb1()){
					if(verb2.contentEquals(verb1)){
						foundVerb = true;
						break;
					}
				}
				if(!foundVerb){
					l.diff.add("-" +verb2);
				}
			}
		}
	}





	/** 
	 * Extracts all the 'resources' from the Roles' rules.
	 */
	private void fillResources() {
		for (Rule r : rules) {
			for (String s : r.getResources()) {
				// System.out.println(s);
				if (!this.resources.contains(s)) {
					// System.out.println(s + " added");
					this.resources.add(s);
				}
			}
		}
		// System.out.println("Resources size " + this.resources.size());
	}

	/**
	 * Extracts all the apiGroups from the Roles' rules.
	 */
	private void fillApiGroups() {
		for (Rule r : rules) {
			for (String s : r.getApiGroups()) {
				if (!this.apiGroups.contains(s)) {
					this.apiGroups.add(s);
				}
			}
		}
	}

	/** 
	 * public method for calling the console printing
	 */
	public void printDiff() {
		getDiffs();
	}

}
