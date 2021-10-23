package entities;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
//import javax.swing.JFormattedTextField$AbstractFormatter;

public class FrameInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogradouro;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JTextField txtCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInicio frame = new FrameInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FrameInicio() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 100, 800, 600);
		contentPane = new JPanel();
		//contentPane.setBackground(Color.darkGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null) ; 
		contentPane.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.add(lblTitulo);
		
		lblTitulo.setBounds(250, 20, 400, 16);
		
		JLabel lblCodigo = new JLabel("Código..: ") ; 
		lblCodigo.setBounds(16, 59, 70, 20);
		contentPane.add(lblCodigo ); 
    		
		JTextField txtCodigo = new JFormattedTextField(new MaskFormatter("#####"));
		txtCodigo.setBounds(76,59, 70,20 );
		txtCodigo.setCaretPosition(0);
		txtCodigo.setText("00001");
		contentPane.add(txtCodigo); 
		
		
		JLabel lblNome = new JLabel("Nome..: ");
		lblNome.setBounds(175, 59, 70, 20);
		contentPane.add(lblNome);
		
		JTextField txtNome = new JTextField("Nome Cliente ");
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
	            String nome = txtNome.getText().trim() ;
	            Integer var = txtNome.getText().trim().length(); 
	            
	            System.out.println(nome+"||"+var );
	            if ( nome == null || var == 0   )  { 
	            	JOptionPane.showMessageDialog( null , "Atenção !!! Nome não pode ser igual a nulo ");
	            	txtNome.requestFocus(); 
	            	
	            	txtNome.requestFocusInWindow(null); 
	            }
			} 
	               
        }); 
	            	
		
		
		
		
		
		txtNome.setBounds(228,59, 566,20 );
		txtNome.setCaretPosition(0);
		
		
		contentPane.add(txtNome); 
		
		JLabel lblCep = new JLabel("Cep.......: ");
		lblCep.setBounds(16, 103, 70, 20);
		contentPane.add(lblCep);
		
		
		

		JTextField txtCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String cep = txtCep.getText();
			    Integer var = txtCep.getText().trim().length(); 
				String cepAux = cep.replaceAll("-", "");
			    cep=cepAux ;
				System.out.println(cep);
				if ( cep == null || var == 0 ) { 
				JOptionPane.showMessageDialog(txtCep, "Cep Nao pode ser igual a Nulo "); 
				txtCep.requestFocus(); 
				}
				else 
				{ 
					Endereco endereco=null ;
					try {
						endereco = veCep( cep);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				    txtLogradouro.setText(endereco.getLogradouro()); 
			        txtBairro.setText(endereco.getBairro()); 
			        txtCidade.setText(endereco.getLocalidade()); 
			        txtUf.setText(endereco.getuf()); 
			        
				}    
			        
					
			}
		});
		txtCep.setBounds(76,103, 84,20 );
		txtCep.setCaretPosition(0);
		
		
		
		contentPane.add(txtCep); 
		
		
		JLabel lblLogradouro = new JLabel("Logradouro..:");
		lblLogradouro.setBounds(175, 103, 89, 20);
		contentPane.add(lblLogradouro);
		
		txtLogradouro = new JTextField("Logradouro");
		txtLogradouro.setCaretPosition(0);
		txtLogradouro.setBounds(264, 103, 413, 20);
		contentPane.add(txtLogradouro);
		
		JLabel lblNum = new JLabel("Num : ");
		lblNum.setBounds(684, 103, 42, 20);
		contentPane.add(lblNum);
		
		JFormattedTextField txtNumero = new JFormattedTextField(new MaskFormatter("#######") );
		txtNumero.setCaretPosition(0);
		txtNumero.setBounds(724, 103, 70, 20);
		contentPane.add(txtNumero);
			
		
		
		JLabel lblComplemento = new JLabel("Complemento .:");
		lblComplemento.setBounds(16, 147, 108, 20);
		contentPane.add(lblComplemento);
		
		txtComplemento = new JTextField("Comp.");
		txtComplemento.setCaretPosition(0);
		txtComplemento.setBounds(116, 147, 70, 20);
		contentPane.add(txtComplemento);
		
		JLabel lblBairro = new JLabel("Bairro.:");
		lblBairro.setBounds(187, 147, 58, 20);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField("Bairro");
		txtBairro.setCaretPosition(0);
		txtBairro.setBounds(228, 147, 265, 20);
		contentPane.add(txtBairro);
		
		

		JLabel lblCidade = new JLabel("Cidade.:");
		lblCidade.setBounds(494, 147, 51, 20);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField("Cidade");
		txtCidade.setCaretPosition(0);
		txtCidade.setBounds(545, 147, 193, 20);
		contentPane.add(txtCidade);
		
		
		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(739, 147, 20, 20);
		contentPane.add(lblUf);
		
		txtUf = new JTextField("UF");
		txtUf.setCaretPosition(0);
		txtUf.setBounds(759, 147, 35, 20);
		contentPane.add(txtUf);
		
		
		
		JLabel lblDiv = new JLabel("-------------------------------------------------------------------------------------------------"); 
		lblDiv.setBounds(16, 166, 778, 20);
		contentPane.add(lblDiv);
		
		
		
		JPanel pnlOptDoc = new JPanel();
		pnlOptDoc.setBounds(16, 198, 136, 95);
		contentPane.add(pnlOptDoc);
		pnlOptDoc.setLayout(null);
		
		JLabel lblTipoDeDocumento = new JLabel("Tipo de Documento");
		lblTipoDeDocumento.setToolTipText("");
		lblTipoDeDocumento.setBounds(6, 6, 124, 16);
		pnlOptDoc.add(lblTipoDeDocumento);
		
		
		JRadioButton rdbtnCnpj = new JRadioButton("CNPJ");
		rdbtnCnpj.setBounds(6, 66, 62, 23);
		pnlOptDoc.add(rdbtnCnpj);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("CPF");
		rdbtnNewRadioButton.setBounds(6, 34, 55, 23);
		pnlOptDoc.add(rdbtnNewRadioButton);
		
		
		
		JLabel lblDoc = new JLabel("Documento");
		lblDoc.setBounds(16, 307, 84, 20);
		lblDoc.setVisible(false);  
		contentPane.add(lblDoc);
		
		JTextField txtDoc = new JTextField("Documento");
		txtDoc.setCaretPosition(0);
		txtDoc.setBounds(112, 307, 117, 20);
		txtDoc.setVisible( false ); 
		contentPane.add(txtDoc);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Button btnSair = new Button("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		btnSair.setForeground(Color.RED); 
		btnSair.setBounds(660, 500, 117, 29);
		contentPane.add(btnSair);
		/*
		txtCpf = new JTextField("684.789.847-04");
		txtCpf.setCaretPosition(0);
		txtCpf.setBounds(112, 307, 117, 20);
		contentPane.add(txtCpf);
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		}
	

	    public static  Endereco  veCep ( String  cep) throws Exception    {
		    //System.out.printf("CEP ....::: %s\n\n\n ",cep);
		    String cepAux = cep.replaceAll("-", "");
		    cep=cepAux ;
		    //System.out.printf("CEPAUX .::: [%s] \n\n\n ",cep);
		    
		    Endereco endereco=null; 
		    if ( cep.equals("") ) 
		    	JOptionPane.showMessageDialog(null, "Favor Digitar o Cep "); 
		    else 	 
		        endereco = ServicoDeCep.buscaEnderecoPelo(cep);
		    	System.out.println("Endereço :"+ endereco ); 
		return(  endereco ) ; 
	}
}
