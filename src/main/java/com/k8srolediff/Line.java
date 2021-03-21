package com.k8srolediff;

import java.util.ArrayList;

public class Line {
    
    private String apiGroup;
    private String resource;
    public ArrayList<String> verb1;
    public ArrayList<String> verb2;
    public ArrayList<String> diff;

    public Line(String apiGroup, String resource){
        this.apiGroup = apiGroup;
        this.resource = resource;
        this.verb1 = new ArrayList<String>();
        this.verb2 = new ArrayList<String>();
        this.diff = new ArrayList<String>();
        
    }

    public String toString(){
        return this.apiGroup + "\t" + this.resource + "\t" + this.verb1.size() + "\t" + this.verb2.size();
    }


    public String getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(String apiGroup) {
        this.apiGroup = apiGroup;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public ArrayList<String> getVerb1() {
        return verb1;
    }

    public void setVerb1(ArrayList<String> verb1) {
        this.verb1 = verb1;
    }

    public ArrayList<String> getVerb2() {
        return verb2;
    }

    public void setVerb2(ArrayList<String> verb2) {
        this.verb2 = verb2;
    }

    public ArrayList<String> getDiff() {
        return diff;
    }

    public void setDiff(ArrayList<String> diff) {
        this.diff = diff;
    }

    


}