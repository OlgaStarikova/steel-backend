package com.example.webksz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "additional_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfo {

    @Id
    @Column(name = "id_steel", nullable = false)
    private Long idSteel;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_steel", nullable = false)
    private SteelGrade steelGrade;

    @Column(name = "header_text", length = 500)
    private String headerText;

    @Column(name = "footer_text", length = 500)
    private String footerText;
}