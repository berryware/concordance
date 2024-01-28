package org.exaxis.concordance;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ConcordanceTest {

  @Test
  public void shouldCountWordsAndSentencesCorrectly(){
    String input = "One.\nThree-THREE-three.\nTwo,TWO.(Four,\nfour).(FOUR:FOUR).";
    WordUsage[] expected={
            new WordUsage("four", new int[]{4,4,5,5}),
            new WordUsage("one", new int[]{1}),
            new WordUsage("three", new int[]{2,2,2}),
            new WordUsage("two", new int[]{3,3})
    };
    try {
      Concordance concordance = Concordance.fromString(input);
      validateWordUsages(expected, concordance.getWordUsages());
    } catch (Exception e){
      fail("Error processing input");
    }
  }

  private void validateWordUsages(WordUsage[] expected, Collection<WordUsage> actual){
    int index=0;
    for (WordUsage wordUsage : actual){
      assertEquals(expected[index++], wordUsage);
    }
  }

}
