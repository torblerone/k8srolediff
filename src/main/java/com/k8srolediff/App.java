package com.k8srolediff;

/**
 * Main Application starting.
 */
public class App {
    

    /**
     * Starts the helper classes to extract the logical roles from the files.
     * @param args [oldRole.yaml] [newRole.yaml]
     */
    public static void main(String[] args) {

        switch(args.length) {
            case 2:
                try {
                    RoleReader roleReader = new RoleReader(args[0], args[1]);
                    roleReader.readRoles();
                    ConsoleDiff cDiff = new ConsoleDiff(roleReader.getRoles());
                    cDiff.printDiff();
                } catch(Exception e) {

                    System.err.println(e);
                    e.printStackTrace();
                    System.exit(1);
                }
                break;
            case 1:
                if(args[0].contentEquals("help")) {
                    HelperClass.printHelp();
                    System.exit(0);
                }
                // break; is not intended here, if argument is not "help" we want to exit with below code block.
            default:
                System.out.println("Expected argument length: 2\tReality: " + args.length + ".");
                System.out.println("Consider checking manual by typing \"java -jar k8srolediff help\".");
                System.exit(1);
        }
    }
}
