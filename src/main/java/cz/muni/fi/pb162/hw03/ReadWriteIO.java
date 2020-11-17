package cz.muni.fi.pb162.hw03;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for reading and writing operations.
 *
 * @author Marek Sabo
 */
public interface ReadWriteIO {

    /**
     *
     * Reads everything from the input stream and returns it as a single String.
     *
     * @param inputStream input stream
     * @return string containing everything from the input stream
     * @throws IOException when IO problem occurs, f.e. stream is closed
     */
    String streamToString(InputStream inputStream) throws IOException;

    /**
     * Reads everything from the file and returns it as a single String.
     *
     * @param file file
     * @return string containing everything from the file
     * @throws IOException when IO problem occurs, f.e. file does not exist
     */
    String fileToString(File file) throws IOException;

    /**
     * Prints input string to the output stream.
     *
     * @param string       input string
     * @param outputStream stream, to which the string is printed into
     * @throws IOException when IO problem occurs, f.e. stream is closed
     */
    void stringToStream(String string, OutputStream outputStream) throws IOException;

    /**
     * Prints input string to the output stream.
     *
     * @param string input string
     * @param file   file, to which the string is printed into
     * @throws IOException when IO problem occurs, f.e. file does not exist
     */
    void stringToFile(String string, File file) throws IOException;

}
