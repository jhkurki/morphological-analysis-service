package com.github.jhkurki.mas.analyzer;

import com.github.jhkurki.mas.domain.Analysis;
import fi.seco.hfst.Transducer;
import fi.seco.hfst.Transducer.Result;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyzerConfiguration {

  @Bean
  public Map<String, Analyzer> analyzers() throws IOException {
    Map<String, Analyzer> analyzers = new ConcurrentHashMap<>();
    analyzers.put("fi", fiAnalyzer());
    return analyzers;
  }

  private Analyzer fiAnalyzer() throws IOException {
    Transducer transducer = HfstTransducerFactory
        .buildFromZippedResource("/transducers/finnish-analysis.hfst.zip");

    BiFunction<String, Result, Analysis> resultParser = (word, result) -> {
      String resultString = String.join("", result.getSymbols());
      List<String> lemmaAndTags = Arrays.asList(resultString.split("\\s"));
      return new Analysis(
          word,
          lemmaAndTags.get(0),
          lemmaAndTags.subList(1, lemmaAndTags.size()),
          (double) result.getWeight());
    };

    return new ErrorLoggingAnalyzer(new HfstAnalyzer(transducer, resultParser));
  }

}
