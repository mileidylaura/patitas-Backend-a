package pe.edu.cibertec.patitas_backend.service;

import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend.dto.SignOutRequestDTO;

import java.io.IOException;

public interface AutenticacionService {

    String [] validarUsuario(LoginRequestDTO loginRequestDTO)throws  IOException;

    String CierreSesion(SignOutRequestDTO signOutRequestDTO) throws IOException;

}
