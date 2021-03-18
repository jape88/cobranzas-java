package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Paramo
 * Controlador que se encarga de realzar la conexión con la base de datos
 */
public class Conexion {
   static String bd = "bd_cobranza";
   static String login = "root";
   static String password = "";
   static String url = "jdbc:mysql://localhost:3306/"+bd;

   Connection conn = null;

   public Conexion() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection(url,login,password);
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }

   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
      conn = null;
   }

}