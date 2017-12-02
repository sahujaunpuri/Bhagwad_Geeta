package com.application.aayush.geeta;

/**
 * Created by Aayush on 11/1/2017.
 */

public class Shlokas {
    int id;
    String verse_details;
    String verse_translation;
    String verse_purpose;
    int chapter_id;

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Shlokas() {
    }

    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
    }

    public Shlokas(int id,int chapter_id) {
        this.id = id;
        this.chapter_id = chapter_id;
    }
    public Shlokas(int id, String verse_details, String verse_translation, String verse_purpose, int chapter_id) {
        this.id = id;
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
        this.chapter_id = chapter_id;
    }

    public Shlokas(String verse_details, String verse_translation, String verse_purpose) {
        this.verse_details = verse_details;
        this.verse_translation = verse_translation;
        this.verse_purpose = verse_purpose;
    }


    public int getId() {
        return id;
    }

    public String getVerse_details() {
        return verse_details;
    }

    public String getVerse_translation() {
        return verse_translation;
    }

    public String getVerse_purpose() {
        return verse_purpose;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setVerse_details(String verse_details) {
        this.verse_details = verse_details;
    }


    public void setVerse_translation(String verse_translation) {
        this.verse_translation = verse_translation;
    }

    public void setVerse_purpose(String verse_purpose) {
        this.verse_purpose = verse_purpose;
    }
}
