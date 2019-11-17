package ru.malifaux.programming.FTBWL.event.converters;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Orica mapper for easier simple entities mapping .
 * Works with entities that can mapped <b>FIELD BY FIELD</b>.
 * User custom mappers for map complex object fields.
 */
public class OricaMapper {
    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    /**
     * Field by field map single entity
     *
     * @param fromEntity source entity
     * @param toClass    target entity
     * @param <A>        type of source entity
     * @param <B>        type of target entity
     * @return mapped entity
     */
    public static <A, B> B mapByDefault(A fromEntity, Class<B> toClass) {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(fromEntity, toClass);
    }

    /**
     * Field by field mapping list of entities
     *
     * @param listOfEntities list of entities to be mapped
     * @param toClass        target entity
     * @param <A>            type of source entity
     * @param <B>            type of target entity
     * @return list of mapped entities
     */
    public static <A, B> List<B> mapListByDefault(Collection<A> listOfEntities, Class<B> toClass) {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return listOfEntities.stream().map(e -> mapper.map(e, toClass)).collect(Collectors.toList());
    }

    /**
     * Field by field mapping set of entities
     *
     * @param setOfEntities set of entities to be mapped
     * @param toClass       target entity
     * @param <A>           type of source entity
     * @param <B>           type of target entity
     * @return list of mapped entities
     */
    public static <A, B> Set<B> mapSetByDefault(Set<A> setOfEntities, Class<B> toClass) {
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return setOfEntities.stream().map(e -> mapper.map(e, toClass)).collect(Collectors.toSet());
    }

    /**
     * Get factory for custom mapping
     *
     * @return custom factory
     */
    public static MapperFactory getCustomFactory() {
        return new DefaultMapperFactory.Builder().build();
    }
}
