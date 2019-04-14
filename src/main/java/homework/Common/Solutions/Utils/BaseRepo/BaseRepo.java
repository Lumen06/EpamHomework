package homework.Common.Solutions.Utils.BaseRepo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepo<TYPE, ID> {

    void add(TYPE entity);

    void update(TYPE entity);

    Optional<TYPE> findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<TYPE> findAll();

    void insert(Collection<TYPE> items);


}
