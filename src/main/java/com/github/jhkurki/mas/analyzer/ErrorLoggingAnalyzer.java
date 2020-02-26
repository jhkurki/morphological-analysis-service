package com.github.jhkurki.mas.analyzer;

import com.github.jhkurki.mas.domain.Analysis;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Catch, log and return an empty Stream on Runtime Exception.
 */
class ErrorLoggingAnalyzer implements Analyzer {

  private final Logger log = LoggerFactory.getLogger(getClass());
  private final Analyzer delegate;

  public ErrorLoggingAnalyzer(Analyzer delegate) {
    this.delegate = delegate;
  }

  @Override
  public Stream<Analysis> analyze(String word) {
    try {
      return delegate.analyze(word);
    } catch (RuntimeException e) {
      log.error("Failed to analyse: " + word, e);
      return Stream.empty();
    }
  }

}
