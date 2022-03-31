package ru.netology.graphics.image;

public class TextColorSchemaClass implements TextColorSchema{

    private final char [] characters;

    public TextColorSchemaClass (char [] characters) {
        this.characters = characters;
    }

    @Override
    public char convert(int color) {
        return characters[(int) Math.round((double)color / 32)];
    }
}
