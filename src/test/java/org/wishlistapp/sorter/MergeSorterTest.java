package org.wishlistapp.sorter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wishlistapp.repository.GiftRepository;
import org.wishlistapp.service.GiftService;

@SpringBootTest
public class MergeSorterTest {
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private MergeSorter mergeSorter;
    @Autowired
    private GiftService giftService;

    @Test
    public void testMergeSorter() {
        var gifts = giftRepository.findByOwnerList_WishListId(1L);
        var sorted = mergeSorter.sort(gifts);

        assert gifts.size() == 5;
        assert sorted != null;
        assert sorted.size() == 5;
        assert sorted.getFirst().getPriority() == 1;
        assert sorted.getLast().getPriority() == 5;

    }

    @Test
    public void testMergeSortedInService() {
        var sorted = giftService.getSortedGiftsByWishListId(1L);

        assert sorted != null;
        assert sorted.getLast().getPriority() == 5;
        assert sorted.getFirst().getPriority() == 1;
    }
}
