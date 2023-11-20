package com.orthh.backend.dto.rental;

import com.orthh.backend.domain.Product;
import com.orthh.backend.domain.Rental;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentalResDto {
  String rentalId;
  String startdate;
  String duedate;
  String productId;
  String name;
}
