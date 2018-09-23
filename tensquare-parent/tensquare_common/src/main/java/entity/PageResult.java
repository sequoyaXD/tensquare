package entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:12
 * @Description:
 */

public class PageResult<T> implements Serializable {
    private Long total ;
    private List<T> rows ;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {

        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
