/*---------------------------------------------------------------------------------------
  Framework para javascript - jwifi
  Desenvolvedor: Wilton de Paula Filho
  Ultima atualizacao: Dezembro de 2012
  Contatos: http://www.wiltonfilho.com; wiltonpaulafilho@gmail.com
---------------------------------------------------------------------------------------*/


/****************************************************************************************
  Funcao: mudarEstilosInputs
  Objetivo: Mudar o estilo (CSS) de todos os inputs dispon�veis na p�gina cujos conteudos
	   forem iguais a valorRef (atributo)
  Parametros:
  		- tipoTag: tipo de tag a ser validada. Ex: input, textarea, select
  		- tipoInput: tipo de campo a ser validado. Ex: text, button, etc
		- valorRef: valor utilizado como refer�ncia para altera��o de estilos
		- nomeClasse: classe em CSS contendo o estilo a ser aplicado a cada um dos inputs
  Retorno: nenhum
  Exemplos de chamada de fun��o: 
  		- mudarEstilosInputs('input','text','','inputTextErro'); 
		- mudarEstilosInputs('input','button','','inputTextErro'); 
		- mudarEstilosInputs('textarea','textarea','','inputTextErro'); 
****************************************************************************************/
function mudarEstilosInputs(tipoTag, tipoInput, valorRef, nomeClasse) {
	var nroCampos = document.getElementsByTagName(tipoTag).length;

	for (i=0; i<nroCampos; i++) {
			if (tipoInput == "button") {
				if (document.getElementsByTagName(tipoTag)[i].type == tipoInput)
					document.getElementsByTagName(tipoTag)[i].className=nomeClasse;
			}
			else if (document.getElementsByTagName(tipoTag)[i].value == valorRef)
					document.getElementsByTagName(tipoTag)[i].className=nomeClasse;
	}
}

/****************************************************************************************
  Funcao: inserirMascara
  Objetivo: Insere qualquer mascara em um campo qualquer a medida que o usu�rio for 
  				digitando a informa��o
  Parametros:
  		- idCampo: id do campo onde sera aplicada a mascara
		- mascara: mascara do campo. Ex: dd/mm/aaaa, xxx.xxx.xxx-yy, (000)0000-0000
  Retorno: nenhum
  Exemplos de chamada de fun��o: 
  		- inserirMascara('txtData','dd/mm/aaaa');
  		- inserirMascara('txtCPF','xxx.xxx.xxx-xx');
  		- inserirMascara('txtCEP','yy.xxx-www');
  OBS: A chamada desta funcao devera ser feita pelo manipulador de eventos onkeypress 
  de cada campo de entrada de dados. 
  Ex: <input type="text" id="txtData" onKeyPress="inserirMascara('txtData','dd/mm/aaaa')">
****************************************************************************************/
function inserirMascara(idCampo, mascara) {
	
	var tamanhoMascara = mascara.length;

	// Limita a quantidade de caracteres a ser digitada pelo usuario dentro do campo em relacao a 
	// quantidade de caracteres contidos na mascara
	if (document.getElementById(idCampo).value.length > tamanhoMascara-1)
		document.getElementById(idCampo).value = (document.getElementById(idCampo).value).substr(0,tamanhoMascara-1);
	else {

		var i;
		var vetorPosicoes = new Array(); // Vetor contendo as posicos de cada s�mbolo da mascara
		var vetorSimbolos = new Array(); // Vetor contendo todos os s�mbolos da mascara
		var padrao = /[a-zA-Z0-9]/;     // Na mascara e permitido ao usuario digitar qualquer caracater alfanumerico
	
		// Criacao de dois vetores: um contendo os simbolos da mascara e outro as posicoes de cada simbolo
		// dentro da mascara
		for (i=0; i<tamanhoMascara; i++) {
			if (mascara.charAt(i).match(padrao)==null) {
				vetorSimbolos.push(mascara.charAt(i));
				vetorPosicoes.push(i+1);
			}
		}
	
		var qtidadeSimbolos = vetorPosicoes.length;
		var qtidadePosicoes = vetorSimbolos.length;
		var tamanhoCampo = document.getElementById(idCampo).value.length;
		// Insere os simbolos na informacao digitada pelo usu�rio. Cria��o da mascara dinamicamente
		for (i=0; i<vetorPosicoes.length; i++) {
			// Caracteres insuficientes para aplicar um simbolo especifico da mascara
			if (tamanhoCampo < vetorPosicoes[i]) break; 
			else {
				var texto = document.getElementById(idCampo).value;
				if (texto[vetorPosicoes[i]-1] != vetorSimbolos[i])
					document.getElementById(idCampo).value = texto.slice(0,vetorPosicoes[i]-1) + vetorSimbolos[i] + texto.slice(vetorPosicoes[i] - 1);
			}
		}
	}
}

/****************************************************************************************
  Funcao: validarInformacao
  Objetivo: Mudar a formata��o do campo de entrada de dados do formulario, alterando a cor 
            de fundo e da borda e o espa�amento interno, conforme o resultado da 
			valida��o, ou seja, se a informacao digitada pelo usu�rio for compativel/imcompativel 
			com o padrao esperado, aquelas propriedades do campo serao alteradas (cor de fundo, etc)
			conforme os valores do terceiro e quarto parametros.
  Parametros:
  		- idCampo: id do campo onde sera aplicada a mascara
		- expReg: expressao regular contendo o padrao esperado a ser digitado pelo usuario
		          dentro do campo
		- classeUsuarioSucesso (opcional): classe CSS contendo a configuracao a ser aplicada no campo, idCampo,
		  caso o usuario digite a informacao CORRESPONDENTE ao padrao. No caso de informacao opcional dever�
		  ser passado como parametro o valor ''
		- classeUsuarioErro (opcional): classe CSS contendo a configuracao a ser aplicada no campo, idCampo,
		  caso o usuario digite uma informacao INCOMPAT�VEL ao padrao. No caso de informacao opcional dever�
		  ser passado como parametro o valor ''
  Retorno: nenhum
  Exemplos de chamada de fun��o: 
  		- validarInformacao('txtData','dd/mm/aaaa','',''); // se o usu�rio digitar no campo 00/00/0000 (valido) ou dd/00/0000
		  (invalido) sera aplicado ao campo a formatacao CSS default da funcao
  		- validarInformacao('txtData','dd/mm/aaaa','classeSucesso',''); // se o usu�rio digitar no campo 00/00/0000
		  sera aplicado ao campo a formatacao CSS contida dentro da classe classeSucesso, passada como parametro
		- validarInformacao('txtData','dd/mm/aaaa','classeSucesso','classeErro'); // se o usu�rio digitar no campo 0d/00/0000
		  sera aplicado ao campo a formatacao CSS contida dentro da classe classeErro, passada como parametro
  Ex: <input type="text" id="txtData" onclick="validarInformacao('txtData',/\d{2}\/\d{2}\/\d{4},'','msgErro')">
****************************************************************************************/
function validarInformacao(idCampo, expReg, classeUsuarioSucesso, classeUsuarioErro){
	var valor = document.getElementById(idCampo).value;
		
	var estiloSucesso = '.classeGenericaErro { background-color:#FFF4F4; border:#FF6666 1px solid; padding:3px;} ';
	var estiloErro = '.classeGenericaSucesso { background-color:#E2FFE1; color:#333333; border:green 1px solid; padding:3px; }';

	var style = document.createElement('style');	
	style.type = 'text/css';

	style.innerHTML = estiloSucesso + estiloErro;
	document.getElementsByTagName('head')[0].appendChild(style);
	
	// Expressao informada pelo usuario foi validada com sucesso pelo padrao
	if (expReg.test(valor)) {
		if (classeUsuarioSucesso == "") // Se o usuario nao especificar a classe de sucesso em CSS, "", usa a default da funcao -> classeGenericaSucesso
			document.getElementById(idCampo).className = 'classeGenericaSucesso';
		else  // Usa a classe de sucesso do usu�rio
			document.getElementById(idCampo).className = classeUsuarioSucesso;
	}	
	else if (classeUsuarioErro == "") // Se o usuario nao especificar a classe de erro em CSS, "", usa a default da funcao -> classeGenericaErro
			document.getElementById(idCampo).className = 'classeGenericaErro';
		 else // Usa a classe de erro do usu�rio
			document.getElementById(idCampo).className = classeUsuarioErro;
}