package org.example.lab3m;

import java.io.File;

public class ImageCollection {

    private File[] images = new File[0];

    public void setImages(File[] images) {

        if (images == null) {
            this.images = new File[0];
        } else {
            this.images = images;
        }
    }

    public File[] getImages() {
        return images;
    }

    public int size() {
        return images.length;
    }

    public ImageIterator createIterator() {
        return new ImageIterator(images);
    }
}