package com.git.k8stools.torblerone.k8srolediff;

import java.util.ArrayList;

public class Rule {

    private ArrayList<String> apiGroups;
    private ArrayList<String> resources;
    private ArrayList<String> verbs;
    public int ruleNumber;


    private static int ruleCount = 0;

    public Rule(){
        this.ruleNumber = ++ruleCount;
        System.out.println("Neue Regel mit Nummer " + this.ruleNumber);
        this.apiGroups = new ArrayList<String>();
        this.resources = new ArrayList<String>();
        this.verbs = new ArrayList<String>();

    }

    public void addApiGroup(String a){
        this.apiGroups.add(a);
    }

    public void addResource(String r){
        this.resources.add(r);
    }

    public void addVerb(String v){
        this.verbs.add(v);
    }

    public String toString(){
        return "RuleNo. " + this.ruleNumber + " - apiGroups " + this.apiGroups.size() + " - resources " + this.resources.size() + " - verbs " + this.verbs.size();
    }

    public ArrayList<String> getApiGroups() {
        return this.apiGroups;
    }

    public void setApiGroups(ArrayList<String> apiGroups) {
        this.apiGroups = apiGroups;
    }

    public ArrayList<String> getResources() {
        return this.resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getVerbs() {
        return this.verbs;
    }

    public void setVerbs(ArrayList<String> verbs) {
        this.verbs = verbs;
    }

}
