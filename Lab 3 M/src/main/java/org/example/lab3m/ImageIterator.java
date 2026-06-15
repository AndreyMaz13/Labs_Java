package org.example.lab3m;

import java.io.File;

public class ImageIterator {

    private final File[] images;
    private int currentIndex = 0;

    public ImageIterator(File[] images) {
        this.images = images;
    }

    public File next() {

        if (images.length == 0) {
            return null;
        }

        currentIndex++;

        if (currentIndex >= images.length) {
            currentIndex = 0;
        }

        return images[currentIndex];
    }

    public File previous() {

        if (images.length == 0) {
            return null;
        }

        currentIndex--;

        if (currentIndex < 0) {
            currentIndex = images.length - 1;
        }

        return images[currentIndex];
    }

    public File first() {

        if (images.length == 0) {
            return null;
        }

        currentIndex = 0;
        return images[currentIndex];
    }

    public File last() {

        if (images.length == 0) {
            return null;
        }

        currentIndex = images.length - 1;
        return images[currentIndex];
    }

    public File current() {

        if (images.length == 0) {
            return null;
        }

        return images[currentIndex];
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getSize() {
        return images.length;
    }
}