import java.io.*;
import java.nio.file.*;

public class TestRunnerV1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Input folder containing input files
        String inputFolder = args[0];
        // Output folder containing expected output files
        String outputFolder = args[1];
        // Code file to be compiled and run
        String codeFile = args[2];

        // Compile the code file
        runCommand("javac -d . " + codeFile);

        // Get a list of all input files in the input folder
        File[] inputFiles = new File(inputFolder).listFiles();
        // Get a list of all output files in the output folder
        File[] outputFiles = new File(outputFolder).listFiles();
        
        // Iterate over all input files
        for (int i = 0; i < inputFiles.length; i++) {
            File inputFile = inputFiles[i];
            File outputFile = outputFiles[i];
            
            // Run the code with the input file
            String output = runCommand("java -cp . " + codeFile.replace(".java", "") + " < " + inputFile.getPath());
            
            // Compare the output to the expected output
            String expectedOutput = new String(Files.readAllBytes(outputFile.toPath()));
            if (output.equals(expectedOutput)) {
                System.out.println("Test case passed: " + inputFile.getName());
            } else {
                
                System.out.println("Test case failed: " + inputFile.getName());
                runCommand("java -cp . " + codeFile.replace(".java", "") + " < " + inputFile.getPath() + "> output.txt");
                System.out.println(runCommand("fc output.txt " + outputFile.getPath()));
                //delete the output file
                runCommand("del output.txt");
            }
            System.out.println("---------------------------------");
        }
    }
    //this code runs the command in the terminal and returns the output as a string
    private static String runCommand(String command) throws InterruptedException {
        StringBuilder output = new StringBuilder();
        try {
            //System.out.println(command);
            ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            //Process process = Runtime.getRuntime().exec(command);
            //process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                //line = reader.readLine();
                output.append(line + "\n");
            }
            reader.close();
            //System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}