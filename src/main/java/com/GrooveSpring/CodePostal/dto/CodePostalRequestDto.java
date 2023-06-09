package com.GrooveSpring.CodePostal.dto;

import com.GrooveSpring.CodePostal.CodePostal;
import lombok.Data;

@Data
public class CodePostalRequestDto {

    private CodePostal codePostal;


    public void CodePostalDto(CodePostal codePostal) {
        this.codePostal = codePostal;
    }

    public CodePostal getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(CodePostal codePostal) {
        this.codePostal = codePostal;
    }
}
