package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.projection.RowProjection;
import uz.pdp.mycinemaapp.repository.HallRepository;
import uz.pdp.mycinemaapp.repository.RowRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RowService {

    private final RowRepository rowRepository;
    private final HallRepository hallRepository;

    public ApiResponse getRowsByHallId(UUID id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isEmpty()) {
            return new ApiResponse("Hall not found!!", false);
        }
        List<RowProjection> rowsByHallId = rowRepository.getRowsByHallId(id);
        if (rowsByHallId.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowsByHallId);
    }

    public ApiResponse getAllRows() {
        List<Row> rowList = rowRepository.findAll();
        if (rowList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowList);
    }

    public ApiResponse getRowById(UUID id) {
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (optionalRow.isEmpty()) {
            return new ApiResponse("Row not found!!", false);
        }
        return new ApiResponse("Success", true, optionalRow.get());
    }

    public ApiResponse addRow(UUID id, Row row) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isEmpty()) {
            return new ApiResponse("Hall not found!!", false);
        }
        try {
            Row saveRow = rowRepository.save(new Row(row.getNumber(), optionalHall.get()));
            return new ApiResponse("Successfully added!", true, saveRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }
    }


    public ApiResponse editRow(UUID id, Row row) {
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (optionalRow.isEmpty()) {
            return new ApiResponse("Row not found!!", false);
        }
        try {
            Row editingRow = optionalRow.get();
            editingRow.setNumber(row.getNumber());
            Row saveRow = rowRepository.save(editingRow);
            return new ApiResponse("Successfully edited!", true, saveRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }

    }

    public ApiResponse deleteRow(UUID id){
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (optionalRow.isEmpty()) {
            return new ApiResponse("Row not found!!", false);
        }
        hallRepository.deleteById(optionalRow.get().getId());
        return new ApiResponse("Successfully deleted!", true);
    }


}
