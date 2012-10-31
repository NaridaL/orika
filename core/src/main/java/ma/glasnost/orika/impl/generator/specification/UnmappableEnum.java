package ma.glasnost.orika.impl.generator.specification;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingException;
import ma.glasnost.orika.impl.generator.SourceCodeContext;
import ma.glasnost.orika.impl.generator.VariableRef;
import ma.glasnost.orika.metadata.FieldMap;
import ma.glasnost.orika.metadata.Property;

/**
 * UnmappableEnum is a place-holder specification to catch types which
 * cannot be mapped (and which shouldn't fall through to some other specification)
 * 
 * @author mattdeboer
 *
 */
public class UnmappableEnum extends AbstractSpecification {

    public UnmappableEnum(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    public boolean appliesTo(FieldMap fieldMap) {
        return fieldMap.getBType().isEnum() && !fieldMap.getAType().isEnum() && !fieldMap.getAType().isString();
    }

    public String generateMappingCode(VariableRef source, VariableRef destination, Property inverseProperty, SourceCodeContext code) {
        throw new MappingException("Encountered mapping of enum to object (or vise-versa); sourceType="+
                source.type() + ", destinationType=" + destination.type());
    }
    
}