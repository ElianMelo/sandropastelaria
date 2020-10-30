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

menuBar.addEventListener('click', (e) => {
  const componentsScreens = [tableData, formRegister, formUpdate];
  let idNumber = (e.target.id) ? Number(e.target.id) : null;
	

  if (idNumber) {
	idNumber--;
    enableOrDisableClass(componentsScreens, idNumber);
  }
});