package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import db.CadastroPojo;
import db.ContatoDAO;
import db.DB;


public class FrameInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogradouro;
	private JTextField txtNumero;

	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;
	private Integer codCli = 0;
	private String opt;
	private String vrCpf;
	private String vrCnpj;
	String vrcompDoc[] = { "Nenhum", "Cpf", "Cnpj" };
	estCivil  vtestCivil[]=estCivil.values();
	private Integer  tipoDoc = 0 ;  
	private JTextField txtIdt;
	private JTextField txtEmiss;
	private JTextField txtDtEmiss;
	private JTextField txtDtNasc;
	private JTextField txtEstCivil;
	private JTextField txtNacionalidade;
	private JTextField txtNaturalidade;

	public FrameInicio() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		contentPane.paintComponents(getGraphics());

		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		lblTitulo.setBounds(293, 20, 400, 16);

		JLabel lblCodigo = new JLabel("Código..: ");
		lblCodigo.setBounds(16, 59, 70, 20);
		contentPane.add(lblCodigo);

		String numero = null;

		JTextField txtCodigo = new JFormattedTextField(new MaskFormatter("#####"));
		txtCodigo.setBounds(76, 59, 70, 20);
		txtCodigo.setCaretPosition(0);

		codCli = leNextCodigo();

		txtCodigo.setText("" + codCli);

		txtCodigo.setEnabled(false);
		contentPane.add(txtCodigo);

		JLabel lblNome = new JLabel("Nome..: ");
		lblNome.setBounds(175, 59, 70, 20);
		contentPane.add(lblNome);

		JTextField txtNome = new JTextField("Nome Cliente ");
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String nome = txtNome.getText().trim();
				Integer var = txtNome.getText().trim().length();

				if (nome == null || var == 0) {
					JOptionPane.showMessageDialog(null, "Atenção !!! Nome não pode ser igual a nulo ");
					txtNome.requestFocus();
					txtNome.requestFocusInWindow(null);
				}
			}

		});

		txtNome.setBounds(228, 59, 566, 20);
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
				cep = cepAux.trim();
				if (cep.equals("") || var == 0) {
					JOptionPane.showMessageDialog(txtCep, "Cep Nao pode ser igual a Nulo ");
					txtCep.requestFocus();
				} else {
					Endereco endereco = null;
					try {
						endereco = veCep(cep);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					String ret = endereco.toString().substring(20, 24);

					String varend = "null";

					if (ret.equals(varend)) {
						txtLogradouro.setText("");
						txtBairro.setText("");
						txtCidade.setText("");
						txtUf.setText("");
						String msg = "Cep : " + cep + " não encontrado , verifique !!!";
						JOptionPane.showMessageDialog(null, msg);
						txtCep.requestFocus();
					} else {
						txtLogradouro.setText(endereco.getLogradouro());
						txtBairro.setText(endereco.getBairro());
						txtCidade.setText(endereco.getLocalidade());
						txtUf.setText(endereco.getuf());
					}
				}

			}
		});
		txtCep.setBounds(76, 103, 84, 20);
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

		JFormattedTextField txtNumero = new JFormattedTextField(new MaskFormatter("**********"));
		txtNumero.setText("N/A");
		txtNumero.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				String Numero = txtNumero.getText();
				Integer var = txtNumero.getText().trim().length();
				String comp = "";
				if (numero == comp || var == 0) {
					JOptionPane.showMessageDialog(txtCep, "Numero  Nao pode ser igual a Nulo ");

					txtNumero.requestFocus();
				}

			}

		});

		txtNumero.setCaretPosition(0);
		txtNumero.setBounds(724, 103, 70, 20);
		contentPane.add(txtNumero);

		JLabel lblComplemento = new JLabel("Comp....:");
		lblComplemento.setBounds(16, 147, 58, 20);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField("N/A");
		txtComplemento.setBounds(76, 147, 84, 20);
		txtComplemento.setCaretPosition(0);

		contentPane.add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro.:");
		lblBairro.setBounds(175, 147, 58, 20);
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

		JLabel lblDoc = new JLabel("Tp Doc..: ");
		lblDoc.setBounds(16, 190, 62, 20);
		lblDoc.setVisible(false);
		contentPane.add(lblDoc);

		JComboBox<TipoDoc> cmbDoc = new JComboBox<>();
		cmbDoc.setModel(new DefaultComboBoxModel<>(TipoDoc.values()));
		cmbDoc.setBounds(76, 190, 105, 20);
		cmbDoc.setVisible(false);

		txtUf = new JTextField("UF");
		txtUf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Object[] options = { "Confirmar", "Cancelar" };

				int resposta = JOptionPane.showOptionDialog(null, "Confirma a inclusão ? ", "Informação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (resposta == 0) {
					CadastroPojo cadastro = new CadastroPojo();

					cadastro.setCodigo(codCli);
					cadastro.setNome(txtNome.getText());
					String cep = txtCep.getText();
					String cepAux = cep.replaceAll("-", "");
					cep = cepAux;
					cadastro.setCep(cep);
					cadastro.setLogradouro(txtLogradouro.getText());
					cadastro.setNumero(txtNumero.getText());
					cadastro.setComplemento(txtComplemento.getText());
					cadastro.setBairro(txtBairro.getText());
					cadastro.setCidade(txtCidade.getText());
					cadastro.setUF(txtUf.getText());

					ContatoDAO dao;
					try {
						dao = new ContatoDAO();
						dao.adiciona(cadastro);
						insereCod(codCli);
						codCli = leNextCodigo();
						txtCodigo.setText(codCli.toString());
						txtNome.setEnabled(false);
						txtCep.setEnabled(false);
						txtLogradouro.setEnabled(false);
						txtNumero.setEnabled(false);
						txtComplemento.setEnabled(false);
						txtBairro.setEnabled(false);
						txtCidade.setEnabled(false);
						txtUf.setEnabled(false);

						lblDoc.setVisible(true);
						cmbDoc.setVisible(true);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else
					txtNome.requestFocus();
			}
		});
		txtUf.setCaretPosition(0);
		txtUf.setBounds(759, 147, 35, 20);
		contentPane.add(txtUf);

		JLabel lblDiv = new JLabel(
				"-------------------------------------------------------------------------------------------------");
		lblDiv.setBounds(16, 315, 778, 20);
		contentPane.add(lblDiv);

		JFormattedTextField txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				vrCpf = txtCpf.getText();
				String vrCpfaux = vrCpf.replace(".", "");
				vrCpf = vrCpfaux.replace("-", "");
				boolean testeCpf = validaCPF.isCPF(vrCpf);
				//System.out.println(testeCpf);
				if (!testeCpf) {
					JOptionPane.showMessageDialog(null, "Cpf :" + txtCpf.getText() + " Inválido !!! ");
					txtCpf.requestFocus();
				}

			}
		});

		txtCpf.setVisible(false);
		txtCpf.setCaretPosition(0);
		txtCpf.setBounds(76, 190, 120, 20);
		contentPane.add(txtCpf);

		JFormattedTextField txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		txtCnpj.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				vrCnpj = txtCnpj.getText();

				String vrCnpjaux = vrCnpj.replace(".", "");
				vrCnpj = vrCnpjaux.replace("-", "");
				vrCnpjaux = vrCnpj.replace("/", "");
				vrCnpj = vrCnpjaux;

				boolean testeCnpj = validaCNPJ.isCNPJ(vrCnpj);

				//System.out.printf("Cnpj Enviado : %s \n ", vrCnpj);
				//System.out.println(testeCnpj);
				if (!testeCnpj) {
					JOptionPane.showMessageDialog(null, "Cnpj :" + txtCnpj.getText() + " Inválido !!! ");
					txtCnpj.requestFocus();
				}


			}
		});

		txtCnpj.setVisible(false);
		txtCnpj.setBounds(76, 190, 145, 20);
		txtCnpj.setCaretPosition(0);
		contentPane.add(txtCnpj);

		
			
		JLabel lblIdt = new JLabel("Identidade :");
		lblIdt.setBounds(228, 190, 84, 20);
		lblIdt.setVisible(false ); 
		contentPane.add(lblIdt);
		
		txtIdt = new JTextField("N/A");
		txtIdt.setCaretPosition(0);
		txtIdt.setBounds(303, 190, 129, 20);
		txtIdt.setVisible(false); 
		contentPane.add(txtIdt);
		
		JLabel lblEmiss = new JLabel("Emissor : ");
		lblEmiss.setBounds(438, 190, 62, 20);
		lblEmiss.setVisible(false); 
		contentPane.add(lblEmiss);
		
		txtEmiss = new JTextField("N/A");
		txtEmiss.setCaretPosition(0);
		txtEmiss.setBounds(504, 190, 120, 20);
		txtEmiss.setVisible(false); 
		contentPane.add(txtEmiss);
		
		JLabel lblDtEmiss = new JLabel("Dt. Emiss:");
		lblDtEmiss.setBounds(626, 190, 90, 20);
		lblDtEmiss.setVisible(false); 
		contentPane.add(lblDtEmiss);
		
		//isDateValid
		
		JFormattedTextField txtDtEmiss = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDtEmiss.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				String dtEmiss = txtDtEmiss.getText();
				// Calendar hoje = Calendar.getInstance();

				boolean valData = ValidaData.isDateValid(dtEmiss);

				if (!valData ) {
					JOptionPane.showMessageDialog(null, "Data de Emissão  :" + txtDtEmiss.getText() + " Inválida !!! ");

					txtDtEmiss.requestFocus();

					// contentPane.add(txtDtEmiss);
				} else {

					boolean resu = ValidaData.ValidDtAtu(dtEmiss);

					if (!resu) {
						JOptionPane.showMessageDialog(null,
								"Data de Emissão  :" + txtDtEmiss.getText() + " maior que data de hoje  ");
						txtDtEmiss.requestFocus();
					}

				}

			}
		});
		txtDtEmiss.setCaretPosition(0);
		txtDtEmiss.setBounds(698, 190, 96, 20);
		txtDtEmiss.setVisible(false); 
		contentPane.add(txtDtEmiss);
		
		
		
		
		JLabel lblDtNasc = new JLabel("Dt. Nasc : ");
		lblDtNasc.setBounds(16, 233, 70, 20);
		lblDtNasc.setVisible(false); 
		contentPane.add(lblDtNasc);
		
		
		JFormattedTextField txtDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		//txtDtNasc = new JTextField("");
		txtDtNasc.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				String dtNasc = txtDtNasc.getText();
				// Calendar hoje = Calendar.getInstance();

				boolean valData = ValidaData.isDateValid(dtNasc);

				if (!valData ) {
					JOptionPane.showMessageDialog(null, "Data de Nascimento  :" + txtDtNasc.getText() + " Inválida !!! ");

					txtDtEmiss.requestFocus();

				}
			}
		}); 
		
		
		
		
		
		
		
		txtDtNasc.setCaretPosition(0);
		txtDtNasc.setBounds(76, 233, 96, 20);
		txtDtNasc.setVisible(false);
		contentPane.add(txtDtNasc);
		
		JLabel lblEstcivil = new JLabel("Est.Civil :");
		lblEstcivil.setBounds(175, 233, 70, 20);
		lblEstcivil.setVisible(false);
		contentPane.add(lblEstcivil);
		
		
		
		JComboBox<estCivil> cmbEstCivil = new JComboBox<>();
		cmbEstCivil.setModel(new DefaultComboBoxModel<>(estCivil.values()));
		cmbEstCivil.setBounds(250, 233, 105, 20);
		cmbEstCivil.setVisible(false);
		contentPane.add(cmbEstCivil); 
		
		
		
		cmbDoc.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) // para evitar duplicações
					//System.out.printf("Você escolheu a opção [%s] \n ", e.getItem());
				opt = e.getItem().toString();
				if (opt == vrcompDoc[1]) {
					tipoDoc=1  ; 
					lblDoc.setText("Cpf ..: ");
					txtCpf.setVisible(true);
					txtCpf.requestFocus();
				} else if (opt == vrcompDoc[2]) {
					tipoDoc=2 ;
					lblDoc.setText("Cnpj..: ");
					//System.out.println("Iniciando CNPJ ");
					txtCnpj.setVisible(true);
					txtCnpj.requestFocus();

				}
				cmbDoc.setVisible(false);
				
				
			    lblDtNasc.setVisible(true); 
                txtDtNasc.setVisible(true);
                lblEstcivil.setVisible(true);
                lblIdt.setVisible(true); 
                txtIdt.setVisible(true); 
                lblEmiss.setVisible(true); 
                txtEmiss.setVisible(true); 
                lblDtEmiss.setVisible(true); 
                txtDtEmiss.setVisible(true); 
                
                cmbEstCivil.setVisible(true);

			}
		});

		contentPane.add(cmbDoc);
		
		
		cmbEstCivil.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) // para evitar duplicações
					//System.out.printf("Você escolheu a opção [%s] \n ", e.getItem());
				opt = e.getItem().toString();
				
				
				for ( int i = 0 ; i<  vtestCivil.length ; i++ )
				{ 
					String var = vtestCivil[i].toString(); 
					if ( var == opt )
						txtEstCivil.setText(var);
				}
				
				//txtEstCivil.setText(vtestCivil[opt]);
				cmbEstCivil.setVisible(false);
				///  txtEstCivil.setEditable(false) ; 
				txtEstCivil.setVisible(true) ; 
				
			}
		});

		

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DB.closeConnection();

				System.exit(0);
			}
		});

		
		
		
		
		
		
		btnSair.setForeground(Color.RED);
		btnSair.setBounds(660, 500, 117, 29);
		contentPane.add(btnSair);
		
		txtEstCivil = new JTextField("Estado Civil");
		txtEstCivil.setCaretPosition(0);
		txtEstCivil.setBounds(238, 233, 120, 20);
		txtEstCivil.setVisible(false);
		contentPane.add(txtEstCivil);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade :");
		lblNacionalidade.setBounds(367, 233, 97, 20);
		contentPane.add(lblNacionalidade);
		
		txtNacionalidade = new JTextField("Nacionalidade");
		txtNacionalidade.setCaretPosition(0);
		txtNacionalidade.setBounds(466, 233, 129, 20);
		contentPane.add(txtNacionalidade);
		
		JLabel lblNaturalidade = new JLabel("Naturalidade :");
		lblNaturalidade.setBounds(596, 233, 97, 20);
		contentPane.add(lblNaturalidade);
		
		txtNaturalidade = new JTextField("Naturalidade");
		txtNaturalidade.setCaretPosition(0);
		txtNaturalidade.setBounds(689, 233, 105, 20);
		contentPane.add(txtNaturalidade);
		
		
		
		

	}

	public static Endereco veCep(String cep) throws Exception {
		String cepAux = cep.replaceAll("-", "");
		cep = cepAux;
		Endereco endereco = null;
		if (cep.equals(""))
			JOptionPane.showMessageDialog(null, "Favor Digitar o Cep ");
		else
			endereco = ServicoDeCep.buscaEnderecoPelo(cep);

		return (endereco);

	}

	public static Integer leNextCodigo() throws SQLException {

		ContatoDAO concod = new ContatoDAO();
		Integer codCli = concod.selCod();
		return codCli;

	}

	public void insereCod(Integer codCli) throws SQLException {
		ContatoDAO concod = new ContatoDAO();
		concod.insCod(codCli);

	}
}