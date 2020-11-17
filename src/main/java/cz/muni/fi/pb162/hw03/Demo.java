package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.BinaryCodecImpl;
import cz.muni.fi.pb162.hw03.impl.ReadWriteIOImpl;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.FrequencyTable;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.HuffmanEncoding;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Demo executable class.
 *
 * @author Marek Sabo
 */
public class Demo {


    private static final String ENDING = ".txt";
    private static final String SHORT_FILE = "test";
    private static final String LONG_FILE = "midsummer";


    private static final String USED_FILE = SHORT_FILE;


    private static final String USED_FILE_NAME = USED_FILE + ENDING;
    private static final String CODED_FILE_NAME = USED_FILE + "CODED" + ENDING;
    private static final String OUTPUT_FILE_NAME = USED_FILE + "OUT" + ENDING;


    public static final ReadWriteIO READ_FILE = new ReadWriteIOImpl();

    /**
     * Main method.
     *
     * @param args command line arguments, will not be used
     * @throws IOException if IO problem occurs
     * @throws InterruptedException if diff command is interrupted
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        String messageString = READ_FILE.fileToString(new File(USED_FILE_NAME));
        FrequencyTable frequencyTable = new FrequencyTable(messageString);
        HuffmanEncoding huffman = new HuffmanEncoding(frequencyTable);

        Demo.prettyTablePrint(huffman, frequencyTable.createTable());
        Demo.encodeAndDecode(huffman, messageString);
        Demo.runDiff();
    }

    private static void prettyTablePrint(HuffmanEncoding huffmanEncoding, Set<SymbolFrequency> frequencies) {
        System.out.println("SYMBOL  FREQUENCY  CODE");
        System.out.println("---------------------------");
        frequencies.forEach((node) -> System.out.printf("%6s %9s %-15s" + System.lineSeparator(),
                StringEscapeUtils.escapeJava(String.valueOf(node.getCharacter())),
                node.getFrequency(),
                huffmanEncoding.getEncodingString(node.getCharacter())
        ));
    }

    private static void encodeAndDecode(HuffmanEncoding huffmanEncoding, String message) throws IOException {
        BinaryCodec binaryCodec = new BinaryCodecImpl(huffmanEncoding);

        System.out.println("Creating encoded message...");
        String encodedMessage = binaryCodec.encode(message);
        new ReadWriteIOImpl().stringToFile(encodedMessage, new File(CODED_FILE_NAME));

        System.out.println("Creating decoded message...");
        String decodedMessage = binaryCodec.decode(encodedMessage);
        System.out.println(decodedMessage);
        new ReadWriteIOImpl().stringToFile(decodedMessage, new File(OUTPUT_FILE_NAME));
    }

    private static void runDiff() throws IOException, InterruptedException {
        String[] command = new String[]{diffCommand(), USED_FILE_NAME, OUTPUT_FILE_NAME};
        Process p = Runtime.getRuntime().exec(command);

        System.out.println(READ_FILE.streamToString(p.getErrorStream()));
        System.out.println(READ_FILE.streamToString(p.getInputStream()));

        boolean areSame = p.waitFor() == 0;
        System.out.println("Files are " + (areSame ? "" : "not ") + "same.");
    }

    private static String diffCommand() {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("win");
        if (isWindows) return "fc";
        return "diff";
    }

}
