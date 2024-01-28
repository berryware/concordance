package org.exaxis.concordance;

/**
 * Generate a Concordance from the file passed in
 */
public class App 
{
    public static void main( String[] args )
    {
        // make sure we have enough arguments
        if (args.length==0)
            usage();

        // grab the filename from the last argument
        String filename = args[args.length-1];

        try {
            //load the concordance from the file
            Concordance concordance = Concordance.fromFile(filename);

            // print the concordance
            concordance.print(System.out);
        } catch (Exception e) {
            errorMessageAndExit("Error processing file: "+filename+e.getLocalizedMessage());
        }
    }

    private static void usage(){
        errorMessageAndExit("Usage: java -jar target/concordance-1.0-SNAPSHOT.jar inputFile");
    }

    private static void errorMessageAndExit(String message){
        System.err.println(message);
        System.exit(1);
    }
}
