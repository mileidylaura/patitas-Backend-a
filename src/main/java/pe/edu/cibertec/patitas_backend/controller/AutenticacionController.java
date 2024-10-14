package pe.edu.cibertec.patitas_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend.dto.SignOutRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.SignOutResponseDTO;
import pe.edu.cibertec.patitas_backend.service.AutenticacionService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/autenticacion")

public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
      public LoginResponseDTO login(@RequestBody  LoginRequestDTO loginRequestDTO) {


        try {
              //   Thread.sleep(Duration.ofSeconds(60));
            String  [] datosUsuarios = autenticacionService.validarUsuario(loginRequestDTO);
             System.out.println("Resultado:"+ Arrays.toString(datosUsuarios));

             if (datosUsuarios ==null){
                  return new LoginResponseDTO("01", "Error:Usuario no encontrado", "", "");

              }
              return  new LoginResponseDTO("00","",datosUsuarios[0],datosUsuarios[1]);


        } catch ( Exception e) {
            return new LoginResponseDTO("99", "Error:Ocurrio un problema", "", "");

        }

    }
    @PostMapping("/signout")
    public SignOutResponseDTO CierreSesion(@RequestBody SignOutRequestDTO signOutRequestDTO){
        try {
            String codigo = autenticacionService.CierreSesion(signOutRequestDTO);

            if (codigo == null){
                return new SignOutResponseDTO("99","Error : No se pudo guardar el registro");
            }

            return new SignOutResponseDTO(codigo, "Se Guardo el registro de cierre de sesion");
        }
        catch (Exception e ) {
            System.out.println(e.getMessage().toString());
            return new SignOutResponseDTO("99", "Error");
        }
    }
}
