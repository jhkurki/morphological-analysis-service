package com.github.jhkurki.mas.domain;

import com.google.common.collect.ImmutableList;
import java.util.Collection;

public class Analysis {

  public final String word;
  public final String lemma;
  public final ImmutableList<String> tags;
  public final Double weight;

  public Analysis(String word, String lemma, Collection<String> tags, Double weight) {
    this.word = word;
    this.lemma = lemma;
    this.tags = ImmutableList.copyOf(tags);
    this.weight = weight;
  }

}
