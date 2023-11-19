package com.orthh.backend.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Rental {
  Long rentalid;
  Long userid;
  Long productid;
  LocalDateTime startdate;
  LocalDateTime duedate;
  LocalDateTime returndate;

  @Builder
  public Rental(
      Long userid,
      Long productid,
      LocalDateTime startdate,
      LocalDateTime duedate,
      LocalDateTime returndate) {
    this.userid = userid;
    this.productid = productid;
    this.duedate = duedate;
    this.startdate = startdate;
    this.returndate = returndate;
  }
}
