package parameta.gateway.dto;

import lombok.Getter;

/**
 * Registro Civil (RC),
 * la Tarjeta de Identidad (TI),
 * la Cédula de Ciudadanía (CC),
 * la Tarjeta de Extranjería (TE),
 * la Cédula de Extranjería (CE),
 * el Número de Identificación Tributaria (NIT),
 * el Pasaporte (PP),
 * el Permiso Especial de Permanencia (PEP)
 * y el Documento de Identificación Extranjero (DIE)
 */
public enum DocumentType{
    RC("Registro Civil"),
    TI("Tarjeta de Identidad"),
    CC("Cédula de Ciudadanía"),
    TE("Tarjeta de Extranjería"),
    CE("Cédula de Extranjería"),
    NIT("Número de Identificación Tributaria"),
    PP("Pasaporte"),
    PEP("Permiso Especial de Permanencia"),
    DIE("Documento de Identificación Extranjero");

    @Getter
    private final String description;

    DocumentType(String description) {
        this.description = description;
    }
}





