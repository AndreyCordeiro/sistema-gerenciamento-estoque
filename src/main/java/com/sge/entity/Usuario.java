package com.sge.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Usuario extends Auditavel implements UserDetails {
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

    @Column(name = "codigo_recuperacao_senha")
    private String codigoRecuperacaoSenha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_envio_codigo")
    private Date dataEnvioCodigo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissaoUsuarios;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}