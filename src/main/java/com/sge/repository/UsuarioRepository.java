package com.sge.repository;

import com.sge.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    Usuario findByEmailAndCodigoRecuperacaoSenha(String email, String codigoRecuperacaoSenha);
}
