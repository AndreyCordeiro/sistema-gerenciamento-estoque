package com.sge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "permissao_pessoa")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissaoUsuario extends Auditavel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_permissao")
    private Permissao permissao;

    @Override
    public String getAuthority() {
        return permissao.getNome();
    }
}