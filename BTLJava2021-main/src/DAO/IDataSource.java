package DAO;

import java.util.List;
import Mapper.IMapper;

/**
 *
 * @author Iroha
 */
public interface IDataSource<T> {
    <T> List<T> excute(String sql, IMapper<T> mapper, Object... parameters);
    Boolean insert(String sql, Object... parameters);
    Boolean update(String sql, Object... parameters);
}
