package designpattern.creational.factory;

public class CollegeFeeFactory {

	private CollegeFeeFactory() {} 
	
	public static CollegeFee getCollegeFee(String type) {

		switch (type) {
		case "MERIT":
			return new MeritBasedFee();
		case "MANAGEMENT":
			return new ManagementQuotaFee();
		case "SPECIAL":
			return new SpecialCaseFee();
		default:
			return new InstitutionalFee();
		}
	}
}
