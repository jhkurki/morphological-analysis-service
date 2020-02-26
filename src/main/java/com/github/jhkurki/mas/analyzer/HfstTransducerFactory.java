package com.github.jhkurki.mas.analyzer;

import com.google.common.base.Charsets;
import fi.seco.hfst.Transducer;
import fi.seco.hfst.TransducerAlphabet;
import fi.seco.hfst.TransducerHeader;
import fi.seco.hfst.UnweightedTransducer;
import fi.seco.hfst.WeightedTransducer;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

final class HfstTransducerFactory {

  private HfstTransducerFactory() {
  }

  public static Transducer buildFromResource(String name) throws IOException {
    try (InputStream resourceStream = HfstTransducerFactory.class.getResourceAsStream(name)) {
      return build(resourceStream);
    }
  }

  public static Transducer buildFromZippedResource(String name) throws IOException {
    try (InputStream resourceStream = HfstTransducerFactory.class.getResourceAsStream(name)) {
      ZipInputStream zipInputStream = new ZipInputStream(resourceStream, Charsets.UTF_8);

      if (zipInputStream.getNextEntry() == null) {
        throw new RuntimeException("No entries found from zipped transducer file.");
      }

      return build(zipInputStream);
    }
  }

  public static Transducer build(InputStream inputStream) throws IOException {
    DataInputStream dataInputStream = new DataInputStream(inputStream);

    TransducerHeader h = new TransducerHeader(dataInputStream);
    TransducerAlphabet a = new TransducerAlphabet(dataInputStream, h.getSymbolCount());

    return h.isWeighted()
        ? new WeightedTransducer(dataInputStream, h, a)
        : new UnweightedTransducer(dataInputStream, h, a);
  }

}
