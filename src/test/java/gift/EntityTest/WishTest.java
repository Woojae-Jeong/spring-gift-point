package gift.EntityTest;

import gift.category.entity.Category;
import gift.member.entity.Member;
import gift.product.entity.Product;
import gift.wish.entity.Wish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class WishTest {

    private Category category;
    private Member member1;
    private Product product1;
    private LocalDateTime createdDate;

    @BeforeEach
    void beforEach() {
        category = new Category("식품", "#812f3D", "식품 url", "");
        product1 = new Product("아메리카노", 4000, "아메리카노url", category);
        member1 = new Member("woo6388@naver.com", "12345678", 5000);
        createdDate = LocalDateTime.now();
    }

    @Test
    void creationTest(){
        Wish wish = new Wish(member1, product1, createdDate);

        assertAll(
                ()->assertThat(wish.getMember()).isEqualTo(member1),
                ()-> assertThat(wish.getProduct()).isEqualTo(product1)
        );
    }
}
