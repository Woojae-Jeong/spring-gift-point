package gift.option.service;

import gift.option.exception.OptionNotFoundException;
import gift.product.exception.ProductNotFoundException;
import gift.option.entity.Option;
import gift.product.entity.Product;
import gift.option.dto.RequestOptionDto;
import gift.option.dto.ResponseOptionDto;
import gift.option.repository.OptionRepository;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;
    private final OptionKeeperService optionKeeperService;

    OptionService(OptionRepository optionRepository, ProductRepository productRepository, OptionKeeperService optionKeeperService){
        this.optionRepository = optionRepository;
        this.productRepository = productRepository;
        this.optionKeeperService= optionKeeperService;
    }

    @Transactional
    public Option addOption(Long productId, RequestOptionDto requestOptionDTO){
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("매칭되는 product가 없습니다"));
        optionKeeperService.checkUniqueOptionName(product, requestOptionDTO.name());
        Option option = new Option(requestOptionDTO.name(), requestOptionDTO.quantity(), product);
        return optionRepository.save(option);
    }

    @Transactional(readOnly = true)
    public  List<ResponseOptionDto> getOptions (Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("매칭되는 product가 없습니다"));
        return optionRepository.findByProduct(product)
                .stream()
                .map(it-> new ResponseOptionDto(
                        it.getId(),
                        it.getName().getValue(),
                        it.getQuantity().getValue()))
                .toList();
    }

    @Transactional
    public void editOption(Long productId, Long optionId, RequestOptionDto requestOptionDTO) {
        Option option = optionRepository.findById(optionId).orElseThrow(()-> new OptionNotFoundException("매칭되는 옵션이 없습니다"));
        if (!(option.isBelongToProduct(productId)))
            throw new OptionNotFoundException("해당 옵션은 해당 상품에 속하지 않는 옵션입니다");
        if(!(option.hasSameName(requestOptionDTO.name()))) //옵션의 name을 변경하려고 한다면 이미 존재하는 name이 있는지 체크
            optionKeeperService.checkUniqueOptionName(option.getProduct(), requestOptionDTO.name());
        option.update(requestOptionDTO.name(), requestOptionDTO.quantity());
    }

    @Transactional
    public void subtractQuantity(Long optionId, int quantity) {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new OptionNotFoundException("매칭되는 옵션이 없습니다"));
        option.subtractQuantity(quantity);
    }

    @Transactional
    public void deleteOption(Long productId, Long optionId) {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new OptionNotFoundException("매칭되는 옵션이 없습니다"));
        if (!(option.isBelongToProduct(productId)))
            throw new OptionNotFoundException("해당 옵션은 해당 상품에 속하지 않는 옵션입니다");
        optionKeeperService.checkHasAtLeastOneOption(option.getProduct());
        optionRepository.deleteById(option.getId());
    }
}