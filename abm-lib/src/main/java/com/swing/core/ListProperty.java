package com.swing.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class ListProperty<T> {
    
    private final List<T> list;
    private final String name;
    private final List<Listener> listeners;
    
    public interface Listener {
        
        void onDataChange();
        void onAddItem(int index);
        void onRemoveItem(int index);
        
    }
    
    public ListProperty(String name) {
        this.name = name;
        list = new ArrayList<T>();
        listeners = new ArrayList<Listener>();
    }
    
    public String getName() {
        return name;
    }
    
    public void assignData(List<T> list) {
        if (!this.list.isEmpty())
            this.list.clear();
        
        this.list.addAll(list);
        
        fireDataChange();
    }
    
    public List<T> getList() {
        return list;
    }
    
    public T get(int index) {
        return list.get(index);
    }
    
    public void add(T object) {
        list.add(object);
        
        fireAddItem(list.size() - 1);
    }
    
    public void remove(int index) {
        list.remove(index);
        
        fireRemoveItem(index);
    }
    
    public void remove(T object) {
        int index = list.indexOf(object);
        list.remove(object);
        
        fireRemoveItem(index);
    }
    
    public void clear() {
        list.clear();
        
        fireDataChange();
    }
    
    public synchronized void addListener(Listener listener) {
        listeners.add(listener);
    }
    
    public synchronized void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    
    private void fireDataChange() {
        for (Listener listener : listeners)
            listener.onDataChange();
    }
    
    private void fireAddItem(int index) {
        for (Listener listener: listeners)
            listener.onAddItem(index);
    }
    
    private void fireRemoveItem(int index) {
        for (Listener listener : listeners)
            listener.onRemoveItem(index);
    }
    
}
