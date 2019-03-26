package homework.Common.Business.Search;

public abstract class BaseSearchCondition<ID> {

    protected ID id;
    protected SortType sortType = SortType.SIMPLE;
    protected SortDirection sortDirection;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public boolean needSorting() {
        return sortDirection != null && sortType != null;
    }
}
