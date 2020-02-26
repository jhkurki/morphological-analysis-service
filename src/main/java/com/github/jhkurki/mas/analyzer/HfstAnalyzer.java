package com.github.jhkurki.mas.analyzer;

import com.github.jhkurki.mas.domain.Analysis;
import fi.seco.hfst.Transducer;
import fi.seco.hfst.Transducer.Result;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class HfstAnalyzer implements Analyzer {

  private final Transducer transducer;
  private final BiFunction<String, Result, Analysis> resultParser;

  public HfstAnalyzer(Transducer transducer, BiFunction<String, Result, Analysis> resultParser) {
    this.transducer = transducer;
    this.resultParser = resultParser;
  }

  public Stream<Analysis> analyze(String word) {
    return transducer.analyze(word).stream().map(result -> resultParser.apply(word, result));
  }

}
