package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.NightSessionAddFeeDto;

import java.util.UUID;

public interface NightSessionAddFeeService {

    ApiResponse getAllNightSessionAddFees();

    ApiResponse getNightSessionAddFee(UUID id);

    ApiResponse addNightSessionAddFee(NightSessionAddFeeDto dto);

    ApiResponse editNightSessionAddFee(UUID id, NightSessionAddFeeDto dto);

    ApiResponse deleteNightSessionAddFee(UUID id);

}
