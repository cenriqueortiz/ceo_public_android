package com.cenriqueortiz.utils;

/* ---------------------------------------------------------
 * SimpleOrderedHashtable.java
 * Author: C. Enrique Ortiz
 * Copyright (c) 2004-2012 C. Enrique Ortiz
 * <enrique dot ortiz at gmail dot com>
 *
 * Copyright (c) 2004-2012 C. Enrique Ortiz
 * Usage & redistributions of source code must retain attribution notice.
 * Created on November, 2004.
 * Updated on December 21, 2004.
 * Updated on April 2012 to use Generics
 *
 * SimpleOrderedHashtable.java implements a simple Hashtable
 * that is chronologically ordered bu using a Vector combined
 * with a Hashtable
 *
 * This is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version
 * 2 of the License, or (at your option) any later version.
 *
 * Usage & redistributions of source code must retain the above
 * copyright notice.
 *
 * This software is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should get a copy of the GNU Lesser General Public
 * License from the Free Software Foundation, Inc., 59
 * Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * ------------------------------------------------------
 */

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

/**
* Implements an Ordered Hashtable, with elements in
* chronological order (i.e. insertion order)
*/
public class SimpleOrderedHashtable<K,V> extends Hashtable<K,V> {
    private static final long serialVersionUID = -3519599471601519880L;
    private Vector<K> orderedKeys;

    /**
     * Constructor, creates an <code>SimpleOrderedHashtable</code> (SOH)
     */
    public SimpleOrderedHashtable() {
     super();
        orderedKeys = new Vector<K>();
    }

    /**
     * Constructor, creates an <code>SimpleOrderedHashtable</code>.
     * @param initialCapacity is the initial size for the container.
     */
    public SimpleOrderedHashtable(int initialCapacity) {
     super(initialCapacity);
        orderedKeys = new Vector<K>(initialCapacity);
    }

    /**
     * Maps the specified key to the specified value in this
     * <code>SimpleOrderedHashtable</code>. The value can be
     * retrieved by calling the get method with a key that is
     * equal to the original key.
     * @param key is the hashtable key.
     * @param value is the value.
     * @return the previous value of the specified key in this
     * <code>SimpleOrderedHashtable</code>, or null if it did not have one.
     */
    @Override
    synchronized public V put(K key, V value) {
        int i = orderedKeys.indexOf(key);
        if (i == -1) {
            // Add new name/value pair.
            // insert (append) at the end of the list
            orderedKeys.addElement(key);
        } else {
            // Replace name/value pair.
            orderedKeys.setElementAt(key, i);
        }
        return super.put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     * @param key is a key in the SOH.
     * @return the value to which the key is mapped in
     * this hashtable; null if the key is not mapped to any
     * value in this hashtable.
     */
    @Override
    synchronized public V get(Object key) {
        return super.get(key);
    }

    /**
     * Returns an enumeration of the keys.
     * @return an enumeration of the keys.
     */
    @Override
    synchronized public Enumeration<K> keys() {
        return orderedKeys.elements();
    }

    /**
     * Returns an enumeration of the elements.
     * @return an enumeration of the elements.
     */
    @Override
    synchronized public Enumeration<V> elements() {
        int s = size();
        Vector<V> elements = new Vector<V>(s);
        for (int i=0; i<s; i++) {
            elements.addElement(elementAt(i));
        }
        return elements.elements();
    }

    /**
     * Returns the component at the specified index.
     * @param index is an index into this SOH.
     * @return the <code>Object</code> component at the specified index.
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds.
     */
    synchronized public V elementAt(int index)
            throws ArrayIndexOutOfBoundsException {
        K key = orderedKeys.elementAt(index);
        return get(key);
    }

    /**
     * Returns the key at the specified index.
     * @param index is an index into this SOH.
     * @return the <code>Object</code> key at the specified index.
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds.
     */
    synchronized public K keyAt(int index)
            throws ArrayIndexOutOfBoundsException {
        return orderedKeys.elementAt(index);
    }

    /**
     * Returns the index of the specified <code>Object</code>.
     * @param key is a key in the <code>SimpleOrderedHashtable</code>.
     * @return the index of the specified <code>Object</code>.
     */
    synchronized public int getIndex(Object key) {
        return orderedKeys.indexOf(key);
    }

    /**
     * Removes the key (and its corresponding value) from this Hashtable.
     * This method does nothing if the key is not in the Hashtable.
     * @param key is the key that needs to be removed.
     * @return
     */
    @Override
    synchronized public V remove(Object key) {
        orderedKeys.removeElement(key);
        return super.remove(key);
    }

    /**
     * Removes an element at the specified index.
     * @param i is the index of the element to remove.
     */
    synchronized public void removeElementAt(int i) {
        K key = orderedKeys.elementAt(i);
        orderedKeys.removeElementAt(i);
        remove(key);
    }

    /**
     * Clears this SimpleOrderedHashtable so that it contains no keys.
     */
    @Override
    synchronized public void clear() {
        orderedKeys.removeAllElements();
        super.clear();
    }

    /**
     * Returns the number of components in this SimpleOrderedHashtable.
     * @return the number of components in this vector.
     */
    @Override
    synchronized public int size() {
        return orderedKeys.size();
    }

    /**
     * Recomputes the <code>SimpleOrderedHashtable</code> capacity.
     * @param capacity is the capacity to ensure.
     */
    synchronized public void ensureCapacity(int capacity) {
        orderedKeys.ensureCapacity(capacity);
    }
}

