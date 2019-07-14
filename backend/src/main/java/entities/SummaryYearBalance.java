package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SummaryYearBalance {

    private LocalDate summaryDate;
    private long summaryIncome;
    private long summarySpending;
    private long summaryBalance;

    public SummaryYearBalance(LocalDate summaryDate, long summaryIncome, long summarySpending){
        this.summaryDate = summaryDate;
        this.summaryIncome = summaryIncome;
        this.summarySpending = summarySpending;
        summaryBalance = summaryIncome + summarySpending;
    }
}
