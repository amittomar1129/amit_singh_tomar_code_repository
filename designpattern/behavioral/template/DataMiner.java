package designpattern.behavioral.template;

// Template class (defines the skeleton of the algorithm)
public abstract class DataMiner {

  // Template Method (final so subclasses cannot change the algorithm flow)
  public final void mine(String filePath) {
    openFile(filePath);
    byte[] rawData = extractData();
    String data = parseData(rawData);
    save(data);
    closeFile();
  }

  // Steps common to all implementations
  protected void openFile(String path) {
    System.out.println("Opening file: " + path);
  }

  protected void closeFile() {
    System.out.println("Closing file.");
  }

  protected void save(String data) {
    System.out.println("Saving extracted data:\n" + data);
  }

  // Steps that vary across subclasses
  protected abstract byte[] extractData();
  protected abstract String parseData(byte[] rawData);
}
