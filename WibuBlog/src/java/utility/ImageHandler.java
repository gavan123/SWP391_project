import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class ImageHandler {

    // Regex pattern for common image file extensions
    private static final Pattern IMAGE_FILE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg|jpeg|bmp|gif))$)");

    // Base directory path for images
    private static final Path BASE_IMAGE_PATH = FileSystems.getDefault().getPath("web", "images");

    // Function to save a BufferedImage to a file
    public static void saveImage(BufferedImage image, String directory, String fileName, String format) {
        Path imagePath = BASE_IMAGE_PATH.resolve(directory).resolve(fileName);
        saveImage(image, imagePath.toString(), format);
    }

    // Function to load a BufferedImage from a file
    public static BufferedImage loadImage(String directory, String fileName) {
        Path imagePath = BASE_IMAGE_PATH.resolve(directory).resolve(fileName);
        return loadImage(imagePath.toString());
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
    private static void saveImage(BufferedImage image, String filePath, String format) {
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

    // Function to load a BufferedImage from a file
    private static BufferedImage loadImage(String filePath) {
        if (!isImageFile(filePath)) {
            System.err.println("The file " + filePath + " is not a valid image file.");
            return null;
        }

        File inputFile = new File(filePath);
        try {
            BufferedImage image = ImageIO.read(inputFile);
            System.out.println("Image loaded successfully from " + filePath);
            return image;
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage

        // Load images from files
        BufferedImage image1 = loadImage("game", "image1.png");  // Replace with your image paths
        BufferedImage image2 = loadImage("game", "image2.jpg");  // Replace with your image paths

        if (image1 != null && image2 != null) {
            // Save the images to their respective directories
            BufferedImage[] images = {image1, image2};
            String[] directories = {"game", "game"};
            String[] fileNames = {"saved_image1.png", "saved_image2.jpg"};
            String[] formats = {"png", "jpg"};
            saveImages(images, directories, fileNames, formats);
        }
    }
}
