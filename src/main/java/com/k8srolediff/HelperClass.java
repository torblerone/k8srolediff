package com.k8srolediff;

/**
 * Used for printing a small manual on using the tool.
 * TODO: finish the HelperClass properly
 */
public class HelperClass {

	public static void printHelp() {
		System.out.println("Manual for k8srolediff");
		System.out.println("\nUsage:\t\tjava -jar k8srolediff.jar [oldRole.yaml] [newRole.yaml]");
	}

}
