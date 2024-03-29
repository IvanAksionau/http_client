function switchPreview(event, blockName) {
  document.querySelectorAll('div.tabcontent').forEach((element) => {
    element.style.display = 'none';
  });
  document.querySelectorAll('button.tablinks').forEach((element) => {
    element.classList.remove('active');
  });

  const codeBlock = document.getElementById(blockName);
  if (codeBlock) {
    codeBlock.style.display = 'block'
  }

  event.currentTarget.classList.add('active');
}
