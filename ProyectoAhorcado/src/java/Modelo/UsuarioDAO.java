
package Modelo;

import Config.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
     public Usuario validarUsuario(String usuario, String contraseña) {
        Usuario usu = null;
        
        String sql = "{call sp_ValidarUsuario(?,?)}";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si encuentra el usuario, crea el objeto Usuario con datos
                usu = new Usuario();
                usu.setCodigoUsuario(rs.getInt("codigo_usuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setContraseña(rs.getString("contraseña"));
                // agrega otros campos si los tienes
            } else {
                // Si no encuentra usuario, puedes retornar null o lanzar excepción o mensaje
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return usu;
    }
}
