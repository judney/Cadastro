package db;



public class CadastroPojo {
        private int codigo;
        private String nome;
        private String cep ; 
        private String logradouro ; 
        private String numero ; 
        private String Complemento ; 
        private String Bairro ; 
        private String Cidade ;
        
        
		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getComplemento() {
			return Complemento;
		}

		public void setComplemento(String complemento) {
			Complemento = complemento;
		}

		public String getBairro() {
			return Bairro;
		}

		public void setBairro(String bairro) {
			Bairro = bairro;
		}

		public String getCidade() {
			return Cidade;
		}

		public void setCidade(String cidade) {
			Cidade = cidade;
		}

		public String getUF() {
			return UF;
		}

		public void setUF(String uF) {
			UF = uF;
		}

		private String UF ; 
        

        public String getLogradouro() {
			return logradouro;
		}

		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		public int getCodigo() {
                return codigo;
        }

        public void setCodigo(int codigo) {
        
	
                this.codigo = codigo;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        
        public String getCep() {
            return cep ;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        
}