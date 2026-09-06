package designpattern.behavioral.template;

public class App {

// It provides an abstract class that has abstract and non-abstract methods defined together.
// Def:
// It allows you to define a skeleton of an algorithm in a base class and let subclasses override the
// steps without changing the overall algorithm's structure. In the template method pattern, we have a
// predefined structure method called template method which consists of steps. These steps can be an
// abstract method that will be implemented by its subclasses.

// Example: java.io.InputStream, java.io.OutputStream, java.io.Reader and java.io.Writer.
// java.util.AbstractList, java.util.AbstractSet and java.util.AbstractMap.
// javax.servlet.http.HttpServlet

//	Advantages:
//		Reusable Code with the Template Method pattern as it uses inheritance. Only a few methods need to be overridden.
//    Flexibility lets subclasses decide how to implement steps in an algorithm.
// 		Easy to implement and readable.
//		Clean architecture - Parent class defines sequence to execute its methods

//	Disadvantages:
//  	Might Violate SOLID Liskov Substitution Principle.
//		Maintenance issue - Changes at high-level or low-level can disturb implementation,
//    Debugging and understanding the sequence of flow in the Template Method pattern can be confusing at times.

//	Problem:
//	Imagine that you’re creating a data mining application that analyzes corporate documents.
//	Users feed the app documents in various formats (PDF, DOC, CSV), and it tries to extract meaningful data
//	from these docs in a uniform format.
//	The first version of the app could work only with DOC files. In the following version, it was able to support CSV files.
//	A month later, you “taught” it to extract data from PDF files.
//	At some point, you noticed that all three classes have a lot of similar code. While the code for dealing with
//	various data formats was entirely different in all classes.

	public static void main(String[] args) {
		DataMiner pdfMiner = new PdfDataMiner();
		pdfMiner.mine("sample.pdf");

		System.out.println("------------------------------");

		DataMiner docMiner = new DocDataMiner();
		docMiner.mine("report.doc");
	}
}
