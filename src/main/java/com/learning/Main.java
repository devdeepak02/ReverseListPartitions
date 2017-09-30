package com.learning;

/**
 * Created by kumade1 on 9/30/17.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);

            String[] tokens = line.split(";");

            if(tokens.length < 2){
                throw new RuntimeException("Invalid input");

            }
            String inputString = tokens[0];
            //how many items to reverse at a time
            String k = tokens[1];
            Integer subListSize = Integer.parseInt(k);

            //Create a parent list from input
            String[] inputTokens = inputString.split(",");
            List<Integer> itemsList =
                    Arrays.stream(inputTokens).
                            map(Integer::parseInt)
                            .collect(Collectors.toList());

            if(itemsList == null || itemsList.isEmpty() || itemsList.size()<subListSize )
            {
                throw new RuntimeException("List contains insufficient elements");
            }

            List<Integer> appendedList = getAppendList(itemsList , subListSize);
            //Now start reversing Lists
            //Create a result List
            List<Integer> resultString = new ArrayList<>();
            int nextIndex = 0;
            int leftOverItems = appendedList != null? appendedList.size() : 0;

            do
            {
                List<Integer> slicedList = itemsList.
                        subList(nextIndex,nextIndex + subListSize);
                resultString.addAll(reverseList(slicedList));

                nextIndex += subListSize;
            }while (nextIndex < (itemsList.size() - leftOverItems));
            //append remaining items
            if(leftOverItems > 0)
            {
                resultString.addAll(appendedList);
            }

            resultString.forEach(x -> System.out.print(""+x+","));
            System.out.println("");


        }
    }

    private static List<Integer> getAppendList(List<Integer> itemsList , int subListSize)
    {
        if(itemsList != null && !itemsList.isEmpty()) {
            int leftItemsToBeAppended = itemsList.size() % subListSize;
            //create a sublist of items to be appended at the end
            if (leftItemsToBeAppended > 0) {
                int lastIndex = itemsList.size()-1;
                int startIndex = lastIndex - leftItemsToBeAppended+1;
                return itemsList.subList(startIndex, lastIndex+1);
            }
        }

        return Collections.<Integer>emptyList();

    }

    private static List<Integer> reverseList(List<Integer> inputList) {
        List<Integer> reversedList = new ArrayList<>(inputList);
        Collections.reverse(reversedList);
        return reversedList;
    }
}
