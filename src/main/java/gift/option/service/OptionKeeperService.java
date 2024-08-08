package gift.option.service;

import gift.option.exception.OptionValidationException;
import gift.option.entity.Option;
import gift.option.exception.SingleOptionRemovalException;
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
                    throw new OptionValidationException();
                });
    }

    public void checkHasAtLeastOneOption(Product product) {
        List<Option> optionList = optionRepository.findByProduct(product);
        if (optionList.size() == 1 )
            throw new SingleOptionRemovalException();
    }
}
