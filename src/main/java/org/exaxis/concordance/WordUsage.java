package org.exaxis.concordance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WordUsage {
  private final String word;
  private final ArrayList<Integer> sentences = new ArrayList<>();

  public WordUsage(String word, int sentence){
    this.word = word;
    addSentence(sentence);
  }

  public WordUsage(String word, int[] locations){
    this.word = word;
    Arrays.sort(locations);
    for (int sentence : locations)
      this.sentences.add(sentence);
  }

  public String getWord() {
    return word;
  }

  public int getOccurrences() {
    return sentences.size();
  }

  public List<Integer> getSentences() {
    return sentences;
  }

  public void addSentence(int sentence){
    sentences.add(sentence);
  }

  private String printLocations(){
    StringBuilder sb = new StringBuilder();
    for(int sentence: sentences){
      sb.append(sentence);
      sb.append(',');
    }
    sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }

  @Override
  public String toString() {
    return String.format("%15s: {%d:%s}", getWord(), getOccurrences(), printLocations());
  }


  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || !(other instanceof WordUsage)) {
      return false;
    }

    WordUsage wordUsage = (WordUsage) other;

    if (Objects.equals(this.word, wordUsage.word) && this.sentences.equals(wordUsage.sentences)) {
      return true;
    }

    return false;
  }

}
