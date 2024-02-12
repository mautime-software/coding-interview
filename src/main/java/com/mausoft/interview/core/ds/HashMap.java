package com.mausoft.interview.core.ds;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

public class HashMap<K, V> {
    public static void main(String... args) {
        HashMap<Integer, String> map = new HashMap<>(10);
        map.put(1, "Hello");
        map.put(1, "Hola");
        map.get(1);
        map.get(2);
        map.put(2, "World!!!");
        map.remove(1);
        map.get(1);
        map.get(2);
    }

    private Bucket<K, V>[] buckets;

    public HashMap(int size) {
        buckets = new Bucket[size];
        init();
    }

    private void init() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket<>();
        }
    }

    public void put(K key, V value) {
        System.out.printf("PUT -> [%s, %s]\n", key, value);
        Bucket<K, V> bucket = getBucket(key);
        bucket.upsert(key, value);
    }

    public V get(K key) {
        Bucket<K, V> bucket = getBucket(key);
        V value = bucket.find(key).orElse(null);
        System.out.printf("GET [%s] -> [%s]\n", key, value);
        return value;
    }

    public void remove(K key) {
        System.out.printf("REMOVE [%s]\n", key);
        Bucket<K, V> bucket = getBucket(key);
        bucket.remove(key);
    }

    private Bucket<K, V> getBucket(K key) {
        int hash = key.hashCode() % buckets.length;
        return buckets[hash];
    }

    private static class Bucket<K, V> {
        LinkedList<Item<K, V>> items;

        Bucket() {
            items = new LinkedList<>();
        }

        public void upsert(K key, V value) {
            Optional<Item<K, V>> item = findItem(key);
            if (item.isPresent()) {
                item.get().setValue(value);
                return;
            }
            items.add(new Item<>(key, value));
        }

        public Optional<V> find(K key) {
            Optional<Item<K, V>> item = findItem(key);
            if (!item.isPresent()) {
                return Optional.empty();
            }
            return Optional.ofNullable(item.get().getValue());
        }

        public void remove(K key) {
            Optional<Item<K, V>> item = findItem(key);
            if (!item.isPresent()) {
                return;
            }
            removeItem(item.get().getKey());
        }

        private Optional<Item<K, V>> findItem(K key) {
            return items.stream().filter(e -> e.getKey().equals(key)).findAny();
        }

        private void removeItem(K key) {
            int i = 0;
            for (; i < items.size(); i++) {
                if (items.get(i).getKey() == key) {
                    break;
                }
            }
            items.remove(i);
        }
    }

    private static class Item<K, V> {
        K key;
        V value;

        Item(K aKey, V aValue) {
            key = aKey;
            value = aValue;
        }

        K getKey() {
            return key;
        }

        Item<K, V> setKey(K aKey) {
            key = aKey;
            return this;
        }

        V getValue() {
            return value;
        }

        Item<K, V> setValue(V aValue) {
            value = aValue;
            return this;
        }

        @Override
        public boolean equals(Object target) {
            if (this == target) return true;
            if (!(target instanceof Item<?, ?> item)) return false;
            return Objects.equals(key, item.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}