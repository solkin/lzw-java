package com.tomclaw.lzw;

import java.io.*;

import static com.tomclaw.lzw.StreamHelper.copy;

public class Main {

    private static final String HELP = """
            First option must be a mode specifier:
              -c Create  -x Extract
            Create: bzz -c <file> <archive>
              <file>  add these item to archive
              <archive>  archived output file with .bzz extension
            Extract: bzz -x <archive> <file>
              <archive>  archive with .bzz extension path
              <file>  extracted file path
            bzz 1.0""";

    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_ERROR = 1;
    private static final int EXIT_USAGE_ERROR = 2;

    public static void main(String[] args) {
        if (args.length == 0 || (args.length == 1 && args[0].equals("-h"))) {
            System.out.println(HELP);
            System.exit(EXIT_SUCCESS);
        }

        if (args.length != 3) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println(HELP);
            System.exit(EXIT_USAGE_ERROR);
        }

        String command = args[0];
        String source = args[1];
        String destination = args[2];

        // Validate command
        if (!command.equals("-c") && !command.equals("-x")) {
            System.err.println("Error: Invalid command '" + command + "'. Use -c or -x.");
            System.err.println(HELP);
            System.exit(EXIT_USAGE_ERROR);
        }

        // Validate source file exists for both operations
        File sourceFile = new File(source);
        if (!sourceFile.exists()) {
            System.err.println("Error: Source file does not exist: " + source);
            System.exit(EXIT_ERROR);
        }
        if (!sourceFile.isFile()) {
            System.err.println("Error: Source path is not a file: " + source);
            System.exit(EXIT_ERROR);
        }

        // Validate destination directory exists
        File destFile = new File(destination);
        File destDir = destFile.getParentFile();
        if (destDir != null && !destDir.exists()) {
            System.err.println("Error: Destination directory does not exist: " + destDir);
            System.exit(EXIT_ERROR);
        }

        try {
            switch (command) {
                case "-c":
                    compress(source, destination);
                    break;
                case "-x":
                    extract(source, destination);
                    break;
                default:
                    System.err.println("Error: Invalid command '" + command + "'.");
                    System.exit(EXIT_USAGE_ERROR);
            }
            System.exit(EXIT_SUCCESS);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + e.getMessage());
            System.exit(EXIT_ERROR);
        } catch (IOException e) {
            System.err.println("Error: I/O error occurred: " + e.getMessage());
            e.printStackTrace();
            System.exit(EXIT_ERROR);
        } catch (Exception e) {
            System.err.println("Error: Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(EXIT_ERROR);
        }
    }

    private static void compress(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        long originalSize = sourceFile.length();
        long startTime = System.currentTimeMillis();

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath));
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destPath));
             LZWOutputStream lzwOutputStream = new LZWOutputStream(outputStream)) {
            copy(inputStream, lzwOutputStream);
        }

        long endTime = System.currentTimeMillis();
        File destFile = new File(destPath);
        long compressedSize = destFile.length();
        double compressionRatio = originalSize > 0 ? (1.0 - (double) compressedSize / originalSize) * 100 : 0;
        double elapsedTime = (endTime - startTime) / 1000.0;

        System.out.println("Compression completed successfully!");
        System.out.println("  Original size:  " + formatFileSize(originalSize));
        System.out.println("  Compressed size: " + formatFileSize(compressedSize));
        System.out.println("  Compression ratio: " + String.format("%.2f", compressionRatio) + "%");
        System.out.println("  Time elapsed: " + String.format("%.2f", elapsedTime) + "s");
    }

    private static void extract(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        long archiveSize = sourceFile.length();
        long startTime = System.currentTimeMillis();

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath));
             LZWInputStream lzwInputStream = new LZWInputStream(inputStream);
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destPath))) {
            copy(lzwInputStream, outputStream);
        }

        long endTime = System.currentTimeMillis();
        File destFile = new File(destPath);
        long extractedSize = destFile.length();
        double elapsedTime = (endTime - startTime) / 1000.0;

        System.out.println("Extraction completed successfully!");
        System.out.println("  Archive size:   " + formatFileSize(archiveSize));
        System.out.println("  Extracted size: " + formatFileSize(extractedSize));
        System.out.println("  Time elapsed: " + String.format("%.2f", elapsedTime) + "s");
    }

    private static String formatFileSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024.0 * 1024.0));
        }
    }
}
