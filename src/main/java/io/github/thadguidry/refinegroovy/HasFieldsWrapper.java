package io.github.thadguidry.refinegroovy;

import java.util.Properties;

import groovy.lang.GroovyObjectSupport;

import com.google.refine.expr.HasFields;

/**
 * Converts OpenRefine Java objects (that have fields)
 * to be supported as Groovy objects during a GroovyShell evaluation.
 * Based on {@link com.google.refine.jython.JythonEvaluable}
 */
public class HasFieldsWrapper extends GroovyObjectSupport {

    public HasFields _obj;

    private Properties _bindings;

    public HasFieldsWrapper(HasFields obj, Properties bindings) {
        _obj = obj;
        _bindings = bindings;
    }

/**
 * Returns the property value.
 * @param name the name of the property of interest
 * @return the value of the property 
*/
@Override
    public Object getProperty(String name) {
        Object v = _obj.getField(name, _bindings);
        if (v != null) {    
            if (v instanceof HasFields) {
                return new HasFieldsWrapper((HasFields) v, _bindings);
            } else {
                return v;
            }
        } else {
            return null;
        }
    }

/** 
 * Invokes the given method on the Java object (or should).
 * @param name the name of the method to call
 * @param args the arguments to use for the method call
*/
@Override
    public Object invokeMethod(String name, Object args) {
        Object v = _obj.getField(name, _bindings);
        if (v != null) {
            if (v instanceof HasFields) {
                return new HasFieldsWrapper((HasFields) v, _bindings);
            } else {
                return v;
            }
        } else {
            return null;
        }
    }
}

 
    
