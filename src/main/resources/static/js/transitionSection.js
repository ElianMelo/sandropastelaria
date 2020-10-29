const menuBar = document.querySelector('.menu-bar');
const tableData = document.querySelector('.table-data');
const formRegister = document.querySelector('.form-register');
const formUpdate = document.querySelector('.form-update');

const componentsScreen = [tableData, formRegister, formUpdate];

menuBar.addEventListener('click', (e) => {
  const idNumber = (e.target.id) ? Number(e.target.id) : null;

  switch (idNumber) {
    case 0:
      componentsScreen.forEach((component, index) => {
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
      })
      break;
    case 1:
      componentsScreen.forEach((component, index) => {
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
      })
      break;
    case 2:
      componentsScreen.forEach((component, index) => {
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
      })
      break;
    default:
      break;
  }
})