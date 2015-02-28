/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.utilitarios;

import java.util.List;

/**
 *
 * @author lchinchilla
 */
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * Adapter that adapts any {@link java.util.List} to {@link java.util.Vector} class.
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:25 $
 */
public class UnmodifiableVectorAdapter extends Vector {
  
  private List adaptedList;
  
  public UnmodifiableVectorAdapter(List list) {
    setAdaptedList(list);
  }
  
  public void setAdaptedList(List list) {
    this.adaptedList = list;
  }
  
  public boolean contains(Object elem) {
    return adaptedList.contains(elem);
  }
  
  public boolean containsAll(Collection c) {
    return adaptedList.containsAll(c);
  }
  
  public Object elementAt(int index) {
    return adaptedList.get(index);
  }
  
  public Object get(int index) {
    return adaptedList.get(index);
  }
  
  public int indexOf(Object elem) {
    return adaptedList.indexOf(elem);
  }
  
  public int indexOf(Object elem, int index) {
    return subList(index, size()).indexOf(elem);
  }

  public boolean isEmpty() {
    return adaptedList.isEmpty();    
  }
  
  public Object lastElement() {
    return adaptedList.get(size() - 1);
  }
  
  public int lastIndexOf(Object elem) {
    return adaptedList.lastIndexOf(elem);
  }
  
  public int lastIndexOf(Object elem, int index) {
    return subList(0, index).indexOf(elem);
  }
  
  public int size() {
    return adaptedList.size();
  }
  
  public List subList(int fromIndex, int toIndex) {
    return adaptedList.subList(fromIndex, toIndex);
  }

  public Object[] toArray() {
    return adaptedList.toArray();
  }
  
  public Object[] toArray(Object[] a) {
    return adaptedList.toArray(a);
  }

  public String toString() {
    return adaptedList.toString();
  }
  
  public void add(int index, Object element) { 
    throw new UnsupportedOperationException();
  }
  
  
  public boolean add(Object o) {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(Collection c) {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection c) {
    throw new UnsupportedOperationException(); 
  }
  
  public void addElement(Object obj) {
    throw new UnsupportedOperationException();
  }
  
  public void clear() { 
    throw new UnsupportedOperationException();
  }
  
  public Object clone() {
    throw new UnsupportedOperationException();
  }

  public void insertElementAt(Object obj, int index) {
    throw new UnsupportedOperationException();
  }

  public Object remove(int index) {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeAll(Collection c) {
    throw new UnsupportedOperationException();
  }
  
  public void removeAllElements() {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeElement(Object obj) {
    throw new UnsupportedOperationException();
  }

  public void removeElementAt(int index) {
    throw new UnsupportedOperationException();
  }

  public boolean retainAll(Collection c) {
    throw new UnsupportedOperationException();
  }
  
  public Object set(int index, Object element) {
    throw new UnsupportedOperationException();
  }
  
  public void setElementAt(Object obj, int index) {
    throw new UnsupportedOperationException();
  }
  
  
  public void setSize(int newSize) {
    throw new UnsupportedOperationException();
  }
}
