package designpattern.behavioral.template;

public class PdfDataMiner extends DataMiner {

  @Override
  protected byte[] extractData() {
    System.out.println("Extracting PDF raw bytes...");
    return "PDF_RAW_BYTES".getBytes();
  }

  @Override
  protected String parseData(byte[] rawData) {
    System.out.println("Parsing PDF format...");
    return "Structured PDF Data";
  }
}

