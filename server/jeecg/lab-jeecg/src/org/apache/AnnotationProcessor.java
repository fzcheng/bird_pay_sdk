package org.apache;
import java.lang.reflect.InvocationTargetException;
import javax.naming.NamingException;

public interface AnnotationProcessor {
    public void postConstruct(Object instance) throws IllegalAccessException, InvocationTargetException;
    public void preDestroy(Object instance) throws IllegalAccessException, InvocationTargetException;
    public void processAnnotations(Object instance) throws IllegalAccessException, InvocationTargetException, NamingException;
}
