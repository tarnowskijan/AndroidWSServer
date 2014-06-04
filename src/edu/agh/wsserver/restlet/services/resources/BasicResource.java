package edu.agh.wsserver.restlet.services.resources;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import android.util.Log;

public abstract class BasicResource extends ServerResource {
	@Get
    public abstract Representation basicGetMethod();
	
	protected Representation invoke(Class<?> clazz){
		URI uri = getReference().toUri();
        String methodName = uri.getQuery();
        String className = uri.getPath().substring(1); // TODO na podstawie patha pobierac klase - jak uniknac No class found?
        Log.i("Trying do invoke: " , methodName);
        
        Method[] methods = clazz.getDeclaredMethods();
        List<String> methodNames = new ArrayList<String>();
        
        Method methodToInvoke = null;
        if(methods != null) {
        	for(Method method: Arrays.asList(methods)){
        		if(method.getName().equals(methodName)){
        			methodToInvoke = method;
        		}
        		methodNames.add(method.getName());
        	}
        }
        
        if(methodToInvoke == null){
        	return new StringRepresentation("There is no method: " + methodName 
        			+ ". Try one of the following: " + methodNames);
        }
        
        Object returnStatement = null;
        try {
        	returnStatement = methodToInvoke.invoke(null);
		} catch (Exception e){
			Log.d("basicGetMethod", e.toString());
		}
        
        return new StringRepresentation((String)returnStatement);
	}
}
