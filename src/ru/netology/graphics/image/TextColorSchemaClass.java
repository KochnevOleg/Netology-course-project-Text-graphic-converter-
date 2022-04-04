package ru.netology.graphics.image;

public class TextColorSchemaClass implements TextColorSchema{

    private final char [] characters;

    public TextColorSchemaClass (char [] characters) {
        this.characters = characters;
    }

    @Override
    public char convert(int color) {
        return characters[(int) Math.round((double)color / 32)];
//        char character = 0;
//
//        if (color >= 0 && color <= 32) {
//            character = '#';
//        } else if (color > 32 && color <= 64){
//            character = '$';
//        } else if (color > 64 && color <= 96) {
//            character = '@';
//        } else if (color > 96 && color <= 128) {
//            character = '%';
//        } else if (color > 128 && color <= 160) {
//            character = '*';
//        } else if (color > 160 && color <= 192) {
//            character = '+';
//        } else if (color > 192 && color <= 224) {
//            character = '-';
//        }else if (color >224 && color <= 255) {
//            character = '/';
//        }
//        return character;
    }
}
