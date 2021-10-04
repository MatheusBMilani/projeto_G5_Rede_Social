package com.projetoG5RedeSocial.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoG5RedeSocial.model.UserLogin;
import com.projetoG5RedeSocial.model.Usuario;
import com.projetoG5RedeSocial.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		Optional<Usuario> user = repository.findByEmail(usuario.getEmail());
		if (user.isPresent()) {
			return Optional.ofNullable(null);
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return Optional.of(repository.save(usuario));
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());

				return user;
			}
		}

		return null;
	}

}