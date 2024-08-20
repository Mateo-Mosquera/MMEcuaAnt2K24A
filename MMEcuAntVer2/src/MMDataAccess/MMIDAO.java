package MMDataAccess;

import java.util.List;

public interface MMIDAO<T> {

    public boolean mmCreate(T entity) throws Exception;

    public List<T> mmReadAll() throws Exception;

    public boolean mmUpdate(T entity) throws Exception;

    public boolean mmDelete(int id) throws Exception;

    public T mmReadBy(Integer id) throws Exception;

}
