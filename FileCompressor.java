import java.io.*;
import java.util.HashMap;

public class FileCompressor {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java FileCompressor <compress|decompress> <file>");
            return;
        }

        String command = args[0];
        String fileName = args[1];

        if ("compress".equals(command)) {
            compressFile(fileName);
        } else if ("decompress".equals(command)) {
            decompressFile(fileName);
        } else {
            System.out.println("Unknown command. Use 'compress' or 'decompress'.");
        }
    }

    private static void compressFile(String fileName) throws IOException {
        HashMap<Byte, Integer> frequencyMap = new HashMap<>();

        // Read the file and calculate frequencies
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                frequencyMap.put((byte) byteData, frequencyMap.getOrDefault((byte) byteData, 0) + 1);
            }
        }

        // Build Huffman Tree
        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);
        HuffmanCoding huffmanCoding = new HuffmanCoding(huffmanTree.getRoot());

        // Encode the file
        StringBuilder encodedData = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                String code = huffmanCoding.encode((byte) byteData);
                if (code != null) {
                    encodedData.append(code);
                }
            }
        }

        // Check encoded data length
        if (encodedData.length() == 0) {
            System.out.println("Encoded data is empty, compression failed.");
            return;
        }

        // Write frequencies and encoded data to a new file
        String compressedFileName = fileName + ".huff";
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(compressedFileName))) {
            for (var entry : frequencyMap.entrySet()) {
                String frequencyLine = entry.getKey() + ":" + entry.getValue() + "\n";
                bos.write(frequencyLine.getBytes());
            }
            bos.write("EOF\n".getBytes());
            bos.write(encodedData.toString().getBytes()); // Writing encoded data as a string
        }

        System.out.println("File compressed to: " + compressedFileName);
    }

    private static void decompressFile(String fileName) throws IOException {
        HashMap<Byte, Integer> frequencyMap = new HashMap<>();
        StringBuilder encodedData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Read frequencies
            while ((line = reader.readLine()) != null) {
                if (line.equals("EOF")) break; // End of frequency section
                if (line.trim().isEmpty()) continue; // Skip empty lines

                String[] parts = line.split(":");
                if (parts.length != 2) continue;

                try {
                    byte byteValue = Byte.parseByte(parts[0]);
                    int frequency = Integer.parseInt(parts[1]);
                    frequencyMap.put(byteValue, frequency);
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }

            // Read the encoded data
            while ((line = reader.readLine()) != null) {
                encodedData.append(line);
            }
        }

        // Check if encoded data is empty
        if (encodedData.length() == 0) {
            System.out.println("No encoded data found in the file.");
            return;
        }

        // Build Huffman tree from the frequency map
        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);
        HuffmanCoding huffmanCoding = new HuffmanCoding(huffmanTree.getRoot());

        // Decode the encoded data
        StringBuilder decodedData = new StringBuilder();
        String currentCode = "";
        HashMap<Byte, String> codes = huffmanCoding.getCodes(); // Get the Huffman codes

        for (char bit : encodedData.toString().toCharArray()) {
            currentCode += bit;

            for (var entry : codes.entrySet()) {
                if (entry.getValue().equals(currentCode)) {
                    decodedData.append(entry.getKey());
                    currentCode = "";
                    break;
                }
            }
        }

        // Write the decoded data to a new file with the original name
        String originalFileName = fileName.substring(0, fileName.lastIndexOf('.')); // Remove .huff extension
        try (FileOutputStream fos = new FileOutputStream(originalFileName)) {
            fos.write(decodedData.toString().getBytes()); // Write byte data
        }

        System.out.println("File decompressed to: " + originalFileName);
    }
}
