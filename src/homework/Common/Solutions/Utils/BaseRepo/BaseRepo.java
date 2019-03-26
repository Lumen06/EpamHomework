package homework.Common.Solutions.Utils.BaseRepo;

import java.util.List;

public interface BaseRepo<TYPE, ID> {

    void add(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<TYPE> findAll();

}
