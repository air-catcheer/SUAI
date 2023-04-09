
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

//java EncodingConverter input.txt output.txt utf8 cp1251

public class EncodingConverter {
    public static void main(String[] args) {

        if (args[0].equals("help")) {
            System.out.println("Enter sequentially separated by a space" +
                    " \"input file name\" \"output file name\"" +
                    " \"input file encoding\" \"output file encoding\"");
            return;
        }

        if (!new File(args[0]).exists()) {
            System.out.println("Error: Input file not found");
            return;
        }

        if (!new File(args[1]).exists()) {
            System.out.println("Error: Output file not found");
            return;
        }

        if (!new File(args[2]).exists()) {
            System.out.println("Error: Enter input encoding ");
            return;
        }

        if (!new File(args[3]).exists()) {
            System.out.println("Error: Enter output encoding");
            return;
        }

        // создание списка всех кодировок
        String[] encodings = Charset.availableCharsets().keySet().toArray(new String[0]);
        ArrayList<String> charsets = new ArrayList<String>(Arrays.asList(encodings));
        for (String encoding : encodings) {
            charsets.addAll(Charset.availableCharsets().get(encoding).aliases());
        }

        for (int i = 2; i < 4; i++) {
            final int j = i;
            try {
                args[j] = charsets.stream().filter(charset -> charset.equalsIgnoreCase(args[j])).findFirst().get();
            } catch (Exception e) {
                System.out.println("Invalid encoding");
                return;
            }
        }

        try {
            convert(new File(args[0]), new File(args[1]), args[2], args[3]);
        } catch (IOException e) {
            System.out.println("Error while converting file");
        }
    }

    // конвертирование строк
    public static void convert(File source, File target, String fromEncoding, String toEncoding) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), fromEncoding));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), toEncoding));) {
            char[] buffer = new char[1024];
            int read;
            while ((read = br.read(buffer)) != -1) {
                bw.write(buffer, 0, read);
            }
        }
    }
}

