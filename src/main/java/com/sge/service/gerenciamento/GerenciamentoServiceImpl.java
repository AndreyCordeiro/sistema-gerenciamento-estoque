package com.sge.service.gerenciamento;

import com.sge.entity.Usuario;
import com.sge.repository.UsuarioRepository;
import com.sge.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GerenciamentoServiceImpl implements GerenciamentoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO FAZER VALIDAÇÃO NOS MÉTODOS
    @Override
    public String solicitarCodigo(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        usuario.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(usuario.getId()));
        usuario.setDataEnvioCodigo(new Date());

        usuarioRepository.save(usuario);
        emailService.enviarEmailTexto(usuario.getEmail(), "Código de Recuperação de Senha",
                "Olá, o seu código para recuperação de senha é: " + usuario.getCodigoRecuperacaoSenha());

        return "Código Enviado!";
    }

    @Override
    public String alterarSenha(Usuario usuario) {
        Usuario usuarioBanco = usuarioRepository.findByEmailAndCodigoRecuperacaoSenha(usuario.getEmail(), usuario.getCodigoRecuperacaoSenha());

        if (usuarioBanco != null) {
            Date diferenca = new Date(new Date().getTime() - usuarioBanco.getDataEnvioCodigo().getTime());

            if (diferenca.getTime() / 1000 < 900) {
                usuarioBanco.setSenha(passwordEncoder.encode(usuario.getSenha()));
                usuarioBanco.setCodigoRecuperacaoSenha(null);

                usuarioRepository.save(usuarioBanco);
                return "Senha alterada com sucesso!";
            } else {
                return "Código expirado, solicite um novo código de recuperação de senha!";
            }
        } else {
            return "Email ou código não encontrado!";
        }
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date()) + id;
    }
}
