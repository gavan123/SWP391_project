package utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class ImageHandler {

    // Regex pattern for common image file extensions
    private static final Pattern IMAGE_FILE_PATTERN = Pattern.compile("([^\\s]+\\s)*[^\\s]+(\\s[^\\s]+)*\\.(?i)(png|jpg|jpeg|bmp|gif|webp)$");

    // Base directory path for images
    private static final Path BASE_IMAGE_PATH = FileSystems.getDefault().getPath("web", "images").toAbsolutePath();

    // Function to save a BufferedImage to a file
    public static void saveImage(BufferedImage image, String directory, String fileName, String format) {
        Path imagePath = BASE_IMAGE_PATH.resolve(directory).resolve(fileName);
        saveImage(image, imagePath, format);
    }

    // Function to save multiple BufferedImages to a directory with individual formats
    public static void saveImages(BufferedImage[] images, String directory, String[] fileNames, String[] formats) {
        if (images.length != fileNames.length || images.length != formats.length) {
            System.err.println("The number of images, file names, and formats must match.");
            return;
        }

        for (int i = 0; i < images.length; i++) {
            saveImage(images[i], directory, fileNames[i], formats[i]);
        }
    }

    // Function to check if a file is a valid image file based on the regex pattern
    public static boolean isImageFile(String filePath) {
        return IMAGE_FILE_PATTERN.matcher(filePath).matches();
    }

    // Function to save a BufferedImage to a file
//    public static void saveImage(BufferedImage image, String filePath, String format) {
//        if (!isImageFile(filePath)) {
//            System.err.println("The file path " + filePath + " is not a valid image file.");
//            return;
//        }
//
//        File outputFile = new File(filePath);
//        try {
//            ImageIO.write(image, format, outputFile);
//            System.out.println("Image saved successfully to " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error saving image: " + e.getMessage());
//        }
//    }
    
    
    public static void saveImage(BufferedImage image, Path filePath, String format) {
        if (!isImageFile(filePath.toString())) {
            System.err.println("The file path " + filePath + " is not a valid image file.");
            return;
        }

        File outputFile = filePath.toFile();
        try {
            ImageIO.write(image, format, outputFile);
            System.out.println("Image saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    public static Path removeBuildFromPath(Path path) {
        String pathString = path.toString();
        pathString = pathString.replace(File.separator + "build" + File.separator, File.separator);
        return Paths.get(pathString);
    }

    public static String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
            return extension;
        }
        return null;
    }

     public static String encodeMediaName(int userID) {
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
        String formattedDateTime = datetime.format(formatter);
        return userID + "_" + formattedDateTime;
    }

    public static void main(String[] args) {
        // Example BufferedImage (you can replace this with your own image loading logic)
        BufferedImage image1 = createSampleImage(200, 200);
        BufferedImage image2 = createSampleImage(300, 300);

        // Directory where the images will be saved
        String directory = "game"; // Note: Directory with spaces

        // File names and formats for the images
        String[] fileNames = {"sample_image1.png", "sample_image2.png"};
        String[] formats = {"png", "png"};

        // Save the images using ImageHandler
        ImageHandler.saveImages(new BufferedImage[]{image1, image2}, directory, fileNames, formats);
    }

    private static BufferedImage createSampleImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Fill the image with some content (optional)
        Graphics2D g2d = image.createGraphics();
        try {
            // Fill the image with some content (optional)
            g2d.drawRect(width / 4, height / 4, width / 2, height / 2);
        } finally {
            g2d.dispose(); // Dispose of graphics context to free resources
        }

        return image;
    }
}
