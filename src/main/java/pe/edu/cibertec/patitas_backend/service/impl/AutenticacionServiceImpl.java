package pe.edu.cibertec.patitas_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend.dto.SignOutRequestDTO;
import pe.edu.cibertec.patitas_backend.service.AutenticacionService;

import java.io.*;

@Service
public class AutenticacionServiceImpl  implements AutenticacionService {

   @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO)throws IOException {

        String []datosUsuarios=null;
        Resource resource= resourceLoader.getResource("classpath:usuarios.txt");

        try(BufferedReader br= new BufferedReader(new FileReader(resource.getFile()))) {

            String linea;
            while ((linea=br.readLine())!=null) {
                String[] datos = linea.split(";");
                if (loginRequestDTO.tipoDocumento(). equals(datos[0])&&
                        loginRequestDTO.numeroDocumento().equals(datos[1])&&
                        loginRequestDTO.password().equals(datos[2])){

                    datosUsuarios =new String[2];
                    datosUsuarios [0]=datos[3];//Recuperar nombre
                    datosUsuarios [1]=datos[4];// Recuperar correo
                    break;
                }


            }
        } catch (IOException e){
            datosUsuarios=null;
            throw  new IOException(e);
        }



        return datosUsuarios;
    }

    @Override
    public String CierreSesion(SignOutRequestDTO signOutRequestDTO) throws IOException {
        String nombre_documento = "";
        String codigo = "";
        switch (signOutRequestDTO.tipoDocumento()) {
            case "1":
                nombre_documento = "DNI               ";
                break;
            case "2":
                nombre_documento = "Carnet_Extranjeria";
                break;
            default:
                nombre_documento = "Pasaporte         ";
        }

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File("src/main/resources/cierre.txt"), true));
            bf.write( nombre_documento +";"+ signOutRequestDTO.numeroDocumento() + "         ;" + signOutRequestDTO.fechaCierre()+ "\n");
            bf.close();
            codigo = "01";
        }catch (Exception e) {
            codigo = null;
            System.out.println(e.getMessage());
            throw new IOException(e);
        }
        return codigo;
    }
}
