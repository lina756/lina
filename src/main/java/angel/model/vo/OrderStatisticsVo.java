package angel.model.vo;

/**
 * Created by 磷啊 on 2017/12/10.
 */
public class OrderStatisticsVo {
    private Integer count;
    private Double totalPrice;
    private Double checkPrice;
    private Double unCheckPrice;

    public OrderStatisticsVo() {}

    public OrderStatisticsVo(Integer count,Double totalPrice,Double checkPrice,Double unCheckPrice) {
        this.count = count;
        this.totalPrice = totalPrice;
        this.checkPrice = checkPrice;
        this.unCheckPrice = unCheckPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
