package entities;

import java.math.BigDecimal;
import java.util.List;

public class Balance {
    private int id;
    private long income;
    private long spending;
    private int version;
    private List<String> tags;

    public Balance(int id, long income, long spending, int version){
        this.id = id;
        this.income = income;
        this.spending = spending;
        this.version = version;
    }

}

