package com.git.k8stools.torblerone.k8srolediff;

/**
 * Main Application starting.
 */
public class App {
    

    /**
     * Starts the helper classes to extract the logical roles from the files.
     * @param args [oldRole.yaml] [newRole.yaml]
     */
    public static void main(String[] args) {

        if(args.length == 1){
            if(args[0].contentEquals("help")){
                HelperClass.printHelp();
                System.exit(0);
            }
        }

        if(args.length < 2 || args.length == 0){
            System.out.println("Expected argument length: 2\tReality: " + args.length + ".");
            System.out.println("Consider checking manual by typing \"java -jar k8srolediff help\".");
            System.exit(1);
        }

        if(args.length == 2){
            try{
            RoleReader roleReader = new RoleReader(args[0], args[1]);
            roleReader.readRoles();
            ConsoleDiff cDiff = new ConsoleDiff(roleReader.getRoles());

            cDiff.printDiff();
            } catch(Exception e){
                System.err.println(e);
                e.printStackTrace();
                System.exit(1);
            }

        }

    }
}
