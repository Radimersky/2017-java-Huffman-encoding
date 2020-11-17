package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw03.ReadWriteIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class implementing file, stream readers and writers
 *
 * @author Marek Radiměřský
 */
public class ReadWriteIOImpl implements ReadWriteIO {
    /**
     * Reads everything from the input stream and returns it as a single String.
     *
     * @param inputStream input stream
     * @return string containing everything from the input stream
     * @throws IOException when IO problem occurs, f.e. stream is closed
     */
    @Override
    public String streamToString(InputStream inputStream) throws IOException {
      /**  Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
       */
        String streamToString = Stream.of(inputStream.toString())
                .collect(Collectors.joining());
        return streamToString;
    }

    /**
     * Reads everything from the file and returns it as a single String.
     *
     * @param file file
     * @return string containing everything from the file
     * @throws IOException when IO problem occurs, f.e. file does not exist
     */
    @Override
    public String fileToString(File file) throws IOException {
/**

        File f = new File(file.toString());
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
         */
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String data = "";
            while ((line = br.readLine()) != null) {
                data += line;
            }
            return data;
        }

    }

    /**
     * Prints input string to the output stream.
     *
     * @param string       input string
     * @param outputStream stream, to which the string is printed into
     * @throws IOException when IO problem occurs, f.e. stream is closed
     */
    @Override
    public void stringToStream(String string, OutputStream outputStream) throws IOException {
        try (PrintWriter p = new PrintWriter(outputStream)) {
            p.println(string);
        }
    }

    /**
     * Prints input string to the output stream.
     *
     * @param string input string
     * @param file   file, to which the string is printed into
     * @throws IOException when IO problem occurs, f.e. file does not exist
     */
    @Override
    public void stringToFile(String string, File file) throws IOException {
        /**
        try (PrintWriter p = new PrintWriter(new FileOutputStream(file, true))) {
            p.println(string);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
         */

        BufferedWriter bufferedWriter = null;
        try {
            // check if file exist, otherwise create the file before writing
            if (!file.exists()) {
                file.createNewFile();
            }
            Writer writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
            } catch(Exception ex){

            }
        }
    }
}
