package gift.order.service;

import gift.member.entity.Member;
import gift.option.entity.Option;
import gift.option.service.OptionService;
import gift.order.dto.RequestOrderDto;
import gift.order.dto.ResponseOrderDto;
import gift.kakaoOauth.Event.EventObject.SendMessageToMeEvent;
import gift.option.exception.OptionNotFoundException;
import gift.order.entity.Order;
import gift.order.exception.OrderNotFoundException;
import gift.vo.AccessToken;
import gift.vo.CashReceipt;
import gift.option.repository.OptionRepository;
import gift.order.repository.OrderRepository;
import gift.product.entity.Product;
import gift.wish.entity.Wish;
import gift.wish.service.WishRepository;
import gift.util.KakaoUtil;
import gift.member.service.MemberService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OptionService optionService;
    private final WishRepository wishRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;
    private final KakaoUtil kakaoUtil;
    private final ApplicationEventPublisher eventPublisher;
    private final MemberService memberService;

    public OrderService(OptionService optionService, WishRepository wishRepository, OptionRepository optionRepository, OrderRepository orderRepository,
                        KakaoUtil kakaoUtil, ApplicationEventPublisher eventPublisher, MemberService memberService) {
        this.optionService = optionService;
        this.wishRepository = wishRepository;
        this.optionRepository = optionRepository;
        this.orderRepository = orderRepository;
        this.kakaoUtil = kakaoUtil;
        this.eventPublisher = eventPublisher;
        this.memberService = memberService;
    }

    @Transactional
    public ResponseOrderDto createOrder(Member member, RequestOrderDto requestOrderDTO) {
        Option option = optionRepository.findById(requestOrderDTO.optionId()).orElseThrow(()->
                new OptionNotFoundException());

        optionService.subtractQuantity(option.getId(), requestOrderDTO.quantity());

        List<Wish> wishList = wishRepository.findWishListByMember(member);
        wishList.stream()
                .filter(it->it.getProduct().equals(option.getProduct()))
                .findFirst()
                .ifPresent(wish->wishRepository.deleteById(wish.getId()));

        int totalPrice = getToalPrice(requestOrderDTO.quantity(), option.getProduct());
        memberService.subtractPoint(member, totalPrice);

        CashReceipt cashReceipt = null;
        if (requestOrderDTO.phoneNumber().isPresent())
            cashReceipt = new CashReceipt(requestOrderDTO.phoneNumber().get());

        Order order = orderRepository.save(
                new Order(option, member, requestOrderDTO.quantity(), LocalDateTime.now(), requestOrderDTO.message(), cashReceipt)
        );

        Optional<AccessToken> accessToken = member.getAccessToken();
        if(accessToken.isPresent()){
            eventPublisher.publishEvent(new SendMessageToMeEvent(accessToken.get(), requestOrderDTO.message()));
        }

        return ResponseOrderDto.of(order);
    }

    private int getToalPrice(int quantity, Product product) {
        int totalPrice = product.getPrice().getValue() * quantity;
        if (totalPrice >= 50000) //주문 금액이 5만원 이상인 경우 10프로 할인
            totalPrice *= 0.9;

        return totalPrice;
    }

    @Transactional(readOnly = true)
    public List<ResponseOrderDto> getOrders(Member member){
       return orderRepository.findAllByMember(member)
               .stream()
               .map(ResponseOrderDto::of)
               .toList();
    }

    @Transactional
    public void deleteOrder(Member member, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException());
        order.checkOrderBelongsToMember(member);
        order.getOption().addQuantity(order.getQuantity().getValue());
        int totalPrice = getToalPrice(order.getQuantity().getValue(), order.getOption().getProduct());
        memberService.refundPoints(member, totalPrice);
        orderRepository.deleteById(orderId);
    }
}
