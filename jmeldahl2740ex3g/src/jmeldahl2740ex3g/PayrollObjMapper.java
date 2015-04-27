package jmeldahl2740ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		
		// Open the file.
		try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			
			if (fileOpened) {
				// Open the file.
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		return fileOpened;
	}
	
	public boolean openOutputFile() {
		boolean fileOpened = false;
		try {
			this.outputFile = new PrintWriter(fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeInputFile() {
		if(this.inputFile != null) this.inputFile.close();
	}
	
	public void closeOutputFile() {
		if(this.outputFile != null) this.outputFile.close();
	}
	
	public Payroll getNextPayroll() {
		Payroll p = null;
		
//		String textLine = inputFile.nextLine();
//		int id = Integer.parseInt(textLine);
//		String name = inputFile.nextLine();
//		textLine = inputFile.nextLine();
//		double payRate = Double.parseDouble(textLine);
//		textLine = inputFile.nextLine();
//		double hours = Double.parseDouble(textLine);
//		p = new Payroll(id, name, payRate, hours);
		
		int id = 0;
		String name = "";
		double payRate = 0.0;
		double hours = 0.0;
		
		try {
			String textLine = inputFile.nextLine();
			id = Integer.parseInt(textLine);
			name = inputFile.nextLine();
			textLine = inputFile.next();
			payRate = Double.parseDouble(textLine);
			textLine = inputFile.next();
			hours = Double.parseDouble(textLine);
			p = new Payroll(id, name, payRate, hours);
		}
		catch (NoSuchElementException e) { }
		catch (NumberFormatException e) { }
		
		return p;
	}
	
	public void writePayroll(Payroll payroll) {
		if (this.outputFile != null && payroll != null) {
			outputFile.println(payroll.getId());
			outputFile.println(payroll.getName());
			outputFile.println(payroll.getPayRate());
			outputFile.println(payroll.getHours());
		}
	}
	
	public DefaultListModel getAllPayroll() {
		DefaultListModel payrollCollection = new DefaultListModel();
		
//		payrollCollection.addElement(new Payroll(101, "Jessica Meldahl", 10.0));
//		payrollCollection.addElement(new Payroll(102, "Patti Weigard", 20.0));
//		payrollCollection.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		payrollCollection.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		payrollCollection.addElement(new Payroll(105, "Lisa Laing", 50.0));
//		return payrollCollection;
		
		if (this.openInputFile()) {
			while (this.inputFile.hasNext()) {
				Payroll p = this.getNextPayroll();
				if (p != null)
					payrollCollection.addElement(p);
			}
		this.closeInputFile();
	}
		return payrollCollection;
}
	
	public void writeAllPayroll(DefaultListModel payrollCollection) {
		if (this.openOutputFile()) {
			// Loop all elements of payrollCollection, call this.writePayroll()
			int size = payrollCollection.getSize();
			
			for (int i = 0; i < size ; i++ ) {
			Payroll p = (Payroll) payrollCollection.get(i);
			this.writePayroll(p);
		}
		this.closeOutputFile();
		
	}
	}
}

