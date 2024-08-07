package gift.option.service;

import gift.option.exception.OptionValidationException;
import gift.option.entity.Option;
import gift.product.entity.Product;
import gift.option.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionKeeperService {
    private final OptionRepository optionRepository;

    public OptionKeeperService(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    public void checkUniqueOptionName(Product product, String name){
        optionRepository.findByProduct(product)
                .stream()
                .filter(it->it.getName().equals(name))
                .findFirst()
                .ifPresent(it -> { // 옵션이 존재할 경우
                    throw new OptionValidationException("이미 해당 상품내에 동일한 이름의 옵션이 존재합니다");
                });
    }

    public void checkHasAtLeastOneOption(Product product) {
        List<Option> optionList = optionRepository.findByProduct(product);
        if (optionList.size() == 1 )
            throw new OptionValidationException("상품 내의 옵션은 적어도 1개 이상이여야 합니다.");
    }
}
