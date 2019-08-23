package tr.com.fkocak.bytranslator.model.randomword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4RandomWord {

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("results")
    @Expose
    private List<ResultData> results = null;
    @SerializedName("syllables")
    @Expose
    private SyllablesWord syllables;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<ResultData> getResults() {
        return results;
    }

    public void setResults(List<ResultData> results) {
        this.results = results;
    }

    public SyllablesWord getSyllables() {
        return syllables;
    }

    public void setSyllables(SyllablesWord syllables) {
        this.syllables = syllables;
    }

}
