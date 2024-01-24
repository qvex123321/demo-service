package com.demo.exam.demoservice.model.vo;

import com.demo.exam.demoservice.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo {
    private ResponseStatusVo error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ForeignExchangeRateVo> currency;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseStatusVo {
        private String code;
        private String message;

        public ResponseStatusVo(ResponseCode responseCode) {
            this.code = responseCode.getCode();
            this.message = responseCode.getMessage();
        }
    }

    public ResponseVo(List<ForeignExchangeRateVo> currency) {
        this.error = new ResponseStatusVo(ResponseCode.SUCCESS);
        this.currency = currency;
    }

    public ResponseVo(ResponseCode responseCode) {
        this.error = new ResponseStatusVo(responseCode);
    }

}
