package org.example.lab3m;

import java.io.File;

public class ImageModel {

    private final ImageCollection collection =
            new ImageCollection();

    private ImageIterator iterator;

    public void loadImages(File[] files) {

        collection.setImages(files);
        iterator = collection.createIterator();
    }

    public File getCurrentImage() {

        if (iterator == null) {
            return null;
        }

        return iterator.current();
    }

    public File nextImage() {

        if (iterator == null) {
            return null;
        }

        return iterator.next();
    }

    public File previousImage() {

        if (iterator == null) {
            return null;
        }

        return iterator.previous();
    }

    public File firstImage() {

        if (iterator == null) {
            return null;
        }

        return iterator.first();
    }

    public File lastImage() {

        if (iterator == null) {
            return null;
        }

        return iterator.last();
    }

    public int getCurrentPosition() {

        if (iterator == null) {
            return 0;
        }

        return iterator.getCurrentIndex() + 1;
    }

    public int getTotalImages() {

        if (iterator == null) {
            return 0;
        }

        return iterator.getSize();
    }
}