package top.common.Page;

import java.util.List;

public class PageQueryBean {



    private static final int DEFAULT_PAGE_SIZE = 10;
    /** 当前页 */
    private Integer currentPage;
    /** 每页显示数据条数 */
    private Integer pageSize;
    /** 所有记录数 */
    private int totalRows;
    /** sql查询起始行 */
    private Integer startRow;
    /** 总页数 */
    private Integer totalPage;
    /** 查询所得数据集 */
    private List<?> items;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        int totalPage = totalRows %getPageSize() == 0 ? totalRows / getPageSize() : totalRows / getPageSize() + 1;
        setTotalPage(totalPage);
    }

    public final Integer getStartRow() {
        if(startRow == null){
            startRow = (currentPage == null ? 0 : (currentPage - 1) * getPageSize());
        }
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

}
