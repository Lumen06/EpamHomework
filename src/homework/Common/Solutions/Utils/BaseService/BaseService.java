package homework.Common.Solutions.Utils.BaseService;

import java.util.List;

public interface BaseService<TYPE, ID> {

    void add(TYPE city);

    TYPE findById(ID id);

    void delete(TYPE t);

    void update(TYPE t);

    void deleteById(ID id);

    void printAll();

    List<TYPE> findAll();
}
