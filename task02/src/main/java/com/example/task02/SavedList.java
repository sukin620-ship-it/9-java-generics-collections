package com.example.task02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private final List<E> elements;

    public SavedList(File file) {
        this.file = file;
        this.elements = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream input =
                         new ObjectInputStream(new FileInputStream(file))) {

                @SuppressWarnings("unchecked")
                List<E> loaded = (List<E>) input.readObject();

                elements.addAll(loaded);

            } catch (IOException | ClassNotFoundException e) {
                throw new IllegalStateException(
                        "Не удалось загрузить список из файла", e);
            }
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = elements.set(index, element);
        save();
        return oldElement;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E removedElement = elements.remove(index);
        save();
        return removedElement;
    }

    private void save() {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(file))) {

            output.writeObject(elements);

        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Не удалось сохранить список в файл", e);
        }
    }
}
