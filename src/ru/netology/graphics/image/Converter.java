package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double maxRatio;
    private TextColorSchema schema = new TextColorSchemaClass(new char[]{'#', '$', '@', '%', '*', '+', '-', '/'});

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        setMaxHeight(300);
        setMaxWidth(300);
        setMaxRatio(4);

        double horizontalImgRatio = (double) img.getWidth() / img.getHeight();
        double verticalImgRatio = (double) img.getHeight() / img.getWidth();

        if (Math.max(horizontalImgRatio, verticalImgRatio) > maxRatio && maxRatio != 0) {
            throw new BadImageSizeException(maxRatio, Math.max(horizontalImgRatio, verticalImgRatio));
        }

        int newWidth;
        int newHeight;

        if (width != 0) {
            newWidth = Math.min(img.getWidth(), width);
            newHeight = newWidth == width ? (int) (img.getHeight() * horizontalImgRatio) : height;
        } else {
            newWidth = img.getWidth();
            newHeight = img.getHeight();
        }

        if (height != 0) {
            newHeight = Math.min(img.getHeight(), height);
            newWidth = newHeight == height ? (int) (img.getWidth() * horizontalImgRatio) : width;
        } else {
            newHeight = img.getHeight();
            newWidth = img.getWidth();
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();

        StringBuilder graphicImg = new StringBuilder();

        for (int i = 0; i <= newHeight - 1; i++) {
            for (int j = 0; j <= newWidth - 1; j++) {
                int color = bwRaster.getPixel(j, i, new int[3])[0];
                char c = schema.convert(color);
                graphicImg.append(c);
            }
            graphicImg.append("\n");
        }
        return graphicImg.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
