import static org.junit.jupiter.api.Assertions.assertTrue;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hakkou.mock.boats.dto.BoatDto;
import org.hakkou.mock.boats.model.Boat;
import org.hakkou.mock.boats.utils.MapUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MapperTest {
    
    
    private static Mapper dozerMapper;
    private static Boat entity;
    private static MapUtils mapUtils;
    
    @BeforeAll
    public static void init() {
        dozerMapper = new DozerBeanMapper();
        mapUtils = new MapUtils();

        entity = new Boat();
        entity.setId(1L);
        entity.setName("first");
        entity.setDescription("first boat");
    }

    @Test
    public void isMapperAloneOK() {
        BoatDto boatDto = dozerMapper.map(entity,BoatDto.class);
        assertTrue(boatDto.getId().equals(1L));
        assertTrue(boatDto.getName().equals("first"));
        assertTrue(boatDto.getDescription().equals("first boat"));
    }

    @Test
    public void isWrappedMapperOk() {
        BoatDto boatDto = mapUtils.getMappedObject(entity, BoatDto.class);
        assertTrue(boatDto.getId().equals(1L));
        assertTrue(boatDto.getName().equals("first"));
        assertTrue(boatDto.getDescription().equals("first boat"));
    }
}
