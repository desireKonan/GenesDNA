package com.grayscale;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class GrayScale {

    private ImageResource imageR;
    private DirectoryResource directoryR;


    public GrayScale() {
        this.directoryR = new DirectoryResource();
    }


    public GrayScale(DirectoryResource directoryR) {
        this.directoryR = directoryR;
    }

    public DirectoryResource getDirectoryR() {
        return directoryR;
    }

    public void setDirectoryR(DirectoryResource directoryR) {
        this.directoryR = directoryR;
    }

    public void convertManyGrayscales() {
        for (File file: this.directoryR.selectedFiles()) {
            ImageResource image = new ImageResource(file);
            image = convertGrayscaleImage(image);
            //Affiche une image.
            saveImage(image, "gray-");
            image.draw();
        }
    }

    public void selectAndConvertInversion() {
        for (File file: this.directoryR.selectedFiles()) {
            ImageResource image = new ImageResource(file);
            image = makeInversion(image);
            //Affiche une image.
            saveImage(image, "inverted-");
            image.draw();
        }
    }


    private ImageResource convertGrayscaleImage(ImageResource resource) {
        for (Pixel pixels : resource.pixels()) {
            //Réccupérer le pixel correspondant.
            int average = (pixels.getBlue() + pixels.getGreen() + pixels.getRed()) / 3;
            pixels.setBlue(average);
            pixels.setRed(average);
            pixels.setGreen(average);
        }
        return resource;
    }

    private ImageResource makeInversion(ImageResource image) {
        for (Pixel pixels : image.pixels()) {
            //Réccupérer le pixel et on le modifie pour inversion (255 - Pixels.couleur).
            pixels.setBlue(255 - pixels.getBlue());
            pixels.setGreen(255 - pixels.getGreen());
            pixels.setRed(255 - pixels.getRed());
        }
        return image;
    }

    private void saveImage(ImageResource resource, String prefix) {
        resource.setFileName((prefix + resource.getFileName()));
        resource.save();
        System.out.println("Sauvegarde effectuée avec succès ! \n Nouveau fichier : " + resource.getFileName());
    }
}
