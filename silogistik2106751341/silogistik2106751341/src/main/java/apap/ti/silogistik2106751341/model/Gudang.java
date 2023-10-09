package apap.ti.silogistik2106751341.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGudang;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_gudang", nullable = false)
    private String nama;

    @NotNull
    @Size
    @Column(name = "alamat_gudang", nullable = false)
    private String alamat_gudang;

    @OneToMany(mappedBy = "gudang",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;



    
}
