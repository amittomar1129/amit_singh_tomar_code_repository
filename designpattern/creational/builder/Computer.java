package designpattern.creational.builder;

final class Computer {

  // required parameters
  private final String HDD;
  private final String RAM;

  // optional parameters
  private final boolean isGraphicsCardEnabled;
  private final boolean isBluetoothEnabled;

  public String getHDD() {
    return HDD;
  }

  public String getRAM() {
    return RAM;
  }

  public boolean isGraphicsCardEnabled() {
    return isGraphicsCardEnabled;
  }

  public boolean isBluetoothEnabled() {
    return isBluetoothEnabled;
  }

  @Override
  public String toString() {
    return "Computer [HDD=" + HDD + ", RAM=" + RAM + ", isGraphicsCardEnabled="
        + isGraphicsCardEnabled
        + ", isBluetoothEnabled=" + isBluetoothEnabled + "]";
  }

  private Computer(Builder builder) {
    this.HDD = builder.getHDD();
    this.RAM = builder.getRAM();
    this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled();
    this.isBluetoothEnabled = builder.isBluetoothEnabled();
  }

  public static Computer.Builder newBuilder() {
    return new Computer.Builder();
  }


  public static final class Builder {
    // required parameters
    private String HDD;
    private String RAM;

    // optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    public Builder() {
    }

    String getHDD() {
      return HDD;
    }

    String getRAM() {
      return RAM;
    }

    boolean isGraphicsCardEnabled() {
      return isGraphicsCardEnabled;
    }

    boolean isBluetoothEnabled() {
      return isBluetoothEnabled;
    }

    public Builder setHDD(String HDD) {
      this.HDD = HDD;
      return this;
    }

    public Builder setRAM(String RAM) {
      this.RAM = RAM;
      return this;
    }

    public Builder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
      this.isGraphicsCardEnabled = isGraphicsCardEnabled;
      return this;
    }

    public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
      this.isBluetoothEnabled = isBluetoothEnabled;
      return this;
    }

    public Computer build() {
      return new Computer(this);
    }
  }
}
