package gift.wish.service;

import gift.wish.dto.RequestWishDTO;
import gift.wish.dto.ResponseWishDTO;
import gift.product.exception.ProductNotFoundException;
import gift.wish.exception.WishNotFoundException;
import gift.member.entity.Member;
import gift.product.entity.Product;
import gift.wish.entity.Wish;
import gift.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;


    public WishService(WishRepository wishRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.productRepository = productRepository;
    }



    @Transactional
    public Wish addWish(Member member, RequestWishDTO requestWishDTO) {
        Product product = productRepository.findById(requestWishDTO.getProductId())
                .orElseThrow(()->new ProductNotFoundException());
        Wish wish = new Wish(member, product, LocalDateTime.now());
        return wishRepository.save(wish);
    }

    @Transactional(readOnly = true)
    public Page<ResponseWishDTO> getWishList(Member member, Pageable pageable) {
        Page<Wish> wishListPage= wishRepository.findByMember(member,pageable);
        List<ResponseWishDTO> response = wishListPage.getContent().stream().map(ResponseWishDTO::of).toList();
        Page<ResponseWishDTO> page = new PageImpl<>(response, pageable, wishListPage.getTotalElements());
        return page;
    }

    @Transactional
    public void deleteWish(Member member, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException());
        Wish wish = wishRepository.findByMemberAndProduct(member, product)
                .orElseThrow(() -> new WishNotFoundException());
        wishRepository.deleteById(wish.getId());
    }

}