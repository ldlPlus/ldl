package cn.itcast.travel.domain;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:27
 * 分页查询
 */
public class PageBean<T> {
    private int pageSize;
    private int currentPage;
    private int totalPage;
    private int totalCount;
    private List<T> data;

    @Override
    public String toString() {
        return "PageBean{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
