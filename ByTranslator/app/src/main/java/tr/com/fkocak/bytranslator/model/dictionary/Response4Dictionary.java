package tr.com.fkocak.bytranslator.model.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class Response4Dictionary {

    @SerializedName("word")
    @Expose
    public String word;
    @SerializedName("results")
    @Expose
    public List<Result4SpesificWord> results = null;
    @SerializedName("syllables")
    @Expose
    public Syllables syllables;
    @SerializedName("pronunciation")
    @Expose
    public Pronunciation pronunciation;
    @SerializedName("frequency")
    @Expose
    public Double frequency;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Result4SpesificWord> getResults() {
        return results;
    }

    public void setResults(List<Result4SpesificWord> results) {
        this.results = results;
    }

    public Syllables getSyllables() {
        return syllables;
    }

    public void setSyllables(Syllables syllables) {
        this.syllables = syllables;
    }

    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

}
