 /*
 * $Id: IntrospectorUtils.java,v 1.1.1.1 2005/04/07 18:36:10 pocho Exp $
 */
package com.gmail.lrchfox3.utilitarios;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Class that contains convinience methods for introspection, such as method
 * invoking, searching and caching methods, etc. 
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:10 $
 * TODO test
 */
public class IntrospectorUtils {
  
  /** no arguments array */
  public static final Object[] NO_ARGS = new Class[] {null};

  /** Map for cached methods */
  private IdentityHashMap cachedMethods;
  
  private static IntrospectorUtils instance = new IntrospectorUtils();
  
  protected IntrospectorUtils() {
    cachedMethods = new IdentityHashMap(20);
  }

  public static IntrospectorUtils getInstance() {
    return instance;
  }

  /**
   * Convinience method for invoking method without parameters.
   * 
   * @param method
   * @param o
   * @return
   * @throws Throwable
   */
  public static Object invokeMethod(Method method, Object o) throws Throwable {
    try {
      return method.invoke(o, NO_ARGS);
    }
    catch (InvocationTargetException e) {
      throw e.getCause();
    }
    catch (Exception e) {
      e.printStackTrace(); // TODO log
    }
    return null;
  }
  
  /**
   * Convinience method for invoking methods with a only one parameter.
   * 
   * @param method
   * @param o
   * @param arg
   * @return
   * @throws Throwable
   */
  public static Object invokeMethod(Method method, Object o, Object arg) throws Throwable {
    try {
      return method.invoke(o, new Object[] {arg});
    }
    catch (InvocationTargetException e) {
      throw e.getCause();
    }
    catch (Exception e) {
      e.printStackTrace(); // TODO log
    }
    return null;
  }
  
  /**
   * Returns cached method for given property and given class.
   * 
   * @param clazz
   * @param propertyName
   * @return
   */
  private Method getMethodFromCache(Class clazz, String methodName, 
                                    Class[] args, boolean isExpectedReturnType) {
    if (clazz == null)
      return null;
    
    List cm = (List) cachedMethods.get(clazz);
    Method method;
    if (cm != null) {
      Method[] methods = new Method[cm.size()];
      method = (Method) searchMethod((Method[]) cm.toArray(methods), 
                                     methodName, args, isExpectedReturnType);
      if (method == null)
        method = getMethodFromCache(clazz.getSuperclass(), methodName, 
                                    args, isExpectedReturnType);
    }
    else
      method = getMethodFromCache(clazz.getSuperclass(), methodName, 
                                  args, isExpectedReturnType);
    
    return method;
  }

  /**
   * Searches for a method of given name with given arguments in specified class. 
   * It first look to cached methods and if it is not successful then it continues
   * searching using introspection.
   * 
   * @param clazz
   * @param methodName
   * @param args
   * @param isExpectedReturnType
   * @return
   */
  public static Method findMethodInClass(Class clazz, String methodName, 
                                         Class[] args, boolean isExpectedReturnType) {
    Method method = getInstance().getMethodFromCache(clazz, methodName, 
                                                     args, isExpectedReturnType);
    if (method == null ) {
      method = getInstance().findMethodByIntrospection(clazz, methodName, 
                                                       args, isExpectedReturnType);
      if (method != null)
        getInstance().putMethodToCache(clazz, method);
    }
    return method;
  }

  /**
   * Convinence method for searching a method with multiple possible declarations.
   * This method search all possible declarations in cache in the given order. If match
   * is found, method is return, otherwise it uses introspection to search method.
   * 
   * @param clazz
   * @param methodNames
   * @param args
   * @param isExpectedReturnType
   * @return
   */
  public static Method findMethodInClass(Class clazz, String methodNames[], 
                                         Class[][][] args, boolean isExpectedReturnType) {
    if (methodNames.length != args.length)
      throw new IllegalArgumentException("method names array length must be same as args array length!");
    
    Method method;
    for (int i = 0; i < methodNames.length; i++) {
      for (int j = 0; j < args[i].length; j++) {
        method = getInstance().getMethodFromCache(clazz, methodNames[i], 
                                                  args[i][j], isExpectedReturnType);
        if (method != null)
          return method;
      }
    }
    
    for (int i = 0; i < methodNames.length; i++) {
      for (int j = 0; j < args[i].length; j++) {
        method = getInstance().findMethodByIntrospection(clazz, methodNames[i], 
                                                         args[i][j], isExpectedReturnType);
        if (method != null) {
          getInstance().putMethodToCache(clazz, method);
          return method;
        }
      }
    }

    return null;
  }

  /**
   * Introspects class and looks for methods that supply property "getter" names
   * pattern. Firstly it looks for methods such as getProperty() and isProperty()
   * in itself and in its all superclasses (it caches methods for classes where
   * they are declared). If no such method is found, it looks for methods 
   * "get(String)" or "get(Object)".
   * 
   * @param clazz
   * @param propertyName
   * @return
   */
  private Method findMethodByIntrospection(Class clazz, String methodName, 
                                           Class[] args, boolean isExpectedReturnType) {
    Method methods[] = clazz.getDeclaredMethods();
    Method method = searchMethod(methods, methodName, args, isExpectedReturnType);
    if (method == null && clazz.getSuperclass() != null)
      return findMethodByIntrospection(clazz.getSuperclass(), methodName, 
                                       args, isExpectedReturnType);
    else
      return method;
  }
  
  private void putMethodToCache(Class clazz, Method method) {
    List methods = (List) cachedMethods.get(clazz);
    if (methods == null) {
      methods = new ArrayList(5);
      cachedMethods.put(clazz, methods);
    }
    methods.add(method);
  }

  /**
   * Searchs method in array.
   * @param methods
   * @param name
   * @param args
   * @param isExpectedReturnType
   * 
   * @return
   */
  public static Method searchMethod(Method[] methods, String name, 
                                    Class[] args, boolean isExpectedReturnType) {
    for (int i = 0; i < methods.length; i++) {
      if (name.equals(methods[i].getName()) && 
          (areTypesCastableTo(args, methods[i].getParameterTypes())) && 
          (!isExpectedReturnType || methods[i].getReturnType() != Void.class))
        return methods[i];
    }
    return null;
  }

  /**
   * Checks all classes in array <code>castedArgs</code> whether they are castable
   * to <code>definedArgs</code>.
   * 
   * @param castedArgs
   * @param definedArgs
   * @return
   */
  public static boolean areTypesCastableTo(Class[] castedArgs, Class[] definedArgs) {
    if (castedArgs.length != definedArgs.length)
      return false;
    
    for (int i = 0; i < castedArgs.length; i++) {
      if (!isCastable(castedArgs[i], definedArgs[i]))
        return false;
    }
    return true;
  }
  
  /**
   * Checkes whether class <code>castedClazz</code> is castable to <code>clazz</code>.
   * 
   * @param castedClazz
   * @param clazz
   * @return
   */
  public static boolean isCastable(Class castedClazz, Class clazz) {
    boolean b = castedClazz.isAssignableFrom(clazz) || 
    	(clazz.isPrimitive() && castedClazz == getCorrespondingObjectClassOfPrimitiveType(clazz));
        
    return b;
  }
  
  /**
   * Convenince method that maps primitive Java types to their encapsulating objects.
   * Return class is Object class for primitive type (i.e. for <code>boolean.class</code>,
   * return value is <code>Boolean.class</code>.
   * 
   * @param clazz
   * @return
   */
  public static Class getCorrespondingObjectClassOfPrimitiveType(Class clazz) {
    if (clazz == Boolean.TYPE)
      return Boolean.class;
    else if (clazz == Integer.TYPE)
      return Integer.class;
    else if (clazz == Short.TYPE)
      return Short.class;
    else if (clazz == Double.TYPE)
      return Double.class;
    else if (clazz == Float.TYPE)
      return Float.class;
    else if (clazz == Character.TYPE)
      return Character.class;
    else if (clazz == Byte.TYPE)
      return Byte.class;
    else if (clazz == Long.TYPE)
      return Long.class;
    else
      return null;
  }
  
  /**
   * Utility method to capitalize firts letter of given String.
   * 
   * @param s
   * @return
   */
  public static String capitalize(String s) {
    if (s.length() == 0) {
      return s;
    }
    char chars[] = s.toCharArray();
    chars[0] = Character.toUpperCase(chars[0]);
    return new String(chars);
  }
}

 