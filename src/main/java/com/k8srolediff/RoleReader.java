package com.k8srolediff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RoleReader {

    private String roleFile1;
    private String roleFile2;

    private Role[] roles;

    public RoleReader(String string, String string2) {
        this.roleFile1 = string;
        this.roleFile2 = string2;
        this.roles = new Role[2];
    }

    public void readRoles() throws FileNotFoundException {
        roles[0] = readRole(roleFile1);
        roles[1] = readRole(roleFile2);
        System.out.println("Role readIn finished.");
    }

    private Role readRole(String roleIn) throws FileNotFoundException {
        File roleFile = new File(roleIn);
        Scanner sc = new Scanner(roleFile);
        System.out.println("FILE: " + roleIn);
        // find roleName
        boolean foundName = false;
        String line = "";
        String lastLine = "";
        Role role = null;
        while (sc.hasNextLine()) {
            if (!(line.contains("apiGroups:"))) {
                line = sc.nextLine();
                if (!foundName) {
                    if (line.contains("name: ")) {
                        role = new Role(line.split(": ")[1]);
                        foundName = true;
                    }
                    continue;
                }
            }
            Rule rule = null;
            if (line.contains("apiGroups") | lastLine.contains("apiGroups")) {
                rule = new Rule();
                line = searchApiGroups(sc, line, rule);
            }
            if (line.contains("resources")) {
                line = searchResources(sc, line, rule);
            }
            if (line.contains("verbs")) {
                line = searchVerbs(sc, line, rule);

                role.addRule(rule);
                //System.out.println(rule.toString());
                //System.out.println();
            }

        }
        sc.close();
        return role;
    }

    private String searchApiGroups(Scanner sc, String line, Rule r) {

        while (sc.hasNextLine() && (line = sc.nextLine()).contains("-")) {
            r.addApiGroup(line.split("- ", 2)[1]);
            // System.out.println(line.split("- ", 2)[1]);
        }
        return line;
    }

    private String searchResources(Scanner sc, String line, Rule r) {

        while (sc.hasNextLine() && (line = sc.nextLine()).contains("-")) {
            //System.out.println("Line: " + line);
            r.addResource(line.split("- ", 2)[1]);
            //System.out.println("ruleNumber: " + r.ruleNumber + ", " + line.split("- ", 2)[1]);
        }
        return line;
    }

    private String searchVerbs(Scanner sc, String line, Rule r) {

        while (sc.hasNextLine() && (line = sc.nextLine()).contains("-") && !(line.contains("apiGroups"))) {
            r.addVerb(line.split("- ", 2)[1]);
            // System.out.println(line.split("- ", 2)[1]);
        }
        return line;
    }


    public void printRules(){
        System.out.println(roles[0].toString());
        System.out.println(roles[1].toString());
    }

    public Role[] getRoles(){
        return this.roles;
    }

}
