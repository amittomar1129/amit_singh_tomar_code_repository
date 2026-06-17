package designpattern.behavioral.template;

public class DocDataMiner extends DataMiner {

  @Override
  protected byte[] extractData() {
    System.out.println("Extracting DOC binary data...");
    return "DOC_BINARY_DATA".getBytes();
  }

  @Override
  protected String parseData(byte[] rawData) {
    System.out.println("Parsing DOC format...");
    return "Structured DOC Data";
  }
}
