package org.exaxis.concordance;

import java.io.*;
import java.util.Collection;
import java.util.TreeMap;

public class Concordance {
  private final TreeMap<String, WordUsage>  wordMap;

  private Concordance(TreeMap<String, WordUsage>  wordMap){
    this.wordMap = wordMap;
  }

  public void print(PrintStream printStream) {
    for (WordUsage wordUsage : wordMap.values()){
      printStream.println(wordUsage.toString());
    }
  }

  public Collection<WordUsage> getWordUsages(){
    return wordMap.values();
  }

  public static Concordance fromFile(String filename) throws IOException {
    return fromReader(new FileReader(filename));
  }

  public static Concordance fromString(String s) throws IOException {
    return fromReader(new StringReader(s));
  }

  public static Concordance fromReader(Reader reader) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(reader)) {
      return fromBufferedReader(bufferedReader);
    } catch (Exception e) {
      throw e;
    }
  }

  public static Concordance fromBufferedReader(BufferedReader bufferedReader) throws IOException {
    // Process the text
    // return the Concordance
    return new Concordance(process(bufferedReader));
  }

  private static TreeMap<String, WordUsage> process(BufferedReader bufferedReader) throws IOException {
    assert(bufferedReader != null);
    TreeMap<String, WordUsage> wordUsageMap = new TreeMap<>();
    // process the bufferedReader
    int currentSentence = 1;
    StringBuilder stringBuilder = new StringBuilder();

    // Use a simple single-pass lexer to return the words of the corpus
    int ch = bufferedReader.read();
    while (ch != -1){
      char curr = (char)ch;

      // Are we at the end of the word
      if (Character.isWhitespace(curr)||isEndOfSentence(curr)||isPunctuation(curr)) {
        if (!stringBuilder.isEmpty()){
          // add the word to the TreeMap
          addWord(wordUsageMap, stringBuilder.toString().toLowerCase(), currentSentence);
          stringBuilder = new StringBuilder();
        }
        if (isEndOfSentence(curr)) {
          ++currentSentence;
        }
      } else {
        stringBuilder.append(curr);
      }

      ch = bufferedReader.read();
    }
    return wordUsageMap;
  }

  private static void addWord(TreeMap<String, WordUsage> wordStatsMap, String word, int sentence){
    WordUsage wordUsage = wordStatsMap.get(word);
    if (wordUsage == null) {
      wordStatsMap.put(word, new WordUsage(word, sentence));
    } else {
      wordUsage.addSentence(sentence);
    }
  }

  private static boolean isEndOfSentence(char ch){
    return ch=='.'||ch=='?'||ch=='!';
  }

  private static boolean isPunctuation(char ch){
    return ch == ',' || ch == ':' || ch == ';' || ch == '"' || ch == '\'' || ch == '(' || ch == ')' || ch == '-';
  }

}
