package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class SignatureVerification {

    private final HashMap<String, List<Object>> knownFileTypes = new HashMap();

    private final List<Object> png = new ArrayList();
    private final List<Object> jpg = new ArrayList();
    private final List<Object> bmp = new ArrayList();
    private final List<Object> webp = new ArrayList();
    private final List<Object> mp4 = new ArrayList();
    private final List<Object> txt = new ArrayList();

    private final String[] pngFNE = {".png"};
    private final String[] jpgFNE = {".jpg", ".jpeg", ".jfif"};
    private final String[] bmpFNE = {".bmp"};
    private final String[] webpFNE = {".webp"};
    private final String[] mp4FNE = {".mp4"};
    private final String[] txtFNE = {".txt"};

    private final String pngSignature = "0x89 0x50 0x4E 0x47 0x0D 0x0A 0x1A 0x0A";
    private final String jpgSignature = "0xFF 0xD8 0xFF";
    private final String bmpSignature = "0x42 0x4D";
    private final String webpSignature = "";
    private final String mp4Signature = "0x66 0x74 0x79 0x70";
    private final String txtSignature = "0xEF 0xBB 0xBF";

    public SignatureVerification() {
        png.add(pngFNE);
        png.add(pngSignature);

        jpg.add(jpgFNE);
        jpg.add(jpgSignature);

        bmp.add(bmpFNE);
        bmp.add(bmpSignature);
        
        webp.add(webpFNE);
        webp.add(webpSignature);

        mp4.add(mp4FNE);
        mp4.add(mp4Signature);

        txt.add(txtFNE);
        txt.add(txtSignature);

        knownFileTypes.put("png", png);
        knownFileTypes.put("jpg", jpg);
        knownFileTypes.put("bmp", bmp);
        knownFileTypes.put("webp", webp);
        knownFileTypes.put("mp4", mp4);
        knownFileTypes.put("txt", txt);
    }

    private boolean verifyFileNameExtension(File file, String[] fneList) {
        String fileName = file.getName().toLowerCase();
        for (String fne : fneList) {
            if (fileName.endsWith(fne)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifySignature(File file, String[] fne, byte[] magic) throws IOException {
        if (!verifyFileNameExtension(file, fne)) {
            return false;
        }

        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            int arrayLength = magic.length;
            byte[] byteArray = new byte[arrayLength];
            int count = in.read(byteArray);

            if (count < arrayLength) {
                return false;
            }

            for (int i = 0; i < arrayLength; i++) {
                if (byteArray[i] != magic[i]) {
                    return false;
                }
            }
            return true;

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.err.println("File operation failed.");
            }
        }
    }

    public boolean verifyFile(File file, String fileType) throws IOException {
        List data = knownFileTypes.get(fileType);

        String[] fne = (String[]) data.get(0);
        String magicString = (String) data.get(1);

        String[] hexArray = magicString.split(" ");
        byte[] magic = new byte[hexArray.length];

        for (int i = 0; i < hexArray.length; i++) {
            int value = Integer.parseInt(hexArray[i].substring(2), 16);
            magic[i] = (byte) value;
        }

        return verifySignature(file, fne, magic);
    }
}
