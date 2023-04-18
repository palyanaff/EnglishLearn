package ru.palyanaff.englishlearn.data;

import androidx.annotation.Nullable;

import java.util.Locale;
import java.util.Objects;

public class Word {

    private String wordText; // english word
    private String wordTranslation; // russian word

    public Word() {
    }

    public Word(String wordText, String wordTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
    }

    public String getWordText() {
        return wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setToLowerCase() {
        wordText = wordText.toLowerCase();
        wordText = wordTranslation.toLowerCase();
    }

    public void setToLowerCase(Locale locale) {
        wordText = wordText.toLowerCase(locale);
        wordTranslation = wordTranslation.toLowerCase(locale);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Word) {
            Word anotherWord = (Word) obj;

            return (this.wordText.equals(anotherWord.wordText)
                    && this.wordTranslation.equals(anotherWord.wordTranslation));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordText, wordTranslation);
    }
}
