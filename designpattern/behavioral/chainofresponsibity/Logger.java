package designpattern.behavioral.chainofresponsibity;

public abstract class Logger {

  public static final int INFO = 1;
  public static final int DEBUG = 2;
  public static final int ERROR = 3;

  protected int level;

  // next element in chain or responsibility
  protected Logger nextLogger;

  public void setNextLogger(Logger nextLogger) {
    this.nextLogger = nextLogger;
  }

  public void logMessage(int level, String message) {
    if (level >= this.level) {
      write(message);
    }
    if (nextLogger != null) {
      nextLogger.logMessage(level, message);
    }
  }

  protected abstract void write(String message);
}
