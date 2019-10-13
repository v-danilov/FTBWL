package ru.malifaux.programming.FTBWL.event.converters;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OricaMapper {
    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static <A, B> B mapByDefault(A fromEntity, Class<B> toClass) {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(fromEntity, toClass);
    }

    public static <A, B> List<B> mapListByDefault(List<A> listOfEntities, Class<B> toClass) {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return listOfEntities.stream().map(e -> mapper.map(e, toClass)).collect(Collectors.toList());
    }
}
