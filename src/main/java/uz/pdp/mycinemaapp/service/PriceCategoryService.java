package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.entity.PriceCategory;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.repository.PriceCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PriceCategoryService {

    private final PriceCategoryRepository priceCategoryRepository;

    public ApiResponse getAllPriceCategories() {
        List<PriceCategory> priceCategoryList = priceCategoryRepository.findAll();
        if (priceCategoryList.size() == 0) {
            return new ApiResponse("List empty!", false, priceCategoryList);
        }
        return new ApiResponse("Success", true, priceCategoryList);
    }


    public ApiResponse getPriceCategoryById(UUID id) {
        Optional<PriceCategory> optionalPriceCategory = priceCategoryRepository.findById(id);
        if (optionalPriceCategory.isEmpty()) {
            return new ApiResponse("PriceCategory not found!!", false);
        }
        return new ApiResponse("Success", true, optionalPriceCategory.get());
    }


    public ApiResponse addPriceCategory(PriceCategory priceCategory) {
        try {
            PriceCategory savePriceCategory = priceCategoryRepository.save(priceCategory);
            return new ApiResponse("Successfully added!", true, savePriceCategory);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe priceCategory already exists!", false);
        }
    }

    public ApiResponse editPriceCategory(UUID id, PriceCategory priceCategory) {
        Optional<PriceCategory> optionalPriceCategory = priceCategoryRepository.findById(id);
        if (optionalPriceCategory.isEmpty()) {
            return new ApiResponse("PriceCategory not found!!", false);
        }
        PriceCategory editingPriceCategory = optionalPriceCategory.get();
        editingPriceCategory.setName(priceCategory.getName());
        editingPriceCategory.setColor(priceCategory.getColor());
        editingPriceCategory.setAdditional_fee_in_percent(priceCategory.getAdditional_fee_in_percent());
        PriceCategory savePriceCategory = priceCategoryRepository.save(editingPriceCategory);
        return new ApiResponse("Successfully edited!", true, savePriceCategory);
    }

    public ApiResponse deletePriceCategory(UUID id){
        Optional<PriceCategory> optionalPriceCategory = priceCategoryRepository.findById(id);
        if (optionalPriceCategory.isEmpty()) {
            return new ApiResponse("PriceCategory not found!!", false);
        }
        priceCategoryRepository.deleteById(optionalPriceCategory.get().getId());
        return new ApiResponse("Successfully deleted!", true);
    }

}
