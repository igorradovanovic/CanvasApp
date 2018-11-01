package app.models;

import app.exception.InvalidModelException;

public interface Canvas {

    void addModel(Model model) throws InvalidModelException;

    String render();
}
