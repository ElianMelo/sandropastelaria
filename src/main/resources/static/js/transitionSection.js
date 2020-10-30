const menuBar = document.querySelector('.menu-bar');
const tableData = document.querySelector('.table-data');
const formRegister = document.querySelector('.form-register');
const formUpdate = document.querySelector('.form-update');

const enableOrDisableClass = (componentsScreens, idNumber) => {
  componentsScreens.forEach((component, index) => {
    if (index === idNumber) {
      if (component.classList.contains('disabled')) {
        component.classList.remove('disabled')
      }
      component.classList.add('activated')
    } else {
      if (component.classList.contains('activated')) {
        component.classList.remove('activated')
      }
      component.classList.add('disabled')
    }
  });
}

const enableBounceAnimation = (e) => {
	const clickedIcon = e.target;
	
	if(clickedIcon.classList.contains('animation-bounce')) {
		clickedIcon.classList.remove('animation-bounce');
		setTimeout(() => { clickedIcon.classList.add('animation-bounce'); }, 50);
 	} else {	
		clickedIcon.classList.add('animation-bounce');
	}
}

menuBar.addEventListener('click', (e) => {
  const componentsScreens = [tableData, formRegister, formUpdate];
  let idNumber = (e.target.id) ? Number(e.target.id) : null;
	

  if (idNumber) {
	enableBounceAnimation(e); 
	idNumber--;
    enableOrDisableClass(componentsScreens, idNumber);
  }
});