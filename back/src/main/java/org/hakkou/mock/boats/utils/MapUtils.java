package org.hakkou.mock.boats.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MapUtils {

    private Mapper mapper = new DozerBeanMapper();

    public <S, T> List<T> getMappedList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> mapper.map(element,targetClass)).collect(Collectors.toList());         
    }     

    public <S,T> T getMappedObject(S source, Class<T> targetClass) {
        T targetObj = mapper.map(source, targetClass);
        return targetObj;
    }
}
