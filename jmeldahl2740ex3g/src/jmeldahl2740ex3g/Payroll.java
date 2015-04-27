package jmeldahl2740ex3g;

import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours;
	
	public Payroll(int id, String name, double payRate, double hours) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}
	
	public Payroll(int id, String name, double payRate) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
	}

	public int getId() {
		return this.id;
	}

	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean setName(String name) {
		if (name.isEmpty())
			return false;
		else {
			this.name = name;
			return true;
		}
	}

	public double getPayRate() {
		return this.payRate;
	}

	public boolean setPayRate (double payRate) {
		if (payRate >= 7.25 && payRate <= 100.0) {
		this.payRate = payRate;
		return true;
		}
		else {
			return false;
		}
	}

	public double getHours() {
		return this.hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double calcGrossPay() 
	{
		double grossPay,	// Holds the gross pay
		overtimePay;		// Holds pay for overtime
		
		// Determine whether the employee worked more than 40 hours.
		if (hours > 40)
		{
			// Calculate regular pay for the first 40 hours.
			grossPay = 40 * payRate;
			
			// Calculate overtime pay at 1.5 times the regular hourly pay rate.
			overtimePay = (hours - 40) * (payRate * 1.5);
			
			// Add the overtime pay to the regular pay.
			grossPay += overtimePay;
		}
		else 
		{
			// No overtime worked.
			grossPay = payRate * hours;
		}
		
		return grossPay;
	}
	
	public boolean addHours (double hours){
		if (hours >= 0.1 && hours <= 20.0) {
			this.hours += hours;
			return true;
			}
			else {
				return false;
			}
	}
	
	@Override
	public String toString() {
		DecimalFormat dollarFmt = new DecimalFormat("##.00");
		return this.id + " " + this.name + ", Pay Rate: " + dollarFmt.format(this.getPayRate());
	}

}
