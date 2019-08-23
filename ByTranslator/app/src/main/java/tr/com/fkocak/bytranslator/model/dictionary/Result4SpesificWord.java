package tr.com.fkocak.bytranslator.model.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class Result4SpesificWord {

    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("partOfSpeech")
    @Expose
    private String partOfSpeech;
    @SerializedName("typeOf")
    @Expose
    private List<String> typeOf = null;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms = null;
    @SerializedName("antonyms")
    @Expose
    private List<String> antonyms = null;
    @SerializedName("examples")
    @Expose
    private List<String> examples = null;
    @SerializedName("derivation")
    @Expose
    private List<String> derivation = null;
    @SerializedName("memberOf")
    @Expose
    private List<String> memberOf = null;
    @SerializedName("similarTo")
    @Expose
    private List<String> similarTo = null;
    @SerializedName("instanceOf")
    @Expose
    private List<String> instanceOf = null;
    @SerializedName("partOf")
    @Expose
    private List<String> partOf = null;
    @SerializedName("inCategory")
    @Expose
    private List<String> inCategory = null;
    @SerializedName("hasTypes")
    @Expose
    private List<String> hasTypes = null;
    @SerializedName("hasParts")
    @Expose
    private List<String> hasParts = null;
    @SerializedName("entails")
    @Expose
    private List<String> entails = null;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(List<String> typeOf) {
        this.typeOf = typeOf;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getDerivation() {
        return derivation;
    }

    public void setDerivation(List<String> derivation) {
        this.derivation = derivation;
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
    }

    public List<String> getSimilarTo() {
        return similarTo;
    }

    public void setSimilarTo(List<String> similarTo) {
        this.similarTo = similarTo;
    }

    public List<String> getPartOf() {
        return partOf;
    }

    public void setPartOf(List<String> partOf) {
        this.partOf = partOf;
    }

    public List<String> getInstanceOf() {
        return instanceOf;
    }

    public void setInstanceOf(List<String> instanceOf) {
        this.instanceOf = instanceOf;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getInCategory() {
        return inCategory;
    }

    public void setInCategory(List<String> inCategory) {
        this.inCategory = inCategory;
    }

    public List<String> getHasTypes() {
        return hasTypes;
    }

    public void setHasTypes(List<String> hasTypes) {
        this.hasTypes = hasTypes;
    }

    public List<String> getHasParts() {
        return hasParts;
    }

    public void setHasParts(List<String> hasParts) {
        this.hasParts = hasParts;
    }

    public List<String> getEntails() {
        return entails;
    }

    public void setEntails(List<String> entails) {
        this.entails = entails;
    }

}
