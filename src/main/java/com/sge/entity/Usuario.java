package com.sge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Usuario extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "documento")
    private String documento;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cep")
    private String cep;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Setter(value = AccessLevel.NONE)
    private List<PermissaoUsuario> permissaoUsuarios;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;
}