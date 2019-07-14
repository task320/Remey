package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SummaryMonthBalance {

    private LocalDate summaryDate;
    private long summaryIncome;
    private long summarySpending;
    private long summaryBalance;

    public SummaryMonthBalance(LocalDate summaryDate, long summaryIncome, long summarySpending){
        this.summaryDate = summaryDate;
        this.summaryIncome = summaryIncome;
        this.summarySpending = summarySpending;
        summaryBalance = summaryIncome + summarySpending;
    }
}
