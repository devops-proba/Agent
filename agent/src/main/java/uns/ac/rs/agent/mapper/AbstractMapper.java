package uns.ac.rs.agent.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractMapper {
	
	 public static <D, E> List<D> fromEntityList(List<E> entityList, Function<E, D> e) {
	        return entityList.stream().map(e).collect(Collectors.toList());
	    }

     public static <D, E> List<E> toEntityList(List<D> dtoList, Function<D, E> e) {
	        return dtoList.stream().map(e).collect(Collectors.toList());
	    }
}
