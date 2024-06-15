package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class ImageHandler {

    // Regex pattern for common image file extensions
    private static final Pattern IMAGE_FILE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg|jpeg|bmp|gif))$)");

    // Base directory path for images
    private static final Path BASE_IMAGE_PATH = FileSystems.getDefault().getPath("web", "images").toAbsolutePath();

    // Function to save a BufferedImage to a file
    public static void saveImage(BufferedImage image, String directory, String fileName, String format) {
        Path imagePath = BASE_IMAGE_PATH.resolve(directory).resolve(fileName);
        saveImage(image, imagePath.toString(), format);
    }

    // Function to save multiple BufferedImages to a directory with individual formats
    public static void saveImages(BufferedImage[] images, String[] directories, String[] fileNames, String[] formats) {
        if (images.length != fileNames.length || images.length != formats.length || images.length != directories.length) {
            System.err.println("The number of images, directories, file names, and formats must match.");
            return;
        }

        for (int i = 0; i < images.length; i++) {
            saveImage(images[i], directories[i], fileNames[i], formats[i]);
        }
    }

    // Function to check if a file is a valid image file based on the regex pattern
    public static boolean isImageFile(String filePath) {
        return IMAGE_FILE_PATTERN.matcher(filePath).matches();
    }

    // Function to save a BufferedImage to a file
    public static void saveImage(BufferedImage image, String filePath, String format) {
        if (!isImageFile(filePath)) {
            System.err.println("The file path " + filePath + " is not a valid image file.");
            return;
        }

        File outputFile = new File(filePath);
        try {
            ImageIO.write(image, format, outputFile);
            System.out.println("Image saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    public static Path removeBuildFromPath(Path path) {
        String pathString = path.toString();
        pathString = pathString.replace("\\build\\", "\\");
        return Paths.get(pathString);
    }

    public static String getExtension(String FileName) {
        String extension = "";
        int i = FileName.lastIndexOf('.');
        if (i > 0) {
            extension = FileName.substring(i + 1);
            return extension;
        }
        return null;
    }

    public static void main(String[] args) {
        // Example BufferedImage (you can replace this with your own image loading logic)
        BufferedImage image = createSampleImage();

        // Directory and file name where the image will be saved
        String directory = "game";
        String fileName = "sample_image.png";

        // Format of the image (e.g., "PNG", "JPEG")
        String format = "png";

        // Save the image using ImageHandler
        ImageHandler.saveImage(image, directory, fileName, format);
    }

    // Helper method to create a sample BufferedImage
    private static BufferedImage createSampleImage() {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        // Fill the image with some content (optional)
        image.createGraphics().drawRect(50, 50, 100, 100);
        return image;
    }
}
