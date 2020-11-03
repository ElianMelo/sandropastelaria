       function alterarBotao(event) {
            clickedButton = event.target;
            valor = clickedButton.value;
            if (clickedButton.innerText == 'LIMPA') {
                clickedButton.style.backgroundColor = '#db5855';
                clickedButton.innerText = 'SUJA';
            } else if (clickedButton.innerText == 'SUJA') {
                clickedButton.style.backgroundColor = '#61c473'
                clickedButton.innerText = 'LIMPA';
            } else if (clickedButton.innerText == 'LIVRE') {
                clickedButton.style.backgroundColor = '#db5855'
                clickedButton.innerText = 'OCUPADA';
            } else if (clickedButton.innerText == 'OCUPADA') {
                clickedButton.style.backgroundColor = '#61c473'
                clickedButton.innerText = 'LIVRE';
            }
        }