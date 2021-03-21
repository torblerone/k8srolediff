package com.k8srolediff;

import java.util.ArrayList;

public class Role {


    private static int roleCounter = 0;

    public int roleNumber;
    private String roleName;
    private ArrayList<Rule> rules;

    public Role(String roleName){
        this.roleName = roleName;
        this.rules = new ArrayList<Rule>();
        this.roleNumber = ++roleCounter;
    }
    
    public void addRule(Rule r){
        rules.add(r);
    }

    public String toString(){
        String result = "roleName: " + roleName;
        result += ", rules: ";
        for(Rule r : rules){
            result += r.toString();
        }
        return result;
    }

    public ArrayList<Rule> getRules(){
        return this.rules;
    }

    
}
