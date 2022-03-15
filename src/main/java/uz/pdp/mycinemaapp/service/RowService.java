package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.projection.RowProjection;
import uz.pdp.mycinemaapp.repository.RowRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RowService {

    private final RowRepository rowRepository;

    public ApiResponse getRowsByHallId(Long id){
        List<RowProjection> rowsByHallId = rowRepository.ketmon(id);
        if (rowsByHallId.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowsByHallId);
    }

    public ApiResponse getAllRows(){
        List<Row> rowList = rowRepository.findAll();
        if (rowList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowList);
    }
}
