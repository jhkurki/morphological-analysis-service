package com.github.jhkurki.mas.analyzer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fi.seco.hfst.Transducer;
import fi.seco.hfst.Transducer.Result;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class HfstTransducerFactoryTest {

  @Test
  void shouldBuildFromZippedClasspathResource() throws IOException {
    Transducer transducer = HfstTransducerFactory.buildFromZippedResource(
        "/transducers/finnish-analysis.hfst.zip");

    Result result = transducer.analyze("omenien").get(0);

    assertTrue(String.join("", result.getSymbols()).startsWith("omena"));
  }

}