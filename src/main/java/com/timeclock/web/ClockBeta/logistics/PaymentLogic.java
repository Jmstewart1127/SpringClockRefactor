package com.timeclock.web.ClockBeta.logistics;

public class PaymentLogic {
	
	double payment;
	double balanceDue;
	double balancePaid;
	
	public PaymentLogic() {
		super();
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

	public double getBalancePaid() {
		return balancePaid;
	}

	public void setBalancePaid(double balancePayed) {
		this.balancePaid = balancePayed;
	}
	
	public void makePayment(double balanceDue, double amountPaid) {
		double newBalance = balanceDue - amountPaid;
		setBalancePaid(amountPaid);
		setBalanceDue(newBalance);
	}
}
