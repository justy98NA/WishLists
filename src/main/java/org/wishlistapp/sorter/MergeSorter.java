package org.wishlistapp.sorter;

import org.springframework.stereotype.Component;
import org.wishlistapp.model.Gift;

import java.util.ArrayList;
import java.util.List;

@Component
public class MergeSorter {
    public List<Gift> sort(List<Gift> gifts) {
        return mergeSort(gifts);
    }

    private List<Gift> mergeSort(List<Gift> gifts) {
        if (gifts.size() <= 1) {
            System.out.println("Only one element!");
            System.out.println(gifts.getFirst().getPriority());
            // Nothing more can be done
            // list is sorted
            return gifts;
        }
        int middle = gifts.size() / 2;
        var left = mergeSort(gifts.subList(0, middle));
        var right = mergeSort(gifts.subList(middle, gifts.size()));

        return merge(left, right);
    }

    private List<Gift> merge(List<Gift> left, List<Gift> right) {
        List<Gift> sortedMerged = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            var leftValue = left.get(i);
            var rightValue = right.get(j);

            if (leftValue.getPriority()>rightValue.getPriority()) {
                sortedMerged.add(rightValue);
                j ++;
            } else {
                sortedMerged.add(leftValue);
                i ++;
            }
        }
        sortedMerged.addAll(left.subList(i, left.size()));
        sortedMerged.addAll(right.subList(j, right.size()));

        System.out.println("Merged:");
        for (Gift g: sortedMerged) {
            System.out.println(g.getPriority());
        }
        return sortedMerged;
    }
}
