package com.github.jhkurki.mas.analyzer;

import com.github.jhkurki.mas.domain.Analysis;
import java.util.stream.Stream;

public interface Analyzer {

  Analyzer NOP_ANALYZER = word -> Stream.empty();

  Stream<Analysis> analyze(String word);

}
