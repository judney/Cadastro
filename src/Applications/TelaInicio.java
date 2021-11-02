package Applications;

//import java.text.ParseException;

//import entities.FrameInicio;
import entities.TelaCliente;

public class TelaInicio {

        public static void main(String[] args) throws Exception {

                //FrameInicio frame = new FrameInicio() ;
                TelaCliente frame = new TelaCliente() ; 
                
                //btnSair.setBounds(660, 500, 117, 29);
                frame.setBounds(10,10,800,550); 
                
                
                frame.setVisible(true) ;

        }

}