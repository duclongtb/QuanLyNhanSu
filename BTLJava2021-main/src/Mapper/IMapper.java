package Mapper;

import java.sql.ResultSet;

/**
 *
 * @author Iroha
 * @param <T>
 */
public interface IMapper<T> {
    T mapRowToObject(ResultSet rs);
}
