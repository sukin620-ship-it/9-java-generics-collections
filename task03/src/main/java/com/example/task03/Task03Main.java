package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    private static final String DICTIONARY = "/singular.txt";

    private static final Charset DICTIONARY_CHARSET = Charset.forName("windows-1251");

    public static void main(String[] args) throws IOException {

        try (InputStream dictionary = Task03Main.class.getResourceAsStream(DICTIONARY)) {
            List<Set<String>> anagrams = findAnagrams(dictionary, DICTIONARY_CHARSET);
            for (Set<String> anagram : anagrams) {
                System.out.println(anagram);
            }
        }

    }

    public static List<Set<String>> findAnagrams(
            InputStream inputStream, Charset charset) {

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, charset))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String word = line.toLowerCase(Locale.ROOT);

                if (word.matches("[а-яё]+") && word.length() >= 3) {
                    words.add(word);
                }
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        words = new ArrayList<>(new LinkedHashSet<>(words));

        Map<String, Set<String>> groups = new HashMap<>();

        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String key = new String(letters);

            groups.computeIfAbsent(key, k -> new TreeSet<>()).add(word);
        }

        List<Set<String>> result = new ArrayList<>();
        for (Set<String> group : groups.values()) {
            if (group.size() >= 2) {
                result.add(group);
            }
        }

        result.sort(Comparator.comparing(group -> group.iterator().next()));

        return result;
    }
}
