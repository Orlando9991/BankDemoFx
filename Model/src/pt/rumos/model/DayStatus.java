package pt.rumos.model;

import java.time.LocalDate;

public class DayStatus {
	private LocalDate date;
	private Double withdrawalsAmount;
	private Integer withdrawalsQuantity;

	public DayStatus(LocalDate date, Double withdrawalsAmount, Integer withdrawalsQuantity) {
		super();
		setDate(date);
		setWithdrawalsAmount(withdrawalsAmount);
		setWithdrawalsQuantity(withdrawalsQuantity);
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getWithdrawalsAmount() {
		return withdrawalsAmount;
	}

	public Integer getWithdrawalsQuantity() {
		return withdrawalsQuantity;
	}

	private void setDate(LocalDate date) {
		this.date = date;
	}
	
	private void setWithdrawalsAmount(Double withdrawalsAmount) {
		this.withdrawalsAmount = withdrawalsAmount;
	}

	private void setWithdrawalsQuantity(Integer withdrawalsQuantity) {
		this.withdrawalsQuantity = withdrawalsQuantity;
	}
	
	
}
