package com.github.jhkurki.mas.web;

import com.github.jhkurki.mas.analyzer.Analyzer;
import com.github.jhkurki.mas.domain.Analysis;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnalysisController {

  private final Map<String, Analyzer> analyzers;

  @Autowired
  public AnalysisController(Map<String, Analyzer> analyzers) {
    this.analyzers = analyzers;
  }

  @GetMapping("/analysis")
  public Stream<Analysis> analysis(
      @RequestParam(name = "word") String word,
      @RequestParam(name = "lang", defaultValue = "fi") String lang) {
    return analyzers.getOrDefault(lang, Analyzer.NOP_ANALYZER).analyze(word);
  }

  @GetMapping("/lemma")
  public String lemma(
      @RequestParam(name = "word") String word,
      @RequestParam(name = "tag", defaultValue = "#{T(java.util.Collections).emptySet()}") Set<String> tags,
      @RequestParam(name = "lang", defaultValue = "fi") String lang) {
    return analysis(word, lang)
        .filter(a -> tags.isEmpty() || a.tags.stream().anyMatch(tags::contains))
        .findFirst()
        .map(a -> a.lemma)
        .orElse("");
  }

}
